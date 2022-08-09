package com.example.b_team_shopping_mall.controller;

import com.example.b_team_shopping_mall.entity.Review;
import com.example.b_team_shopping_mall.response.Response;
import com.example.b_team_shopping_mall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    //리뷰 전체 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/reviews")
    public Response getBoards() {
        return Response.success(reviewService.findAll());
    }

    //리뷰 작성
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/api/reviews")
    public Response save(@RequestBody Review review) {
        return Response.success(reviewService.save(review));
    }

    //리뷰 수정
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/review/{id}")
    public Response editBoard(@PathVariable("id") Long id, @RequestBody Review review) {
        return Response.success(reviewService.edit(id, review));
    }

    //리뷰 삭제
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/review/{id}")
    public Response deleteBoard(@PathVariable("id") Long id) {
        reviewService.delete(id);
        return Response.success("리뷰를 삭제하셨습니다.");
    }
}