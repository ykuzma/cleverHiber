package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.entity.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository {

    Review save(Review review);
    List<Review> findAll();
    Review findById(UUID id);
    void deleteById(UUID id);
    Review update(Review review);

}
