package com.example.b_team_shopping_mall.controller;

import com.example.b_team_shopping_mall.entity.Register;
import com.example.b_team_shopping_mall.response.Response;
import com.example.b_team_shopping_mall.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/")
public class RegisterController {
    private final RegisterService registerService;

    // 회원가입 controller
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@RequestBody Register register){
        return Response.success(registerService.signUp(register));
    }

    // 로그인 controller
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Response login(@RequestBody Register register){
        return Response.success(registerService.login(register));
    }
}
