package com.navi.nbcampspringredisexample.api.controller;

import com.navi.nbcampspringredisexample.api.service.CategoryService;
import com.navi.nbcampspringredisexample.database.entity.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/api/categories1")
    public List<Category> getAllCategories1() {
        return categoryService.getAllCategories1();
    }

    @GetMapping("/api/categories2")
    public List<Category> getAllCategories2() {
        return categoryService.getAllCategories2();
    }

    @GetMapping("/api/categories3")
    public List<Category> getAllCategories3() {
        return categoryService.getAllCategories3();
    }
}
