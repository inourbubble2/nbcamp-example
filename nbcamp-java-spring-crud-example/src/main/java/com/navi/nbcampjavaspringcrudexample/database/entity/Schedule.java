package com.navi.nbcampjavaspringcrudexample.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor // for JPA
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    private Long memberId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Schedule(String title, String contents, Long memberId, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this.title = title;
        this.contents = contents;
        this.memberId = memberId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(String title, String contents, LocalDateTime updatedAt) {
        this.title = title;
        this.contents = contents;
        this.updatedAt = updatedAt;
    }
}
