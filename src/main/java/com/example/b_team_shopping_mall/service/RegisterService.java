package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.dto.Register.*;
import com.example.b_team_shopping_mall.entity.Register;
import com.example.b_team_shopping_mall.exception.RegisterNotFoundIdException;
import com.example.b_team_shopping_mall.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final RegisterRepository registerRepository;

    // 회원가입 명단 전체 조회
    @Transactional
    public List<RegisterGetRegistersResponseDto> getRegisters(){
        List<Register> register = registerRepository.findAll();
        List<RegisterGetRegistersResponseDto> registerGetRegistersResponseDtos = new LinkedList<>();
        register.forEach(i -> registerGetRegistersResponseDtos.add(new RegisterGetRegistersResponseDto().toDto(i)));
        return registerGetRegistersResponseDtos;
    }

    // 회원가입 함수
    @Transactional
    public RegisterSignUpResponseDto signUp(RegisterSignUpRquestDto registerSignUpRquestDto){
        Register register = new Register(registerSignUpRquestDto.getUsername(), registerSignUpRquestDto.getUserid(), registerSignUpRquestDto.getPassword(), registerSignUpRquestDto.getEmail());
        registerRepository.save(register);
        return new RegisterSignUpResponseDto().toDto(register);
    }

    // 로그인 함수
    @Transactional
    public RegisterLoginResponseDto login(RegisterLoginRequestDto registerLoginRequestDto){
        Register register = registerRepository.findByuserid(registerLoginRequestDto.getUserid());
        if(register.getPassword().equals(registerLoginRequestDto.getPassword()))
            return new RegisterLoginResponseDto().toDto(registerLoginRequestDto);

        throw new RegisterNotFoundIdException();
    }
}
