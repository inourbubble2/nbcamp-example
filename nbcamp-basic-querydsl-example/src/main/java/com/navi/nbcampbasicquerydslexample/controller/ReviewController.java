package com.navi.nbcampbasicquerydslexample.controller;

import com.navi.nbcampbasicquerydslexample.entity.Review;
import com.navi.nbcampbasicquerydslexample.repository.ReviewQueryRepositoryImpl;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewQueryRepositoryImpl reviewQueryRepositoryImpl;

    @GetMapping("/reviews/searched")
    public List<Review> getSearchedReviews(@RequestParam LocalDate orderedAfter) {
        return reviewQueryRepositoryImpl.searchReviews(1L, 0, 5, orderedAfter.atStartOfDay());

    }

}
