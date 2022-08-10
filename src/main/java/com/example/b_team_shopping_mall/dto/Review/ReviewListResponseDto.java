package com.example.b_team_shopping_mall.dto.Review;

import com.example.b_team_shopping_mall.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class ReviewListResponseDto {
    private String item;
    private String itemcolor;
    private String itemsize;
    private Long score;
    private String content;
    private String writer;

    public ReviewListResponseDto toDto(Review review) {
        return new ReviewListResponseDto(review.getItem(), review.getItemcolor(), review.getItemsize(),
                review.getScore(), review.getContent(), review.getRegister().getUsername());
    }
}
