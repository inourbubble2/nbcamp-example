package com.navi.nbcampjavatodotask.api.model;

public record ErrorResponse(
    String errorCode,
    String errorMessage
) {

}
