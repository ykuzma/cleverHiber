package by.kuzma.clever.hiber.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record ClientBuyCar(@NotNull UUID id, @NotEmpty List<CarDto> cars) {
}
