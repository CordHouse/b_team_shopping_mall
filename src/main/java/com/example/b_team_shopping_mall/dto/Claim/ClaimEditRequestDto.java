package com.example.b_team_shopping_mall.dto.Claim;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ClaimEditRequestDto {

    @NotBlank(message = "수정하실 문의 제목을 입력해주세요")
    private String title;

    @NotBlank(message = "수정하실 문의 내용을 입력해주세요")
    private String content;
}
