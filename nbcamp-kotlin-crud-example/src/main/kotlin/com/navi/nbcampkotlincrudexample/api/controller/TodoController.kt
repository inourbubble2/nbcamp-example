package com.navi.nbcampkotlincrudpractice.api.controller

import com.navi.nbcampkotlincrudpractice.api.model.todo.CreateTodoRequest
import com.navi.nbcampkotlincrudpractice.api.service.TodoService
import com.navi.nbcampkotlincrudpractice.database.entity.Todo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/todos")
class TodoController(
    private val todoService: TodoService
) {
    @PostMapping
    fun create(
        @RequestBody request: CreateTodoRequest,
    ): Todo =
        todoService.create(
            title = request.title,
            content = request.content,
            createdById = request.createdById,
            createdAt = LocalDateTime.now(),
        )

    @GetMapping
    fun findAll(): List<Todo> = todoService.findAll()

    @GetMapping("/{id}")
    fun findById(
        @PathVariable("id") id: Long,
    ): Todo = todoService.findById(id)
}
