package com.example.b_team_shopping_mall.dto.Review;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data

public class ReviewEditRequestDto {

    @NotBlank(message = "평점을 매겨주세요.")
    private String score;

    @NotBlank(message = "수정하실 내용을 입력해주세요.")
    private String content;
}
