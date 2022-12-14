package com.example.b_team_shopping_mall.dto.Register;

import com.example.b_team_shopping_mall.entity.Register;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSignUpResponseDto {
    private Long id;
    private String username;
    private String name;
    private String email;

    public RegisterSignUpResponseDto toDto(Register register){
        return new RegisterSignUpResponseDto(register.getId(), register.getUsername(), register.getName(), register.getEmail());
    }
}
