package com.example.b_team_shopping_mall.dto.Review;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@AllArgsConstructor
@Data

public class ReviewCreateRequestDto {
    private String userid; //사용자 아이디

    @NotBlank(message = "구매하신 상품명을 입력해주세요.")
    private String item;

    @NotBlank(message = "상품의 색상을 입력해주세요.")
    private String itemcolor;

    @NotBlank(message = "상품의 사이즈를 입력해주세요.")
    private String itemsize;

    @NotBlank(message = "평점을 매겨주세요.")
    private Long score;

    @NotBlank(message = "상품을 리뷰해주세요!")
    private String content;
}
