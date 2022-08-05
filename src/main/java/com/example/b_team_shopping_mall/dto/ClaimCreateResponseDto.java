package com.example.b_team_shopping_mall.dto;

import com.example.b_team_shopping_mall.entity.Claim;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimCreateResponseDto {
    private String title;
    private String content;
    private String writer;

    public ClaimCreateResponseDto toDto(Claim claim) {
        return new ClaimCreateResponseDto(claim.getTitle(), claim.getContent(), claim.getWriter());
    }
}
