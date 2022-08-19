package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.config.jwt.TokenProvider;
import com.example.b_team_shopping_mall.dto.Register.*;
import com.example.b_team_shopping_mall.entity.Authority;
import com.example.b_team_shopping_mall.entity.RefreshToken;
import com.example.b_team_shopping_mall.entity.Register;
import com.example.b_team_shopping_mall.exception.*;
import com.example.b_team_shopping_mall.repository.RefreshTokenRepository;
import com.example.b_team_shopping_mall.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RegisterRepository registerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // 회원가입 명단 전체 조회
    @Transactional(readOnly = true)
    public List<RegisterGetRegistersResponseDto> getRegisters() {
        List<Register> register = registerRepository.findAll();
        List<RegisterGetRegistersResponseDto> registerGetRegistersResponseDtos = new LinkedList<>();
        register.forEach(i -> registerGetRegistersResponseDtos.add(new RegisterGetRegistersResponseDto().toDto(i)));
        return registerGetRegistersResponseDtos;
    }

    // 회원가입 함수
    @Transactional
    public RegisterSignUpResponseDto signUp(RegisterSignUpRequestDto registerSignUpRequestDto) {
        Register register = new Register(registerSignUpRequestDto.getName(), registerSignUpRequestDto.getUsername(), passwordEncoder.encode(registerSignUpRequestDto.getPassword()), registerSignUpRequestDto.getEmail(), Authority.ROLE_USER, "false");
        registerRepository.save(register);
        return new RegisterSignUpResponseDto().toDto(register);
    }

    // 로그인 함수
    @Transactional
    public RegisterLoginResponseDto login(RegisterLoginRequestDto registerLoginRequestDto) {
        Register register = registerRepository.findByUsername(registerLoginRequestDto.getUsername()).orElseThrow(() -> {
            throw new RegisterNotFoundIdException();
        });

        if(passwordEncoder.matches(registerLoginRequestDto.getPassword(), register.getPassword()) ||
                (register.getTemppassword().equals("true")) && register.getPassword().equals(registerLoginRequestDto.getPassword())) { // passwordEncoder.matches(받아온 pw, 데베 pw)

            // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
            UsernamePasswordAuthenticationToken authenticationToken = registerLoginRequestDto.toAuthentication();

            // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
            //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

            // 4. RefreshToken 저장
            RefreshToken refreshToken = RefreshToken.builder()
                    .key(authentication.getName())
                    .value(tokenDto.getRefreshToken())
                    .build();
            refreshTokenRepository.save(refreshToken);

            RegisterLoginResponseDto tokenResponseDto = new RegisterLoginResponseDto(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
            // 5. 토큰 발급
            return tokenResponseDto;
        }
        throw new RegisterNotFoundPasswordException();
    }

    @Transactional
    public RegisterSearchUsernameResponseDto searchUsername(RegisterSearchUsernameRequestDto registerSearchUsernameRequestDto){
        // DB 한번 조회
        List<Register> register = registerRepository.findAllByName(registerSearchUsernameRequestDto.getName()).orElseThrow(() -> {
            throw new RegisterNotFoundSearchUsernameException();
        });

        Register searchRegister = register.stream().filter(s -> s.getEmail().equals(registerSearchUsernameRequestDto.getEmail())).findFirst().orElseThrow(() -> {
            throw new RegisterNotFoundSearchEmailException();
        });

        // DB 두번조회...
//        registerRepository.findByEmail(registerSearchUsernameRequestDto.getEmail()).orElseThrow(() -> {
//            throw new RegisterNotFoundSearchEmailException();
//        });
//        Register register = registerRepository.findByNameAndEmail(registerSearchUsernameRequestDto.getName(), registerSearchUsernameRequestDto.getEmail()).orElseThrow(() -> {
//            throw new RegisterNotFoundSearchUsernameException();
//        });
        return new RegisterSearchUsernameResponseDto().toDto(searchRegister);
    }

    @Transactional
    public RegisterSearchUserPasswordResponseDto searchUserPassword(RegisterSearchUserPasswordRequestDto registerSearchUserPasswordRequestDto){
        Register register = registerRepository.findByUsername(registerSearchUserPasswordRequestDto.getUsername()).orElseThrow(() -> {
            throw new RegisterNotFoundSearchUserPasswordException();
        });
        LocalTime localTimeNow = LocalTime.now();
        register.setPassword(passwordEncoder.encode(localTimeNow.toString())); // 보안상의 문제로 임시 비밀번호 발급
        register.setTemppassword("true"); // 임시 비밀번호 사용 중
        return new RegisterSearchUserPasswordResponseDto().toDto(register);
    }

    @Transactional
    public String changeUserPassword(RegisterChangeUserPasswordRequestDto registerChangeUserPasswordRequestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Register register = registerRepository.findByUsername(authentication.getName()).orElseThrow(() -> {
            throw new IllegalArgumentException("아이디가 존재하지 않습니다.");
        });
        if(register.getPassword().equals(registerChangeUserPasswordRequestDto.getBeforePassword())) {
            register.setPassword(passwordEncoder.encode(registerChangeUserPasswordRequestDto.getAfterPassword()));
            register.setTemppassword("false");
            return "비밀번호가 정상적으로 변경되었습니다.";
        }
        return "임시 비밀번호가 일치하지 않습니다.";
    }

    @Transactional
    public RegisterLoginResponseDto reissue(RegisterLoginResponseDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        RegisterLoginResponseDto tokenResponseDto = new RegisterLoginResponseDto(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
        return tokenResponseDto;
    }
}
