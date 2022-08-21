package com.example.b_team_shopping_mall.entity;

import lombok.AllArgsConstructor;import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id; //저장된 상품. 고유 아이디

    @Column(nullable = false, unique = true)
    private String item; //상품명

    @Column(nullable = false)
    private int count; //상품 개수

    @Column(nullable = false)
    private int price; //상품 가격

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Register register; //유저정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
}
