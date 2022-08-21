package com.example.b_team_shopping_mall.controller;

import com.example.b_team_shopping_mall.dto.Product.ProductCreateRequestDto;
import com.example.b_team_shopping_mall.response.Response;
import com.example.b_team_shopping_mall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/items")
    @ResponseStatus(HttpStatus.OK) // 모든 품목들을 조회
    public Response getProducts() {
        return Response.success(productService.getProducts());
    }

    @GetMapping("/items/{category}") // 특정 부류에 해당하는 제품들을 조회
    @ResponseStatus(HttpStatus.OK)
    public Response getCategories(@PathVariable String category) {
        return Response.success(productService.getProduct(category));
    }

    @PostMapping("/auth/items")
    @ResponseStatus(HttpStatus.CREATED) // 품목을 저장
    public Response save(@RequestBody @Valid ProductCreateRequestDto requestDto) {
        return Response.success(productService.save(requestDto));
    }

    @DeleteMapping("/items/{category}") // 특정 부류에 해당하는 제품들을 삭제
    @ResponseStatus(HttpStatus.OK)
    public Response deleteCategory(@PathVariable String category) {
        productService.deleteCategory(category);
        return Response.success( category + "부류에 해당되는 품목들이 삭제 되었습니다.");
    }

    @DeleteMapping("/item/{id}") // 특정 아이디에 해당하는 제품들을 삭제
    @ResponseStatus(HttpStatus.OK)
    public Response deleteId(@PathVariable Long id) {
        productService.deleteId(id);
        return Response.success("id == " + id + " 인 품목이 삭제되었습니다.");
    }

    @DeleteMapping("/items")
    @ResponseStatus(HttpStatus.OK) // 모든 품목들을 삭제
    public Response deleteProducts() {
        productService.deleteProducts();
        return Response.success("모든 품목들이 삭제되었습니다.");
    }
}
