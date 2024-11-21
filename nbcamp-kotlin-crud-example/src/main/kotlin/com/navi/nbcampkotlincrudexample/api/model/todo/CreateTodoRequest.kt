package com.navi.nbcampkotlincrudpractice.api.model.todo

data class CreateTodoRequest(
    val title: String,
    val content: String,
    val createdById: Long
)
