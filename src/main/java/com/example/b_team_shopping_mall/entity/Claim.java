package com.example.b_team_shopping_mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    private String title;

    private String content;

    private String writer;

    public Claim(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
