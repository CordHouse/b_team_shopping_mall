package com.example.b_team_shopping_mall.dto.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryGetRequestDto {
    @NotBlank(message = "품목을 선택해주세요.")
    private String category;

}
