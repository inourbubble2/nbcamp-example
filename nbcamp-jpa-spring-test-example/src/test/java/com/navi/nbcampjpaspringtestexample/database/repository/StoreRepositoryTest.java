package com.navi.nbcampjpaspringtestexample.database.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import com.navi.nbcampjpaspringtestexample.database.entity.Store;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    @DisplayName("특정 시간대에 주문 가능한 가게를 필터링하여 조회할 수 있다.")
    void test1() {
        storeRepository.saveAll(
            List.of(
                new Store("커다란가게", LocalTime.of(10, 0, 0), LocalTime.of(20, 0,0)),
                new Store("작은가게", LocalTime.of(12, 0, 0), LocalTime.of(22, 0,0)),
                new Store("구멍가게", LocalTime.of(9, 0, 0), LocalTime.of(18, 0,0)),
                new Store("아름다운가게", LocalTime.of(20, 0, 0), LocalTime.of(23, 0,0))
            )
        );


        List<Store> result = storeRepository.findStoresOpenAt(LocalTime.of(18, 0, 0));

        assertThat(result).hasSize(2)
            .extracting(Store::getName)
            .containsExactlyInAnyOrder("커다란가게", "작은가게");
    }
}
