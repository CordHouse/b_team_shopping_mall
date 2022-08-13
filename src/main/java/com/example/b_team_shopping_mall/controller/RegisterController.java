package com.example.b_team_shopping_mall.controller;

import com.example.b_team_shopping_mall.dto.Register.RegisterLoginRequestDto;
import com.example.b_team_shopping_mall.dto.Register.RegisterLoginResponseDto;
import com.example.b_team_shopping_mall.dto.Register.RegisterSignUpRequestDto;
import com.example.b_team_shopping_mall.response.Response;
import com.example.b_team_shopping_mall.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        return Response.success(registerService.getRegisters());
    }

    // 회원가입 controller
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@RequestBody @Valid RegisterSignUpRequestDto registerSignUpRequestDto){
        registerService.signUp(registerSignUpRequestDto);
        return Response.success();
    }

    // 로그인 controller
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Response login(@RequestBody @Valid RegisterLoginRequestDto registerLoginRequestDto){
        return Response.success(registerService.login(registerLoginRequestDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/reissue")
    public Response reissue(@RequestBody RegisterLoginResponseDto tokenRequestDto) {
        return Response.success(registerService.reissue(tokenRequestDto));
    }
}
