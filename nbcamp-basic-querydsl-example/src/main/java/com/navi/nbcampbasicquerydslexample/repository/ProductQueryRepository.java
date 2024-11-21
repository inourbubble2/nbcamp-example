package com.navi.nbcampbasicquerydslexample.repository;

import com.navi.nbcampbasicquerydslexample.entity.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryRepository {


    List<Product> getProductsUnderPrice(long storeId, int price);

    List<Product> getSortedProductsOf(long storeId);

    List<Product> getPaginatedProducts(int page, int size);

    Page<Product> getPagedProducts(Pageable pageable);

}
