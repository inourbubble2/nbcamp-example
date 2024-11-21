package com.navi.nbcampkotlincrudpractice.database.repository

import com.navi.nbcampkotlincrudpractice.database.entity.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long>
