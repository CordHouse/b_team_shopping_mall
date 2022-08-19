package com.example.b_team_shopping_mall.controller;

import com.example.b_team_shopping_mall.dto.Register.*;
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
@RequestMapping("/api")
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
    @PostMapping("/auth/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@RequestBody @Valid RegisterSignUpRequestDto registerSignUpRequestDto){
        return Response.success(registerService.signUp(registerSignUpRequestDto));
    }

    // 로그인 controller
    @PostMapping("/auth/login")
    @ResponseStatus(HttpStatus.OK)
    public Response login(@RequestBody @Valid RegisterLoginRequestDto registerLoginRequestDto){
        return Response.success(registerService.login(registerLoginRequestDto));
    }

    @PostMapping("/username")
    @ResponseStatus(HttpStatus.OK)
    public Response searchUsername(@RequestBody @Valid RegisterSearchUsernameRequestDto registerSearchUsernameRequestDto){
        return Response.success(registerService.searchUsername(registerSearchUsernameRequestDto));
    }

    @PostMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public Response searchUserPassword(@RequestBody @Valid RegisterSearchUserPasswordRequestDto registerSearchUserPasswordRequestDto){
        return Response.success(registerService.searchUserPassword(registerSearchUserPasswordRequestDto));
    }

    @PutMapping("/auth/password")
    @ResponseStatus(HttpStatus.OK)
    public Response changeUserPassword(@RequestBody @Valid RegisterChangeUserPasswordRequestDto registerChangeUserPasswordRequestDto){
        return Response.success(registerService.changeUserPassword(registerChangeUserPasswordRequestDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/auth/reissue")
    public Response reissue(@RequestBody RegisterLoginResponseDto tokenRequestDto) {
        return Response.success(registerService.reissue(tokenRequestDto));
    }
}
