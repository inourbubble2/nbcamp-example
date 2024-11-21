package com.navi.nbcampjavatodotask.api.model.exception;

import lombok.Getter;

@Getter
public abstract class TodoException extends RuntimeException {

    private final TodoErrorCode errorCode;

    public TodoException(TodoErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
