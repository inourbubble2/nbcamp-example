package com.navi.nbcampjavatodotask.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String username;

    private String password;

    private LocalDateTime createdAt;

    @Builder
    public Todo(String title, String content, String username, String password, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    public void update(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }
}
