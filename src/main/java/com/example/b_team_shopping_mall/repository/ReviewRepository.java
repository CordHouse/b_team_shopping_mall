package com.example.b_team_shopping_mall.repository;

import com.example.b_team_shopping_mall.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>{
}