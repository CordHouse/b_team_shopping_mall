package com.example.b_team_shopping_mall.advice;

import com.example.b_team_shopping_mall.exception.*;
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
    public Response claimNotFoundException() {
        return Response.failure(404, "해당 id를 가진 문의글을 찾지 못하였습니다. 다시 한번 확인해주세요");
    }

    @ExceptionHandler(ClaimEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response claimEmptyException() {
        return Response.failure(404, "해당 사용자가 작성한 문의글이 존재하지 않습니다.");
    }


    @ExceptionHandler(RegisterNotFoundIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response registerNotFoundIdException(){
        return Response.failure(404, "해당 아이디는 존재하지 않습니다.");
    }

    @ExceptionHandler(RegisterNotFoundPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response registerNotFoundPasswordException(){
        return Response.failure(404, "비밀번호가 일치하지 않습니다. 비밀번호를 확인해주세요.");
    }

    @ExceptionHandler(CartNotFoundItemListException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response cartNotFoundItemListException(){
        return Response.failure(404, "해당 유저 장바구니는 비어있습니다.");
    }

    @ExceptionHandler(CartNotFoundItemException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response cartNotFoundItemException(){
        return Response.failure(404, "삭제할 품목이 없습니다.");
    }
}