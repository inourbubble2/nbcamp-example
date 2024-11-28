package com.navi.nbcampjpaspringtestexample.api.model;

import com.navi.nbcampjpaspringtestexample.database.entity.OrderStatus;
import com.navi.nbcampjpaspringtestexample.database.entity.Product;
import java.util.Map;

public record OrderResponse(
    Long orderId,
    Long storeId,
    Long memberId,
    OrderStatus status,
    Long totalPrice
) {

}
