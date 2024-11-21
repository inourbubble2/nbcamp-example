package com.navi.nbcampjavaspringcrudexample.api.exception;

public class BadNicknameException extends RuntimeException {

    private ErrorCode errorCode = ErrorCode.INVALID_NICKNAME;

}

class InvalidNicknameException extends BadNicknameException {

}
