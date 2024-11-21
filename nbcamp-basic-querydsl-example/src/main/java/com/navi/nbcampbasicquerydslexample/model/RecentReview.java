package com.navi.nbcampbasicquerydslexample.model;

import java.time.LocalDateTime;

public record RecentReview(
    long reviewId,
    long userId,
    String userName,
    long storeId,
    long storeName,
    int rate,
    String comment,
    LocalDateTime orderedAt
) {

}
