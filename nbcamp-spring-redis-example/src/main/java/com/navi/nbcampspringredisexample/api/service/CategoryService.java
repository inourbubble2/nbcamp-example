package com.navi.nbcampspringredisexample.api.service;

import com.navi.nbcampspringredisexample.database.entity.Category;
import com.navi.nbcampspringredisexample.database.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories1() {
        return categoryRepository.findDistinctAllByOrderByCodeDescNameAsc();
    }

    public List<Category> getAllCategories2() {
        return Optional.ofNullable((List<Category>) redisTemplate.opsForValue().get("categories2"))
            .orElseGet(() -> {
                List<Category> categories = categoryRepository.findDistinctAllByOrderByCodeDescNameAsc();

                redisTemplate.opsForValue().set("categories2", categories);
                return categories;
            });
    }

    @Cacheable(value = "categories3")
    public List<Category> getAllCategories3() {
        return categoryRepository.findDistinctAllByOrderByCodeDescNameAsc();
    }
}
