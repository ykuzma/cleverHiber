package by.kuzma.clever.hiber.mapper;

import by.kuzma.clever.hiber.dto.CarShowroomRequest;
import by.kuzma.clever.hiber.dto.CarShowroomResponse;
import by.kuzma.clever.hiber.entity.CarShowroom;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = CarMapper.class)
public interface CarShowroomMapper {

    CarShowroom requestToEntity(CarShowroomRequest request);

    CarShowroomResponse toResponse(CarShowroom showroom);

    List<CarShowroom> requestsToEntities(List<CarShowroomRequest> requests);

    List<CarShowroomResponse> toResponses(List<CarShowroom> showrooms);

}