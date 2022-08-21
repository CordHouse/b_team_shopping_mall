package com.example.b_team_shopping_mall.dto.Register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAdminRequestDto {
    @NotBlank(message = "권한을 주고 싶은 계정의 아이디를 입력해주세요.")
    private String username;
}
