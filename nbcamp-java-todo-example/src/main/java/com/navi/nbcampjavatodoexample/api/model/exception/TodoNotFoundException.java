package com.navi.nbcampjavatodotask.api.model.exception;

public class TodoNotFoundException extends TodoException {

    public TodoNotFoundException() {
        super(TodoErrorCode.TODO_NOT_FOUND);
    }
}
