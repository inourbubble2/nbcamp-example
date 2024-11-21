package com.navi.nbcampjavatodotask.api.controller;

import com.navi.nbcampjavatodotask.api.model.SimplifiedTodoResponse;
import com.navi.nbcampjavatodotask.api.model.TodoCreateRequest;
import com.navi.nbcampjavatodotask.api.model.TodoDeleteRequest;
import com.navi.nbcampjavatodotask.api.model.TodoResponse;
import com.navi.nbcampjavatodotask.api.model.TodoUpdateRequest;
import com.navi.nbcampjavatodotask.api.service.TodoService;
import com.navi.nbcampjavatodotask.database.entity.Todo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


    @Operation(summary = "일정 생성")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Created the todo.")
    })
    @PostMapping
    public TodoResponse createTodo(@RequestBody @Valid TodoCreateRequest request) {
        Todo todo = todoService.createTodo(
            request.title(),
            request.content(),
            request.username(),
            request.password(),
            LocalDateTime.now()
        );

        return TodoResponse.of(todo);
    }

    @Operation(summary = "일정 목록 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fetched the todo.")
    })
    @GetMapping
    public List<SimplifiedTodoResponse> getTodos() {
        return todoService.getTodos()
            .stream()
            .map(todo -> new SimplifiedTodoResponse(todo.getId(), todo.getTitle(), todo.getCreatedAt()))
            .toList();
    }

    @Operation(summary = "일정 단일 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fetched the todo."),
        @ApiResponse(responseCode = "400", description = "Todo not found."),
    })
    @GetMapping("/{id}")
    public TodoResponse getTodo(@PathVariable("id") Long id) {
        Todo todo = todoService.getTodoById(id);
        return TodoResponse.of(todo);
    }

    @Operation(summary = "일정 수정")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated the todo."),
        @ApiResponse(responseCode = "400", description = "Todo not found."),
        @ApiResponse(responseCode = "401", description = "Password not matched.")
    })
    @PatchMapping("/{id}")
    public TodoResponse updateTodo(
        @PathVariable("id") Long id,
        @RequestBody @Valid TodoUpdateRequest request
    ) {
        Todo todo = todoService.updateTodo(
            id,
            request.title(),
            request.content(),
            request.username(),
            request.password()
        );
        return TodoResponse.of(todo);
    }

    @Operation(summary = "일정 삭제")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Deleted the todo."),
        @ApiResponse(responseCode = "400", description = "Todo not found."),
        @ApiResponse(responseCode = "401", description = "Password not matched.")
    })
    @DeleteMapping("/{id}")
    public void deleteTodo(
        @PathVariable("id") Long id,
        @RequestBody @Valid TodoDeleteRequest request
    ) {
        todoService.deleteTodo(id, request.password());
    }
}
