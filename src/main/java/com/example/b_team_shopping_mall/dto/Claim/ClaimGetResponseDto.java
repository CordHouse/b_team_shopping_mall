package com.example.b_team_shopping_mall.dto.Claim;

import com.example.b_team_shopping_mall.entity.Claim;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClaimGetResponseDto {
    private String title;
    private String content;
    private String writer;

    public ClaimGetResponseDto toDto(Claim claim) {
        return new ClaimGetResponseDto(claim.getTitle(), claim.getContent(), claim.getRegister().getName());
    }
}
