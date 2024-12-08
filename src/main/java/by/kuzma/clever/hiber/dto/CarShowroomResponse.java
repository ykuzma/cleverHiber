package by.kuzma.clever.hiber.dto;

import by.kuzma.clever.hiber.entity.Address;

import java.util.List;
import java.util.UUID;

public record CarShowroomResponse(UUID id, String name, Address address, List<CarDto> cars) {
}
