package com.example.b_team_shopping_mall.repository;

import com.example.b_team_shopping_mall.entity.Category;
import com.example.b_team_shopping_mall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findAllByCategory(Category category);

    Optional<Product> findByItem(String Item);

    void deleteByCategory(Category category);
}