package com.navi.nbcampjavatodotask.database.repository;

import com.navi.nbcampjavatodotask.database.entity.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByOrderByCreatedAtDesc();
    
}
