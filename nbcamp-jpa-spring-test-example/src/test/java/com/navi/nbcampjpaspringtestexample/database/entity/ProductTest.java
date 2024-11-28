package com.navi.nbcampjpaspringtestexample.database.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    @DisplayName("상품이 정상적으로 생성된다.")
    void test1() {
        Product product = new Product("공책", 1000, 0.1);

        assertThat(product.getName()).isEqualTo("공책");
        assertThat(product.getPrice()).isEqualTo(1000);
        assertThat(product.getDiscountRate()).isEqualTo(0.1);
    }

    @Test
    @DisplayName("상품의 할인된 가격을 정상적으로 조회한다.")
    void test2() {
        Product product = new Product("공책", 1000, 0.1);

        assertThat(product.getDiscountedPrice()).isEqualTo(900);
    }

    @Test
    @DisplayName("상품의 할인율이 0.0 ~ 0.1이 아니라면 생성할 수 없다.")
    void test3() {
        assertThatThrownBy(() -> new Product("연필", 300, 2.0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Invalid discount rate");

        assertThatThrownBy(() -> new Product("연필", 300, -0.1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Invalid discount rate");
    }
}
