package com.example.b_team_shopping_mall.dto;

import com.example.b_team_shopping_mall.entity.Claim;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ClaimListResponseDto {
    private String title;
    private String content;
    private String writer;

    public ClaimListResponseDto toDto(Claim claim) {
        return new ClaimListResponseDto(claim.getTitle(), claim.getContent(), claim.getRegister().getMembername());
    }
}
