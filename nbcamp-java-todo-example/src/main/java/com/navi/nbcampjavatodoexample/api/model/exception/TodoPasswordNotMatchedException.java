package com.navi.nbcampjavatodotask.api.model.exception;

public class TodoPasswordNotMatchedException extends TodoException {

    public TodoPasswordNotMatchedException() {
        super(TodoErrorCode.PASSWORD_NOT_MATCHED);
    }
}
