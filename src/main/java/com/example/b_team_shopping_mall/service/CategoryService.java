package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.dto.Category.CategoryCreateRequestDto;
import com.example.b_team_shopping_mall.dto.Category.CategoryCreateResponseDto;
import com.example.b_team_shopping_mall.dto.Category.CategoryFoundResponseDto;
import com.example.b_team_shopping_mall.dto.Category.CategoryListResponseDto;
import com.example.b_team_shopping_mall.entity.Category;
import com.example.b_team_shopping_mall.exception.CategoryListEmptyException;
import com.example.b_team_shopping_mall.exception.CategoryNotFoundException;
import com.example.b_team_shopping_mall.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryListResponseDto> findAll() {
        List<Category> lst = categoryRepository.findAll();

        if(lst.isEmpty()) {
            throw new CategoryListEmptyException();
        }

        return lst.stream().map(s -> new CategoryListResponseDto().toDto(s)).collect(Collectors.toList());
    }

    public CategoryFoundResponseDto findCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        return new CategoryFoundResponseDto().toDto(category);
    }

    public CategoryCreateResponseDto save(CategoryCreateRequestDto requestDto) {
        Category createCategory = new Category(requestDto.getCategory());

        categoryRepository.save(createCategory);

        return new CategoryCreateResponseDto().toDto(createCategory);
    }

    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        categoryRepository.deleteById(id);
    }
}
