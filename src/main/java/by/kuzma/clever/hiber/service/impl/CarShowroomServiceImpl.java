package by.kuzma.clever.hiber.service.impl;

import by.kuzma.clever.hiber.dto.CarShowroomFindAllResponse;
import by.kuzma.clever.hiber.dto.CarShowroomRequest;
import by.kuzma.clever.hiber.dto.CarShowroomResponse;
import by.kuzma.clever.hiber.entity.CarShowroom;
import by.kuzma.clever.hiber.exception.NotFoundDataException;
import by.kuzma.clever.hiber.mapper.CarShowroomMapper;
import by.kuzma.clever.hiber.repository.CarShowroomRepository;
import by.kuzma.clever.hiber.service.CarShowroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CarShowroomServiceImpl implements CarShowroomService {

    private final CarShowroomRepository repository;
    private final CarShowroomMapper mapper;

    @Override
    public List<CarShowroomFindAllResponse> findAll() {

        return mapper.toAllResponses(repository.findAll());
    }

    @Override
    public List<CarShowroomResponse> getShowroomWithAllCars() {

        return mapper.toResponses(repository.findAll());
    }

    @Override
    public CarShowroomResponse findById(UUID id) {
        CarShowroom carShowroom = repository.findById(id)
                .orElseThrow(() -> new NotFoundDataException(id, CarShowroom.class));

        return mapper.toResponse(carShowroom);
    }

    @Override
    public CarShowroomResponse addShowroom(CarShowroomRequest carShowroom) {
        return mapper.toResponse(repository.save(mapper.requestToEntity(carShowroom)));
    }

    @Override
    public void delete(UUID id) {
        if(!repository.existsById(id)) {
            throw new NotFoundDataException(id, CarShowroom.class);
        }
        repository.deleteById(id);
    }

    @Override
    public CarShowroomResponse update(CarShowroomRequest carShowroomRequest, UUID id) {

        CarShowroom carShowroom = mapper.requestToEntity(carShowroomRequest);

        return mapper.toResponse(repository.save(carShowroom));
    }

}
