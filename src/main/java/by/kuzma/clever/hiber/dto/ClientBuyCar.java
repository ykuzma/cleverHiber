package by.kuzma.clever.hiber.dto;

import java.util.List;
import java.util.UUID;

public record ClientBuyCar(UUID id, List<CarDto> cars) {
}
