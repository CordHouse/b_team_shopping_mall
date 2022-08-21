package com.example.b_team_shopping_mall.dto.Register;

import com.example.b_team_shopping_mall.entity.Register;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAdminResponseDto {
    private String username;
    private String role;

    public RegisterAdminResponseDto toDto(Register register){
        return new RegisterAdminResponseDto(register.getUsername(), register.getAuthority().toString());
    }
}
