package com.example.b_team_shopping_mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.IdentityHashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; //구매한 상품명 - 제목

    @Column (nullable = false)
    private String option; //상품 옵션(색깔)

    @Column (nullable = false)
    private String content; //상품 리뷰 내용


    //상품 색상, 사이즈, 리뷰 내용을 저장
    public Review (String title, String option, String content){
        this.title = title;
        this.option = option;
        this.content = content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Register register;
}
