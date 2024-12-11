package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.dto.CarShowroomFindAllResponse;
import by.kuzma.clever.hiber.dto.CarShowroomRequest;
import by.kuzma.clever.hiber.dto.CarShowroomResponse;
import by.kuzma.clever.hiber.entity.CarShowroom;

import java.util.List;
import java.util.UUID;

public interface CarShowroomService {

    List<CarShowroomFindAllResponse> findAll();

    List<CarShowroomResponse> getShowroomWithAllCars();

    CarShowroomResponse findById(UUID id);

    CarShowroomResponse addShowroom(CarShowroomRequest carShowroom);

    void delete(UUID id);

    CarShowroomResponse update(CarShowroomRequest carShowroom, UUID id);
}
