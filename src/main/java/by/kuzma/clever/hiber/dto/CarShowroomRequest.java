package by.kuzma.clever.hiber.dto;

import by.kuzma.clever.hiber.entity.Address;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CarShowroomRequest(UUID id, @NotBlank String name, Address address) {
}
