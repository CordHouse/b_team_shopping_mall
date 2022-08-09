package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.entity.Board;
import com.example.b_team_shopping_mall.entity.Review;
import com.example.b_team_shopping_mall.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service

public class ReviewService {
    private final ReviewRepository reviewRepository;

    //리뷰 전체 조회
    @Transactional(readOnly = true)
    public List<Review> findAll() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews;
    }

    //리뷰 작성
    @Transactional
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    //리뷰 수정
    @Transactional(readOnly = true)
    public Review edit(Long id, Review updateReview) {
        Review review = reviewRepository.findById(id).get();

        //상품명과 옵션은 수정 불가능
        review.setContent(updateReview.getContent());
        return review;
    }

    //리뷰 삭제
    @Transactional
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}