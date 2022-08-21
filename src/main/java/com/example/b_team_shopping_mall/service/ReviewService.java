package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.dto.Review.*;
import com.example.b_team_shopping_mall.entity.Register;
import com.example.b_team_shopping_mall.entity.Review;
import com.example.b_team_shopping_mall.exception.ReviewListEmptyException;
import com.example.b_team_shopping_mall.exception.ReviewNotFoundException;
import com.example.b_team_shopping_mall.exception.UserNotCorrectException;
import com.example.b_team_shopping_mall.repository.RegisterRepository;
import com.example.b_team_shopping_mall.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final RegisterRepository registerRepository;

    //리뷰 전체 조회
    @Transactional(readOnly = true)
    public List<ReviewListResponseDto> findAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewListResponseDto> reviewListResponseDtoList = new ArrayList<>();
        reviews.stream().forEach(i -> reviewListResponseDtoList.add(new ReviewListResponseDto().toDto(i)));

        if(reviewListResponseDtoList.isEmpty()) {
            throw new ReviewListEmptyException();
        }

        return reviewListResponseDtoList;
    }

    //리뷰 작성
    @Transactional
    public ReviewCreateResponseDto saveReviews(ReviewCreateRequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Register register = registerRepository.findByUsername(authentication.getName()).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 유저가 존재하지 않습니다.");
        });
        Review review = Review.builder()
                .title(requestDto.getTitle())
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
    @Transactional
    public ReviewEditResponseDto editReviews(Long id, ReviewEditRequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Review review = reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);

        String user = authentication.getName();

        if(!review.getRegister().getUsername().equals(user)) {
            throw new UserNotCorrectException();
        }

        review.setContent(requestDto.getContent());
        review.setScore(requestDto.getScore());

        return new ReviewEditResponseDto().toDto(review);
    }

    //리뷰 삭제
    @Transactional
    public void deleteReviews(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Register register = registerRepository.findByUsername(authentication.getName()).orElseThrow();
        Review review = reviewRepository.findById(id).orElseThrow();
        Register reviewWriter = review.getRegister();
        if(reviewWriter.equals(register))
            reviewRepository.deleteById(id);
        else
            throw new ReviewNotFoundException();
    }
}