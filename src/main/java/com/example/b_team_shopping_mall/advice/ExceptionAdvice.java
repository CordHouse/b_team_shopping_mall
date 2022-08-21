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

    @ExceptionHandler(RegisterOverlapException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response registerOverlapException(){
        return Response.failure(404, "사용할 수 없는 아이디입니다.");
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

    @ExceptionHandler(RegisterNotFoundSearchEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response registerNotFoundSearchEmailException(){
        return Response.failure(404, "이메일이 일치하지 않습니다.");
    }

    @ExceptionHandler(RegisterNotFoundSearchUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response registerNotFoundSearchUsernameException(){
        return Response.failure(404, "가입한 정보가 없습니다.");
    }

    @ExceptionHandler(RegisterNotFoundSearchUserPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response registerNotFoundSearchUserPasswordException(){
        return Response.failure(404, "일치하는 회원정보가 없습니다.");
    }

    @ExceptionHandler(CartNotFoundItemListException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response cartNotFoundItemListException(){
        return Response.failure(404, "해당 유저 장바구니는 비어있습니다.");
    }

    @ExceptionHandler(CartOverlapException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response cartOverlapException(){
        return Response.failure(404, "이미 담은 물품입니다.");
    }

    @ExceptionHandler(ProductNotFoundItemException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response productNotFoundItemException(){
        return Response.failure(404, "해당 품목이 존재하지 않습니다.");
    }

    @ExceptionHandler(CartNotFoundItemException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response cartNotFoundItemException(){
        return Response.failure(404, "삭제할 품목이 없습니다.");
    }

    @ExceptionHandler(ProductListEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response productListEmptyException() {
        return Response.failure(404, "품목이 존재하지 않습니다.");
    }

    @ExceptionHandler(CategoryProductListEmptyException.class)
    @ResponseStatus(HttpStatus.OK)
    public Response categoryProductListEmptyException() {
        return Response.failure(404, "해당 카테고리에 해당하는 품목이 존재하지 않습니다.");
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public Response productNotFoundException() {
        return Response.failure(404, "해당 ID에 해당하는 품목이 존재하지 않습니다.");
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response categoryNotFoundException() {
        return Response.failure(404, "해당 카테고리를 찾지 못하였습니다.");
    }

    @ExceptionHandler(CategoryListEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response categoryListEmptyException() {
        return Response.failure(404, "카테고리가 존재하지 않습니다.");
    }

    @ExceptionHandler(ReviewListEmptyException.class)
    @ResponseStatus(HttpStatus.OK)
    public Response reviewListEmptyException() {
        return Response.failure(404, "리뷰가 존재하지 않습니다.");
    }

    @ExceptionHandler(UserNotCorrectException.class)
    @ResponseStatus(HttpStatus.OK)
    public Response userNotCorrectException() {
        return Response.failure(404, "현재 로그인한 사용자와 작성자가 일치하지 않습니다.");
    }
}