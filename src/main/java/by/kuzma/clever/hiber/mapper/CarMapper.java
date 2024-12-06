package by.kuzma.clever.hiber.mapper;

import by.kuzma.clever.hiber.dto.CarDto;
import by.kuzma.clever.hiber.entity.Car;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface CarMapper {

    List<Car> toCars(List<CarDto> cars);

    List<CarDto> toCarsDto(List<Car> cars);

    Car toCar(CarDto carDto);

    CarDto toCarDto (Car car);



}
