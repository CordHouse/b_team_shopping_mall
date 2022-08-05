package com.example.b_team_shopping_mall.repository;

import com.example.b_team_shopping_mall.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

// 회원가입 JPA -> Register/ Long
public interface RegisterRepository extends JpaRepository<Register, Long> {
}
