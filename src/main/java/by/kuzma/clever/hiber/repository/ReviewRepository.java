package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

}
