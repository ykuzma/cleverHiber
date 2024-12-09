package by.kuzma.clever.hiber.dto;

import by.kuzma.clever.hiber.entity.Address;

import java.util.UUID;

public record CarShowroomFindAllResponse(UUID id, String name, Address address) {
}
