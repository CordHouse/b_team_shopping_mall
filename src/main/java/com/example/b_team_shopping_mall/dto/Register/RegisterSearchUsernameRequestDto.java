package com.example.b_team_shopping_mall.dto.Register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterSearchUsernameRequestDto {
    @NotBlank(message = "가입하신분의 이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "가입했던 이메일을 입력해주세요.")
    private String email;
}
