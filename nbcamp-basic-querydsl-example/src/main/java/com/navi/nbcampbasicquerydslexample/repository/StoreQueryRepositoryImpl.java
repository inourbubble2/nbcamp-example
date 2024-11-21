package com.navi.nbcampbasicquerydslexample.repository;

import com.navi.nbcampbasicquerydslexample.entity.QStore;
import com.navi.nbcampbasicquerydslexample.entity.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StoreQueryRepositoryImpl {

    public StoreQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private final JPAQueryFactory queryFactory;

    public Store getStore(Long storeId) {
        QStore store = QStore.store;

        return queryFactory
            .selectFrom(store)
            .where(store.id.eq(storeId))
            .fetchOne();
    }

}
