package com.navi.nbcampbasicquerydslexample.repository;

import com.navi.nbcampbasicquerydslexample.entity.Product;
import com.navi.nbcampbasicquerydslexample.entity.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    public ProductQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private final JPAQueryFactory queryFactory;

    private final QProduct product = QProduct.product;

    public List<Product> getProductsUnderPrice(long storeId, int price) {
        return queryFactory
            .selectFrom(product)
            .where(product.price.loe(price)
                .and(product.store.id.eq(storeId)))
            .fetch();
    }

    public List<Product> getSortedProductsOf(long storeId) {
        return queryFactory
            .selectFrom(product)
            .where(product.store.id.eq(storeId))
            .orderBy(product.price.asc(), product.name.desc())
            .fetch();
    }

    public List<Product> getPaginatedProducts(int page, int size) {
        return queryFactory
            .selectFrom(product)
            .orderBy(product.id.asc())
            .offset(page)
            .limit(size)
            .fetch();
    }

    public Page<Product> getPagedProducts(Pageable pageable) {
        QProduct product = QProduct.product;

        List<Product> products = queryFactory
            .selectFrom(product)
            .orderBy(product.price.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        long total = queryFactory
            .select(product.id.count())
            .from(product)
            .fetchOne();

        // Page 객체 반환
        return new PageImpl<>(products, pageable, total);
    }
}
