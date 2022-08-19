package com.example.b_team_shopping_mall.dto.Register;

import com.example.b_team_shopping_mall.entity.Register;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSearchUserPasswordResponseDto {
    private Long id;
    private String password;

    public RegisterSearchUserPasswordResponseDto toDto(Register register){
        return new RegisterSearchUserPasswordResponseDto(register.getId(), register.getPassword());
    }
}
