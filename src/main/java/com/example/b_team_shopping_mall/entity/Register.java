package com.example.b_team_shopping_mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
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
    private String name;

    // 유저 아이디 ( Not Null, 중복 x )
    @Column(nullable = false, unique = true)
    private String username;

    // 유저 패스워드
    @Column(nullable = false)
    private String password;

    // 유저 이메일 ( Not Null, 중복 x )
    @Column(nullable = false, unique = true)
    private String email;

    // 유저 권한
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate;

    // 회원가입 생성자 -> id 없이
    public Register(String name, String username, String password, String email, Authority authority){
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authority = authority;
    }

    @PrePersist // DB에 Insert 되기 직전에 실행된다.
    public void createDate(){
        this.createDate = LocalDate.now();
    }

}
