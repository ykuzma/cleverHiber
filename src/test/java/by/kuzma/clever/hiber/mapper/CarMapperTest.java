package by.kuzma.clever.hiber.mapper;

import by.kuzma.clever.hiber.dto.CarDto;
import by.kuzma.clever.hiber.dto.CategoryDto;
import by.kuzma.clever.hiber.entity.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class CarMapperTest {

/*
    @Autowired
    CarMapper carMapper;

    @Test
    void toCar() {
        //given
        CategoryDto categoryDto = new CategoryDto(null, "pickup");
        CarDto carDto = new CarDto(null,
                "honda", "civic", 1990, 100, categoryDto);

        //when
        Car car = carMapper.toCar(carDto);
        //then
        assertThat(car).isNotNull()
                .hasFieldOrPropertyWithValue("model", carDto.model())
                .hasFieldOrPropertyWithValue("mark", carDto.mark());
        assertThat(car.getCategory()).isNotNull()
                .hasFieldOrPropertyWithValue("name", categoryDto.name());
    }*/
}