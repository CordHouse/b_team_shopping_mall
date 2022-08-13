package com.example.b_team_shopping_mall.dto.Claim;

import com.example.b_team_shopping_mall.entity.Claim;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimEditResponseDto {
    private String title;
    private String content;
    private String writer;

    public ClaimEditResponseDto toDto(Claim claim) {
        return new ClaimEditResponseDto(claim.getTitle(), claim.getContent(), claim.getRegister().getName());
    }
}
