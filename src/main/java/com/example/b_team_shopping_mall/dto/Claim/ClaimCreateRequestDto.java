package com.example.b_team_shopping_mall.dto.Claim;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
public class ClaimCreateRequestDto {
    @NotBlank(message = "문의하실 사항의 제목을 입력해주세요")
    private String title;

    @NotBlank(message = "문의하실 내용을 입력해주세요")
    private String content;
}
