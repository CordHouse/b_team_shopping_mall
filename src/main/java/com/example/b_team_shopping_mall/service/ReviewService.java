package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.dto.Review.*;
import com.example.b_team_shopping_mall.entity.Register;
import com.example.b_team_shopping_mall.entity.Review;
import com.example.b_team_shopping_mall.exception.ReviewNotFoundException;
import com.example.b_team_shopping_mall.repository.RegisterRepository;
import com.example.b_team_shopping_mall.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service

public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RegisterRepository registerRepository;

    //리뷰 전체 조회
    @Transactional(readOnly = true)
    public List<ReviewListResponseDto> findAll() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewListResponseDto> reviewListResponseDtoList = new ArrayList<>();
        reviews.stream().forEach(i -> reviewListResponseDtoList.add(new ReviewListResponseDto().toDto(i)));
        return reviewListResponseDtoList;
    }

    //리뷰 작성
    @Transactional
    public ReviewCreateResponseDto save(ReviewCreateRequestDto requestDto) {
        Register register = registerRepository.findByuserid(requestDto.getUserid());
        Review review = Review.builder()
                .item(requestDto.getItem())
                .itemcolor(requestDto.getItemcolor())
                .itemsize(requestDto.getItemsize())
                .score(requestDto.getScore())
                .content(requestDto.getContent())
                .register(register)
                .build();


        reviewRepository.save(review);
        return new ReviewCreateResponseDto().toDto(review);
    }

    //리뷰 수정
    @Transactional(readOnly = true)
    public ReviewEditResponseDto edit(Long id, ReviewEditRequestDto requestDto) {
        Review findReview = reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);

        //상품명과 옵션(색상, 사이즈)은 수정 불가능
        findReview.setScore(requestDto.getScore());
        findReview.setContent(requestDto.getContent());

        return new ReviewEditResponseDto().toDto(findReview);
    }

    //리뷰 삭제
    @Transactional
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}