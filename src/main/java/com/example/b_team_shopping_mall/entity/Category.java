package com.example.b_team_shopping_mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@AllArgsConstructor //빈생성자
@NoArgsConstructor //모든 인스턴스를 받음
@Entity

public class Category { // 품목조회
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String item; // 물품명

    @Column (nullable = false)
    private String category; // 품목명

    @Column (nullable = false)
    private int price; // 물품 가격

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Register register; // 유저 정보
}
