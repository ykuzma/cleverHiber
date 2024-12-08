package by.kuzma.clever.hiber.dto;

import by.kuzma.clever.hiber.entity.Client;

import java.util.UUID;

public record ReviewDto(UUID id, String content, double rank, ClientDto client, CarDto car) {
}
