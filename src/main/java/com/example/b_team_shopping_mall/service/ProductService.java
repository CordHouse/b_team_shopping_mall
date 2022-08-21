package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.dto.Product.ProductCategoryListDto;
import com.example.b_team_shopping_mall.dto.Product.ProductCreateRequestDto;
import com.example.b_team_shopping_mall.dto.Product.ProductCreateResponseDto;
import com.example.b_team_shopping_mall.dto.Product.ProductListDto;
import com.example.b_team_shopping_mall.entity.Category;
import com.example.b_team_shopping_mall.entity.Product;
import com.example.b_team_shopping_mall.entity.Register;
import com.example.b_team_shopping_mall.exception.*;
import com.example.b_team_shopping_mall.repository.CategoryRepository;
import com.example.b_team_shopping_mall.repository.ProductRepository;
import com.example.b_team_shopping_mall.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RegisterRepository registerRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<ProductListDto> getProducts() {
        List<Product> lst = productRepository.findAll();

        if(lst.isEmpty()) {
            throw new ProductListEmptyException();
        }

        return lst.stream().map(s -> new ProductListDto().toDto(s)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductCategoryListDto> getProduct(String category) {

        Category findCategory = categoryRepository.findByCategory(category)
                .orElseThrow(CategoryNotFoundException::new);

        List<Product> lst = productRepository.findAllByCategory(findCategory)
                .orElseThrow(CategoryProductListEmptyException::new);

        if(lst.isEmpty()) {
            throw new ProductListEmptyException();
        }

        return lst.stream().map(s -> new ProductCategoryListDto().toDto(s)).collect(Collectors.toList());
    }

    @Transactional
    public ProductCreateResponseDto save(ProductCreateRequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Register register = registerRepository.findByUsername(authentication.getName())
                .orElseThrow(RegisterNotFoundSearchUsernameException::new);
        Category category = categoryRepository.findByCategory(requestDto.getCategory())
                .orElseThrow(CategoryNotFoundException::new);

        Product product = Product.builder()
                .item(requestDto.getItem())
                .price(requestDto.getPrice())
                .register(register)
                .category(category)
                .build();

        productRepository.save(product);

        return new ProductCreateResponseDto().toDto(product);
    }

    @Transactional
    public void deleteCategory(String category) {

        Category findCategory = categoryRepository.findByCategory(category)
                .orElseThrow(CategoryNotFoundException::new);

        List<Product> lst = productRepository.findAllByCategory(findCategory)
                .orElseThrow(CategoryProductListEmptyException::new);

        productRepository.deleteAllByCategory(findCategory);
    }

    @Transactional
    public void deleteProducts() {
        List<Product> lst = productRepository.findAll();

        if(lst.isEmpty()) {
            throw new ProductListEmptyException();
        }

        productRepository.deleteAll();
    }

    @Transactional
    public void deleteId(Long id) {
        Product lst = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        productRepository.deleteById(id);
    }
}