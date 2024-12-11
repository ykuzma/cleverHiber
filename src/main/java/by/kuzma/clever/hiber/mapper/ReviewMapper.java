package by.kuzma.clever.hiber.mapper;

import by.kuzma.clever.hiber.dto.ReviewDto;
import by.kuzma.clever.hiber.entity.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {CarMapper.class, ClientMapper.class}, componentModel = "spring")
public interface ReviewMapper {
    Review toEntity(ReviewDto reviewDto);
    ReviewDto toDto(Review review);

    List<Review> toEntities(List<ReviewDto> reviewDtos);
    List<ReviewDto> toDtos(List<Review> reviews);
}
