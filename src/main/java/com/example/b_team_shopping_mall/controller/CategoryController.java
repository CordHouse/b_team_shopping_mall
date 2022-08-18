package com.example.b_team_shopping_mall.controller;

import com.example.b_team_shopping_mall.response.Response;
import com.example.b_team_shopping_mall.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller 는 클라이언트(사용자)의 요청을 받는 클래스입니다.
 * Controller에서는 서비스 클래스 (기능 구현된 클래스) 를 불러서 사용자의 요청을 처리합니다.
 *
 * @Controller 는 템플릿 엔진(JSP, Thymeleaf 등등) 사용할 때 주로 쓰이고,
 * @RestController 는 API 서버를 만들 때 주로 사용됩니다. 저희는 API 서버를 만드니 RestController 로 진행합니다.
 */


public class CategoryController {
    private CategoryService categoryService;

    // 품목 전체 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/categories/items")
    public Response getCategories() { return Response.success(categoryService.getCategories()); }

    // 품목별 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/categories/items/{category}")
    public Response getCategory(@PathVariable String category) { return Response.success(categoryService.getCategory(category)); }

}
