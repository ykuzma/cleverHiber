package by.kuzma.clever.hiber.service.impl;

import by.kuzma.clever.hiber.dto.ReviewDto;
import by.kuzma.clever.hiber.entity.Review;
import by.kuzma.clever.hiber.mapper.ReviewMapper;
import by.kuzma.clever.hiber.repository.ReviewRepository;
import by.kuzma.clever.hiber.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final ReviewMapper mapper;


    @Override
    public List<ReviewDto> findAll() {

        return mapper.toDtos(repository.findAll());
    }

    @Override
    public ReviewDto findById(UUID id) {

        return mapper.toDto(repository.findById(id).orElseThrow());
    }

    @Override
    public ReviewDto addReview(ReviewDto reviewDto) {


        return mapper.toDto(repository.save(mapper.toEntity(reviewDto)));
    }

    @Override
    public void delete(UUID id) {

        repository.deleteById(id);

    }

    @Override
    public ReviewDto update(ReviewDto reviewDto, UUID id) {
        Review review = mapper.toEntity(reviewDto);
        review.setId(id);

        return mapper.toDto(repository.save(review));
    }

    @Override
    public List<Review> fullTextSearch(String predicate) {
      /*  Transaction transaction = null;
        List<Review> result;
        try {
            transaction = HibernateUtil.openTransaction();
            result = getReviews(predicate);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
*/
        return null;
    }

/*    private List<Review> getReviews(String predicate) {
        SearchSession searchSession = Search.session(sessionFactory.getCurrentSession());
        List<Review> result;
        result = searchSession.search(Review.class)
                .where(f -> f.match()
                        .fields("content")
                        .matching(predicate))
                .fetchAllHits();
        return result;
    }*/


}
