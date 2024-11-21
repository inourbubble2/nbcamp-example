package com.navi.nbcampjavatodotask.api.service;

import com.navi.nbcampjavatodotask.api.model.exception.TodoNotFoundException;
import com.navi.nbcampjavatodotask.api.model.exception.TodoPasswordNotMatchedException;
import com.navi.nbcampjavatodotask.database.entity.Todo;
import com.navi.nbcampjavatodotask.database.repository.TodoRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo createTodo(
        String title,
        String content,
        String username,
        String password,
        LocalDateTime createdAt
    ) {
        Todo todo = Todo.builder()
            .title(title)
            .content(content)
            .username(username)
            .password(password)
            .createdAt(createdAt)
            .build();

        return todoRepository.save(todo);
    }

    public List<Todo> getTodos() {
        return todoRepository.findAllByOrderByCreatedAtDesc();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow(TodoNotFoundException::new);
    }

    public Todo updateTodo(
        Long id,
        String title,
        String content,
        String username,
        String password
    ) {
        Todo todo = getTodoById(id);

        if (!todo.getPassword().equals(password)) {
            throw new TodoPasswordNotMatchedException();
        }

        todo.update(title, content, username);

        return todoRepository.save(todo);
    }

    public void deleteTodo(
        Long id,
        String password
    ) {

        Todo todo = getTodoById(id);

        if (!todo.getPassword().equals(password)) {
            throw new TodoPasswordNotMatchedException();
        }

        todoRepository.delete(todo);
    }
}
