package com.navi.nbcampkotlincrudpractice.api.service

import com.navi.nbcampkotlincrudpractice.database.entity.Todo
import com.navi.nbcampkotlincrudpractice.database.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val memberService: MemberService,
) {
    fun create(
        title: String,
        content: String,
        createdById: Long,
        createdAt: LocalDateTime,
    ): Todo {
        val createdBy = memberService.findById(createdById)

        return todoRepository.save(
            Todo(
                title = title,
                content = content,
                completed = false,
                createdBy = createdBy,
                createdAt = createdAt,
            ),
        )
    }

    fun findAll(): List<Todo> = todoRepository.findAll()

    fun findById(id: Long): Todo = todoRepository.findByIdOrNull(id) ?: throw IllegalStateException("Todo not found.")
}
