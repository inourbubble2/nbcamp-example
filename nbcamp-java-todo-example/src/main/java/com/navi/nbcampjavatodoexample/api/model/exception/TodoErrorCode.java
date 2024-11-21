package com.navi.nbcampjavatodotask.api.model.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum TodoErrorCode {
    PASSWORD_NOT_MATCHED(HttpStatus.UNAUTHORIZED, "패스워드가 불일치합니다."),
    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "투두를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

}
