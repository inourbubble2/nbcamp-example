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
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Auditing
    private LocalDateTime registeredAt;

    private LocalDateTime updatedAt;

    public void update(String name, LocalDateTime updatedAt) {
        this.name = name;
        this.updatedAt = updatedAt;
    }

    public Member(String name, LocalDateTime registeredAt, LocalDateTime updatedAt) {
        this.name = name;
        this.registeredAt = registeredAt;
        this.updatedAt = updatedAt;
    }
}
