package com.example.b_team_shopping_mall.dto.Register;

import com.example.b_team_shopping_mall.entity.Register;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSearchUsernameResponseDto {
    private Long id;
    private String username;

    public RegisterSearchUsernameResponseDto toDto(Register register){
        return new RegisterSearchUsernameResponseDto(register.getId(), register.getUsername());
    }
}
