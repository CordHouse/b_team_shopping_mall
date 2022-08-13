package com.example.b_team_shopping_mall.controller;

import com.example.b_team_shopping_mall.dto.Review.ReviewCreateRequestDto;
import com.example.b_team_shopping_mall.dto.Review.ReviewEditRequestDto;
import com.example.b_team_shopping_mall.response.Response;
import com.example.b_team_shopping_mall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class ReviewController {
    private final ReviewService reviewService;

    //리뷰 전체 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/reviews")
    public Response getReviews() {
        return Response.success(reviewService.findAllReviews());
    }

    //리뷰 작성
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reviews")
    public Response saveReviews(@RequestBody @Valid ReviewCreateRequestDto requestDto) {
        return Response.success(reviewService.saveReviews(requestDto));
    }

    //리뷰 수정
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/reviews/{id}")
    public Response editReviews(@PathVariable("id") Long id, @RequestBody @Valid ReviewEditRequestDto requestDto) {
        return Response.success(reviewService.editReviews(id, requestDto));
    }

    //리뷰 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/reviews/{id}")
    public Response deleteReviews(@PathVariable("id") Long id) {
        reviewService.deleteReviews(id);
        return Response.success("리뷰를 삭제하셨습니다.");
    }
}