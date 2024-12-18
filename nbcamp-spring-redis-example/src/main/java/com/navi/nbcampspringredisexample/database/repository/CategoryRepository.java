package com.navi.nbcampspringredisexample.database.repository;

import com.navi.nbcampspringredisexample.database.entity.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findDistinctAllByOrderByCodeDescNameAsc();
}
