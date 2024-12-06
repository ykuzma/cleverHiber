package by.kuzma.clever.hiber.dto;

import java.util.UUID;

public record CarDto(UUID id, String model
        , String mark, int yearOfProduction, double price, CategoryDto category) {

}
