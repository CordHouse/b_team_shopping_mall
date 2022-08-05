package com.example.b_team_shopping_mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id; //저장된 상품. 고유 아이디

    private  String object; //상품명

    private  int count; //상품 개수

    private int price; //상품 가격

}
