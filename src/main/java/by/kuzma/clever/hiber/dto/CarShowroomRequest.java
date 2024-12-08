package by.kuzma.clever.hiber.dto;

import by.kuzma.clever.hiber.entity.Address;

import java.util.UUID;

public record CarShowroomRequest(UUID id, String name, Address address) {
}
