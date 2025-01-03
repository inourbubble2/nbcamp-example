package com.navi.nbcampjavatodotask.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record TodoDeleteRequest(
    @Schema(description = "비밀번호")
    @NotBlank
    String password
) {

}
