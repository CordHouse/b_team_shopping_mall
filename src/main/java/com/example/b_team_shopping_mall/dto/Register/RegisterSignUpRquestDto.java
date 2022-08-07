package com.example.b_team_shopping_mall.dto.Register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSignUpRquestDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String memberid;

    @NotBlank(message = "성함을 입력해주세요.")
    private String membername;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String memberpassword;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String memberemail;
}
