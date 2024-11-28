package com.navi.nbcampjpaspringtestexample.database.repository;

import com.navi.nbcampjpaspringtestexample.database.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
