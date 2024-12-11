package by.kuzma.clever.hiber.dto;

import by.kuzma.clever.hiber.entity.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CarShowroomFindAllResponse(@NotNull UUID id, @NotBlank String name, Address address) {
}
