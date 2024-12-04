package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.entity.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    List<Review> findAll();

    Review findById(UUID id);
    Review addReview(Review review);
    void delete(UUID id);
    Review update(Review client, UUID id);
}
