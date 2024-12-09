package by.kuzma.clever.hiber.dto;

import by.kuzma.clever.hiber.entity.Client;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ReviewDto(UUID id, @NotBlank String content, @Min(0)@Max(10) double rank, ClientDto client, CarDto car) {
}
