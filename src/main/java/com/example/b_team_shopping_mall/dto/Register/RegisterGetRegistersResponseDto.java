package com.example.b_team_shopping_mall.dto.Register;

import com.example.b_team_shopping_mall.entity.Register;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterGetRegistersResponseDto {
    private Long id;
    private String username;

    public RegisterGetRegistersResponseDto toDto(Register register){
        return new RegisterGetRegistersResponseDto(register.getId(), register.getUsername());
    }
}
