package com.example.b_team_shopping_mall.controller;

import com.example.b_team_shopping_mall.dto.Category.CategoryCreateRequestDto;
import com.example.b_team_shopping_mall.response.Response;
import com.example.b_team_shopping_mall.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    @ResponseStatus(HttpStatus.OK) // 전체 카테고리 조회
    public Response categories() {
        return Response.success(categoryService.findAll());
    }

    @GetMapping("/category/{id}")
    @ResponseStatus(HttpStatus.OK) // 단일 카테고리 조회
    public Response getCategory(@PathVariable Long id) {
        return Response.success(categoryService.findCategory(id));
    }

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED) // 카테고리 생성
    public Response createCategory(@RequestBody @Valid CategoryCreateRequestDto requestDto) {
        return Response.success(categoryService.save(requestDto));
    }

    @DeleteMapping("/category/{id}")
    @ResponseStatus(HttpStatus.OK) // 특정 카테고리 삭제
    public Response delete(@PathVariable Long id) {
        categoryService.delete(id);
        return Response.success("id = " + id + "인 부류가 삭제되었습니다.");
    }
}
