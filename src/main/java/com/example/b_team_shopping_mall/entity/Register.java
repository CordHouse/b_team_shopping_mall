package com.example.b_team_shopping_mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저 이름
    @Column(nullable = false)
    private String username;

    // 유저 아이디 ( Not Null, 중복 x )
    @Column(nullable = false, unique = true)
    private String userid;

    // 유저 패스워드
    @Column(nullable = false)
    private String password;

    // 유저 이메일 ( Not Null, 중복 x )
    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    // 회원가입 생성자 -> id 없이
    public Register(String username, String userid, String password, String email, Authority authority){
        this.username = username;
        this.userid = userid;
        this.password = password;
        this.email = email;
        this.authority = authority;
    }

}
