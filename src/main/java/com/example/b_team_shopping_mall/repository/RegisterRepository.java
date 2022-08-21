package com.example.b_team_shopping_mall.repository;

import com.example.b_team_shopping_mall.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// 회원가입 JPA -> Register/ Long
public interface RegisterRepository extends JpaRepository<Register, Long> {
    Optional<Register> findByUsername(String username);
    Optional<Register> findByUsernameAndEmail(String username, String email);
    List<Register> findAllByUsername(String username);

    Optional<List<Register>> findAllByName(String name);
}
