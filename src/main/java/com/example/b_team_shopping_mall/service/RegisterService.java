package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.config.jwt.JwtTokenProvider;
import com.example.b_team_shopping_mall.dto.Register.*;
import com.example.b_team_shopping_mall.entity.Register;
import com.example.b_team_shopping_mall.exception.RegisterNotFoundIdException;
import com.example.b_team_shopping_mall.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final RegisterRepository registerRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    // 회원가입 명단 전체 조회
    @Transactional(readOnly = true)
    public List<RegisterGetRegistersResponseDto> getRegisters(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication);
        List<Register> register = registerRepository.findAll();
        List<RegisterGetRegistersResponseDto> registerGetRegistersResponseDtos = new LinkedList<>();
        register.forEach(i -> registerGetRegistersResponseDtos.add(new RegisterGetRegistersResponseDto().toDto(i)));
        return registerGetRegistersResponseDtos;
    }

    // 회원가입 함수
    @Transactional
    public RegisterSignUpResponseDto signUp(RegisterSignUpRquestDto registerSignUpRquestDto){
        Register register = new Register(registerSignUpRquestDto.getUsername(), registerSignUpRquestDto.getUserid(), bCryptPasswordEncoder.encode(registerSignUpRquestDto.getPassword()), registerSignUpRquestDto.getEmail(), "ROLE_USER");
        registerRepository.save(register);
        return new RegisterSignUpResponseDto().toDto(register);
    }

    // 로그인 함수
    @Transactional
    public RegisterLoginResponseDto login(RegisterLoginRequestDto registerLoginRequestDto){
        Register register = registerRepository.findByUserid(registerLoginRequestDto.getUserid());
        if(bCryptPasswordEncoder.matches(registerLoginRequestDto.getPassword(), register.getPassword())) { // bCryptPasswordEncoder.matches(입력받은 비밀번호, DB에 비밀번호)
            UsernamePasswordAuthenticationToken authenticationToken = registerLoginRequestDto.toAuthentication();
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            String accessToken = jwtTokenProvider.createToken(authentication);
            return new RegisterLoginResponseDto().toDto(accessToken);
        }

        throw new RegisterNotFoundIdException();
    }
}
