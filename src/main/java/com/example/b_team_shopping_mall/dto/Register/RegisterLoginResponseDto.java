package com.example.b_team_shopping_mall.dto.Register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterLoginResponseDto {
    private String token;

    public RegisterLoginResponseDto toDto(String token){
        this.token = token;
        return new RegisterLoginResponseDto(getToken());
    }
}
