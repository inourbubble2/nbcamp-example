package com.navi.nbcampbasicquerydslexample.repository;

import com.navi.nbcampbasicquerydslexample.entity.Product;
import com.navi.nbcampbasicquerydslexample.entity.User;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryRepository {

    @Query(
        "SELECT p "
        + "FROM Review r "
        + "JOIN r.order o "
        + "JOIN OrderProduct op ON op.orderId = o.id "
        + "JOIN Product p ON op.productId = p.id "
        + "WHERE r.user = :user "
        + "AND r.rating >= 5 "
        + "AND r.reviewedAt >= :reviewedAtAfter"
    )
    List<Product> getHighRatingProductsByUserIn(User user, LocalDate reviewedAtAfter);

}
