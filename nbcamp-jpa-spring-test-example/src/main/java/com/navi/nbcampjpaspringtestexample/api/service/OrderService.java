package com.navi.nbcampjpaspringtestexample.api.service;

import com.navi.nbcampjpaspringtestexample.api.model.OrderResponse;
import com.navi.nbcampjpaspringtestexample.database.entity.Order;
import com.navi.nbcampjpaspringtestexample.database.entity.OrderProduct;
import com.navi.nbcampjpaspringtestexample.database.entity.OrderStatus;
import com.navi.nbcampjpaspringtestexample.database.entity.Product;
import com.navi.nbcampjpaspringtestexample.database.repository.OrderProductRepository;
import com.navi.nbcampjpaspringtestexample.database.repository.OrderRepository;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final OrderProductRepository orderProductRepository;

    private final PaymentService paymentService; // PaymentService 추가

    @Transactional
    public OrderResponse order(
        Long storeId,
        Long memberId,
        Map<Product, Long> productsWithQuantity
    ) {
        Order order = orderRepository.save(new Order(storeId, memberId, OrderStatus.READY));

        List<OrderProduct> orderProducts = productsWithQuantity
            .entrySet()
            .stream()
            .map(entry -> new OrderProduct(order.getId(), entry.getKey().getId(), entry.getValue()))
            .toList();
        orderProductRepository.saveAll(orderProducts);

        Long totalPrice = productsWithQuantity
            .entrySet()
            .stream()
            .mapToLong(entry -> entry.getKey().getPrice() * entry.getValue())
            .sum();

        paymentService.pay(order.getId(), totalPrice);

        return new OrderResponse(
            order.getId(),
            order.getStoreId(),
            order.getMemberId(),
            order.getStatus(),
            totalPrice
        );
    }
}
