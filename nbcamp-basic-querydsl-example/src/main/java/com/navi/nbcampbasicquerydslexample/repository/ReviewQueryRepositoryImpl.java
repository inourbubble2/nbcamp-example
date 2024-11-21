package com.navi.nbcampbasicquerydslexample.repository;

import com.navi.nbcampbasicquerydslexample.entity.QOrder;
import com.navi.nbcampbasicquerydslexample.entity.QReview;
import com.navi.nbcampbasicquerydslexample.entity.QStore;
import com.navi.nbcampbasicquerydslexample.entity.QUser;
import com.navi.nbcampbasicquerydslexample.entity.Review;
import com.navi.nbcampbasicquerydslexample.model.RecentReview;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewQueryRepositoryImpl {

    public ReviewQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private final JPAQueryFactory queryFactory;

    private final QReview review = QReview.review;
    private final QOrder order = QOrder.order;
    private final QStore store = QStore.store;
    private final QUser user = QUser.user;

    public List<Review> searchReviews(Long userId, Integer minRate, Integer maxRate, LocalDateTime orderedAfter) {
        BooleanBuilder builder = new BooleanBuilder();

        if (userId != null) {
            //
            builder.and(review.user.id.eq(userId));
        }
        if (minRate != null) {
            builder.and(review.rating.goe(minRate));
        }
        if (maxRate != null) {
            builder.and(review.rating.loe(maxRate));
        }
        if (orderedAfter != null) {
            builder.and(review.order.orderedAt.after(orderedAfter));
        }

        return queryFactory
            .selectFrom(review)
            .join(review.order, order).fetchJoin()
            .where(builder)
            .fetch();
    }

    public List<Review> getReviewsWithStores(long storeId, LocalDateTime orderedAfter) {
        return queryFactory
            .selectFrom(review)
            .join(review.order, order).fetchJoin()
            .join(order.store, store).fetchJoin()
            .where(store.id.eq(storeId)
                .and(order.orderedAt.after(orderedAfter)))
            .fetch();
    }

    public List<Tuple> getAverageRatingsByStore() {
        return queryFactory
            .select(store, review.rating.avg())
            .from(review)
            .join(review.order, order)
            .join(order.store, store)
            .groupBy(store.id)
            .fetch();
    }

    public List<RecentReview> fetchRecentReviews(LocalDateTime orderedAfter) {
        return queryFactory
            .select(Projections.constructor(RecentReview.class,
                review.id.as("reviewId"),
                user.id.as("userId"),
                user.name.as("userName"),
                store.id.as("storeId"),
                store.name.as("storeName"),
                review.rating,
                review.comment,
                order.orderedAt
            ))
            .from(review)
            .join(review.user, user)
            .join(review.order, order)
            .join(order.store, store)
            .where(order.orderedAt.after(orderedAfter))
            .limit(10)
            .fetch();
    }

}
