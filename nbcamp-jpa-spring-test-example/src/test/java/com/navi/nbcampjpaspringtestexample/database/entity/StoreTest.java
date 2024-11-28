package com.navi.nbcampjpaspringtestexample.database.entity;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StoreTest {

    @Test
    @DisplayName("가게 영업 시간에만 주문을 할 수 있다.")
    void test1() {
        Store store = new Store("크레용 가게", LocalTime.of(10, 0), LocalTime.of(22, 0));

        assertThat(store.isAvailableToOrder(LocalTime.of(10, 0))).isTrue();
        assertThat(store.isAvailableToOrder(LocalTime.of(12, 0))).isTrue();
        assertThat(store.isAvailableToOrder(LocalTime.of(18, 0))).isTrue();
        assertThat(store.isAvailableToOrder(LocalTime.of(22, 0))).isTrue();
        assertThat(store.isAvailableToOrder(LocalTime.of(23, 0))).isFalse();
        assertThat(store.isAvailableToOrder(LocalTime.of(9, 0))).isFalse();
        assertThat(store.isAvailableToOrder(LocalTime.of(9, 59))).isFalse();
        assertThat(store.isAvailableToOrder(LocalTime.of(22, 1))).isFalse();
    }
}
