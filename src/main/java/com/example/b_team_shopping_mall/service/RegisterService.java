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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    @Transactional
    public RegisterAdminResponseDto admin(RegisterAdminRequestDto registerAdminRequestDto){
        Register register = registerRepository.findByUsername(registerAdminRequestDto.getUsername()).orElseThrow(() -> {
            throw new RegisterNotFoundSearchUsernameException();
        });
        register.setAuthority(Authority.ROLE_MANAGER);
        return new RegisterAdminResponseDto().toDto(register);
    }

    // ???????????? ?????? ?????? ??????
    @Transactional(readOnly = true)
    public List<RegisterGetRegistersResponseDto> getRegisters() {
        List<Register> register = registerRepository.findAll();
        List<RegisterGetRegistersResponseDto> registerGetRegistersResponseDtos = new LinkedList<>();
        register.forEach(i -> registerGetRegistersResponseDtos.add(new RegisterGetRegistersResponseDto().toDto(i)));
        return registerGetRegistersResponseDtos;
    }

    // ???????????? ??????
    @Transactional
    public RegisterSignUpResponseDto signUp(RegisterSignUpRequestDto registerSignUpRequestDto) {
        List<Register> registerFilter = registerRepository.findAllByUsername(registerSignUpRequestDto.getUsername());

        if(!registerFilter.isEmpty())
            throw new RegisterOverlapException();

        Register register;
        if(registerSignUpRequestDto.getUsername().toLowerCase().equals("admin")){
            register = new Register(registerSignUpRequestDto.getName(), registerSignUpRequestDto.getUsername(), passwordEncoder.encode(registerSignUpRequestDto.getPassword().toLowerCase()), registerSignUpRequestDto.getEmail(), Authority.ROLE_ADMIN);
            registerRepository.save(register);
            return new RegisterSignUpResponseDto().toDto(register);
        }

        register = new Register(registerSignUpRequestDto.getName(), registerSignUpRequestDto.getUsername(), passwordEncoder.encode(registerSignUpRequestDto.getPassword()), registerSignUpRequestDto.getEmail(), Authority.ROLE_USER);
        registerRepository.save(register);
        return new RegisterSignUpResponseDto().toDto(register);
    }

    // ????????? ??????
    @Transactional
    public RegisterLoginResponseDto login(RegisterLoginRequestDto registerLoginRequestDto) {
        Register register = registerRepository.findByUsername(registerLoginRequestDto.getUsername()).orElseThrow(() -> {
            throw new RegisterNotFoundIdException();
        });

        if(passwordEncoder.matches(registerLoginRequestDto.getPassword(), register.getPassword())) { // passwordEncoder.matches(????????? pw, ?????? pw)

            // 1. Login ID/PW ??? ???????????? AuthenticationToken ??????
            UsernamePasswordAuthenticationToken authenticationToken = registerLoginRequestDto.toAuthentication();

            // 2. ????????? ?????? (????????? ???????????? ??????) ??? ??????????????? ??????
            //    authenticate ???????????? ????????? ??? ??? CustomUserDetailsService ?????? ???????????? loadUserByUsername ???????????? ?????????
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            // 3. ?????? ????????? ???????????? JWT ?????? ??????
            TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

            // 4. RefreshToken ??????
            RefreshToken refreshToken = RefreshToken.builder()
                    .key(authentication.getName())
                    .value(tokenDto.getRefreshToken())
                    .build();
            refreshTokenRepository.save(refreshToken);

            RegisterLoginResponseDto tokenResponseDto = new RegisterLoginResponseDto(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
            // 5. ?????? ??????
            return tokenResponseDto;
        }
        throw new RegisterNotFoundPasswordException();
    }

    @Transactional
    public RegisterSearchUsernameResponseDto searchUsername(RegisterSearchUsernameRequestDto registerSearchUsernameRequestDto){
        // DB ?????? ??????
        List<Register> register = registerRepository.findAllByName(registerSearchUsernameRequestDto.getName()).orElseThrow();

        if(register.isEmpty())
            throw new RegisterNotFoundSearchUsernameException();

        Register searchRegister = register.stream().filter(s -> s.getEmail().equals(registerSearchUsernameRequestDto.getEmail())).findFirst().orElseThrow(() -> {
            throw new RegisterNotFoundSearchEmailException();
        });

        // DB ????????????...
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
        Register register = registerRepository.findByUsernameAndEmail(registerSearchUserPasswordRequestDto.getUsername(), registerSearchUserPasswordRequestDto.getEmail()).orElseThrow(() -> {
            throw new RegisterNotFoundSearchUserPasswordException();
        });
        LocalTime localTimeNow = LocalTime.now();
        String tempPassword = localTimeNow.toString().replaceAll("[^0-9]","");
        register.setPassword(passwordEncoder.encode(tempPassword)); // ???????????? ????????? ?????? ???????????? ??????
        return new RegisterSearchUserPasswordResponseDto().toDto(register, tempPassword);
    }

    @Transactional
    public String changeUserPassword(RegisterChangeUserPasswordRequestDto registerChangeUserPasswordRequestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Register register = registerRepository.findByUsername(authentication.getName()).orElseThrow(() -> {
            throw new IllegalArgumentException("???????????? ???????????? ????????????.");
        });
        if (passwordEncoder.matches(registerChangeUserPasswordRequestDto.getBeforePassword(), register.getPassword())) {
            register.setPassword(passwordEncoder.encode(registerChangeUserPasswordRequestDto.getAfterPassword()));
            return "??????????????? ??????????????? ?????????????????????.";
        }
        return "?????? ??????????????? ???????????? ????????????.";
    }

    @Transactional
    public RegisterLoginResponseDto reissue(RegisterLoginResponseDto tokenRequestDto) {
        // 1. Refresh Token ??????
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token ??? ???????????? ????????????.");
        }

        // 2. Access Token ?????? Member ID ????????????
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. ??????????????? Member ID ??? ???????????? Refresh Token ??? ?????????
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("???????????? ??? ??????????????????."));

        // 4. Refresh Token ??????????????? ??????
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("????????? ?????? ????????? ???????????? ????????????.");
        }

        // 5. ????????? ?????? ??????
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. ????????? ?????? ????????????
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // ?????? ??????
        RegisterLoginResponseDto tokenResponseDto = new RegisterLoginResponseDto(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
        return tokenResponseDto;
    }
}
