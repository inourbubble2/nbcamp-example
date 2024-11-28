package com.navi.nbcampjpaspringtestexample.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalTime openAt;

    private LocalTime closeAt;

    public boolean isAvailableToOrder(LocalTime ordersAt) {
        if (ordersAt.isBefore(openAt) || ordersAt.isAfter(closeAt)) {
            return false;
        }

        return true;
    }

    public Store(String name, LocalTime openAt, LocalTime closeAt) {
        this.name = name;
        this.openAt = openAt;
        this.closeAt = closeAt;
    }
}
