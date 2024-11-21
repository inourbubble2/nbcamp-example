package com.navi.nbcampbasicquerydslexample.controller;

import com.navi.nbcampbasicquerydslexample.entity.Product;
import com.navi.nbcampbasicquerydslexample.repository.ProductQueryRepositoryImpl;
import com.navi.nbcampbasicquerydslexample.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;


    @GetMapping("/products")
    public List<Product> getPaginatedProducts(@RequestParam int page, @RequestParam int size) {
        return productRepository.getPaginatedProducts(page, size);
    }

    @GetMapping("/products/stores/{storeId}")
    public List<Product> getProductsByStoreId(@PathVariable Long storeId) {
        return productRepository.getSortedProductsOf(storeId);
    }

    @GetMapping("/products/searched")
    public List<Product> getSearchedProducts(@RequestParam long storeId, @RequestParam int price) {
        return productRepository.getProductsUnderPrice(storeId, price);
    }

}
