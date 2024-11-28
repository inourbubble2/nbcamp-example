package com.navi.nbcampjpaspringtestexample.database.repository;

import com.navi.nbcampjpaspringtestexample.database.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
