package com.navi.nbcampjpaspringtestexample.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import com.navi.nbcampjpaspringtestexample.api.model.OrderResponse;
import com.navi.nbcampjpaspringtestexample.database.entity.Order;
import com.navi.nbcampjpaspringtestexample.database.entity.OrderProduct;
import com.navi.nbcampjpaspringtestexample.database.entity.OrderStatus;
import com.navi.nbcampjpaspringtestexample.database.entity.Product;
import com.navi.nbcampjpaspringtestexample.database.repository.OrderProductRepository;
import com.navi.nbcampjpaspringtestexample.database.repository.OrderRepository;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class OrderServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, orderProductRepository);
    }

    @Test
    @DisplayName("주문 생성 시 Order와 OrderProduct가 데이터베이스에 저장되고, 총 금액이 올바르게 계산된다.")
    void test1() {
        // Given
        Long storeId = 1L;
        Long memberId = 1L;

        Product product1 = new Product("Product1", 1000, 0.0);
        Product product2 = new Product("Product2", 2000, 0.0);
        entityManager.persist(product1);
        entityManager.persist(product2);

        Map<Product, Long> productsWithQuantity = Map.of(
            product1, 2L,
            product2, 3L
        );

        // When
        OrderResponse response = orderService.order(storeId, memberId, productsWithQuantity);

        // Then
        // Order가 잘 생성되었는지 검증
        Order order = orderRepository.findById(response.orderId()).get();
        assertThat(order).isNotNull();
        assertThat(order.getStoreId()).isEqualTo(storeId);
        assertThat(order.getMemberId()).isEqualTo(memberId);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.READY);

        // OrderProduct가 잘 생성되었는지 검증
        List<OrderProduct> orderProducts = orderProductRepository.findAll();
        assertThat(orderProducts).hasSize(2);

        assertThat(response.totalPrice()).isEqualTo(8000); // 1000*2 + 2000*3
    }
}
