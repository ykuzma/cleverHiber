package by.kuzma.clever.hiber.service.impl;

import by.kuzma.clever.hiber.dto.ReviewDto;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.Review;
import by.kuzma.clever.hiber.exception.NotFoundDataException;
import by.kuzma.clever.hiber.mapper.ReviewMapper;
import by.kuzma.clever.hiber.repository.CarRepository;
import by.kuzma.clever.hiber.repository.ClientRepository;
import by.kuzma.clever.hiber.repository.ReviewRepository;
import by.kuzma.clever.hiber.service.ReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final ReviewMapper mapper;


    @Override
    public List<ReviewDto> findAll() {

        return mapper.toDtos(repository.findAll());
    }

    @Override
    public ReviewDto findById(UUID id) {

        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundDataException(id, Review.class)));
    }

    @Override
    public ReviewDto addReview(ReviewDto reviewDto) {
        try {
            Review review = mapper.toEntity(reviewDto);
            review.setCar(carRepository.getReferenceById(reviewDto.car().id()));
            review.setClient(clientRepository.getReferenceById(reviewDto.client().id()));

            return mapper.toDto(repository.save(review));
        } catch (EntityNotFoundException e) {
            throw new NotFoundDataException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(UUID id) {
        if(!repository.existsById(id)) {
            throw new NotFoundDataException(id, Review.class);
        }
        repository.deleteById(id);

    }

    @Override
    public ReviewDto update(ReviewDto reviewDto, UUID id) {
        Review review = mapper.toEntity(reviewDto);
        review.setId(id);

        return mapper.toDto(repository.save(review));
    }

}
