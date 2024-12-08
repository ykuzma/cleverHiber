package by.kuzma.clever.hiber.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ClientDto(UUID id, String name, LocalDate dateOfRegistration, List<String> contacts, List<CarDto> cars) {
}
