package com.navi.nbcampjavatodotask.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public record SimplifiedTodoResponse(
    @Schema(description = "일정 ID")
    Long id,
    @Schema(description = "제목")
    String title,
    @Schema(description = "생성 일시")
    LocalDateTime createdAt
) {

}
