package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.dto.ReviewDto;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.Client;
import by.kuzma.clever.hiber.entity.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    List<ReviewDto> findAll();

    ReviewDto findById(UUID id);
    ReviewDto addReview(ReviewDto reviewDto);
    void delete(UUID id);
    ReviewDto update(ReviewDto client, UUID id);

    List<Review> fullTextSearch(String predicate);
}
