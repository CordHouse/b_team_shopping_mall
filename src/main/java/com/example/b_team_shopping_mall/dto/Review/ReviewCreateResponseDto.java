package com.example.b_team_shopping_mall.dto.Review;

import com.example.b_team_shopping_mall.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateResponseDto {
    private String title;
    private String itemcolor;
    private String itemsize;
    private Long score;
    private String content;
    private String writer;


    public ReviewCreateResponseDto toDto(Review review) {
        return new ReviewCreateResponseDto(review.getTitle(), review.getItemcolor(), review.getItemsize(),
                review.getScore(), review.getContent(), review.getRegister().getUsername());
    }
}
