package com.navi.nbcampjavatodotask.api.model;

import com.navi.nbcampjavatodotask.database.entity.Todo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public record TodoResponse(
    @Schema(description = "일정 ID")
    Long id,
    @Schema(description = "제목")
    String title,
    @Schema(description = "내용")
    String content,
    @Schema(description = "담당자 이름")
    String username,
    @Schema(description = "생성 일시")
    LocalDateTime createdAt
) {

    public static TodoResponse of(Todo entity) {
        return new TodoResponse(
            entity.getId(),
            entity.getTitle(),
            entity.getContent(),
            entity.getUsername(),
            entity.getCreatedAt()
        );
    }
}
