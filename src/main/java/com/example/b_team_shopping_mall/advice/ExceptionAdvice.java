package com.example.b_team_shopping_mall.advice;

import com.example.b_team_shopping_mall.exception.ClaimNotFoundException;
import com.example.b_team_shopping_mall.exception.RegisterNotFoundIdException;
import com.example.b_team_shopping_mall.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return Response.failure(400, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(ClaimNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response boardNotFoundException() {
        return Response.failure(404, "해당 id를 가진 문의글을 찾지 못하였습니다. 다시 한번 확인해주세요");
    }

    @ExceptionHandler(RegisterNotFoundIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response registerNotFoundIdException(){
        return Response.failure(404, "아이디와 비밀번호가 일치하지 않습니다. 비밀번호를 확인해주세요.");
    }
}