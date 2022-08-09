package com.example.b_team_shopping_mall.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String item; //구매한 상품명 - 제목

    @Column (nullable = false)
    private String itemcolor; //상품 색상

    @Column (nullable = false)
    private String itemsize; //상품 사이즈 (free 혹은 숫자)

    @Column (nullable = false)
    private Long score; //평점

    @Column (nullable = false)
    private String content; //상품 리뷰 내용


    //상품명, 색상, 사이즈, 리뷰 내용을 저장
    public Review(String item, String itemcolor, String itemsize, Long score, String content){
        this.item = item;
        this.itemcolor = itemcolor;
        this.itemsize = itemsize;
        this.score = score;
        this.content = content;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Register register;
}
