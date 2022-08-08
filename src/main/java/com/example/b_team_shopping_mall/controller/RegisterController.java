package com.example.b_team_shopping_mall.controller;

import com.example.b_team_shopping_mall.dto.Register.RegisterLoginRequestDto;
import com.example.b_team_shopping_mall.dto.Register.RegisterSignUpRquestDto;
import com.example.b_team_shopping_mall.entity.Register;
import com.example.b_team_shopping_mall.response.Response;
import com.example.b_team_shopping_mall.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class RegisterController {
    private final RegisterService registerService;

    // 회원가입 명단 전체 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getRegisters(){
        return Response.success(registerService.getRegisters());
    }

    // 회원가입 controller
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@RequestBody @Valid RegisterSignUpRquestDto registerSignUpRquestDto){
        return Response.success(registerService.signUp(registerSignUpRquestDto));
    }

    // 로그인 controller
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Response login(@RequestBody @Valid RegisterLoginRequestDto registerLoginRequestDto){
        return Response.success(registerService.login(registerLoginRequestDto));
    }
}
