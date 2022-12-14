package com.example.b_team_shopping_mall.dto.Register;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterChangeUserPasswordRequestDto {
    @NotBlank(message = "임시 비밀번호를 입력해주세요.")
    private String beforePassword;
    @NotBlank(message = "변경하실 비밀번호를 입력해주세요.")
    private String afterPassword;
}
