package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.dto.Category.CategoryGetRequestDto;
import com.example.b_team_shopping_mall.dto.Category.CategoryGetResponseDto;
import com.example.b_team_shopping_mall.dto.Category.CategoryListResponseDto;
import com.example.b_team_shopping_mall.entity.Category;
import com.example.b_team_shopping_mall.exception.CategoryNotFoundException;
import com.example.b_team_shopping_mall.repository.CategoryRepository;
import com.example.b_team_shopping_mall.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service 는 기능을 구현하는 것에 초점을 둔 클래스입니다.
 * Repository 를 불러와서, 데이터베이스에 데이터를 넣거나 혹은 가져와서 기능을 구현합니다.
 * <p>
 * Service 클래스는 Controller 에서 불러와서 사용합니다.
 */

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final RegisterRepository registerRepository;

    // 품목 전체조회
    @Transactional(readOnly = true)
    public List<CategoryListResponseDto> findAll() {
        return categoryRepository.findAll().stream().map(s -> new CategoryListResponseDto().toDto(s)).collect(Collectors.toList());
    }

    // 품목별 조회
    @Transactional(readOnly = true)
    public List<CategoryGetResponseDto> getCategory(CategoryGetRequestDto categoryGetRequestDto) {
        List<Category> category = categoryRepository.findAllByCategory(categoryGetRequestDto.getCategory()).orElseThrow(CategoryNotFoundException::new);
        List<CategoryGetResponseDto> categoryGetResponseDtos = new ArrayList<>();
        category.stream().forEach(i->categoryGetResponseDtos.add(new CategoryGetResponseDto().toDto(i)));
        return categoryGetResponseDtos;
    }
}
