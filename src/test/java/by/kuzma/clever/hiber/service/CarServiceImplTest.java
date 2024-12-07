package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.TestHelper;
import by.kuzma.clever.hiber.dto.CarDto;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.Category;
import by.kuzma.clever.hiber.mapper.CarMapper;
import by.kuzma.clever.hiber.mapper.CarMapperImpl;
import by.kuzma.clever.hiber.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    TestHelper testHelper;

    @Mock
    CarRepository carRepository;

    @Spy
    CarMapperImpl carMapper;

    @InjectMocks
    CarServiceImpl carService;

    public CarServiceImplTest() {
        testHelper = new TestHelper();
    }

    @Test
    void findById() {
        //given
        UUID id = UUID.randomUUID();

        Car build = Car.builder()
                .id(id)
                .mark("honda")
                .model("civic")
                .price(100)
                .yearOfProduction(1980)
                .build();
        CarDto expected = new CarDto(id, "civic", "honda", 1980, 100, null);

        when(carRepository.findById(id)).thenReturn(build);
        //when
        CarDto actual = carService.findById(id);
        //then
        assertThat(actual).isEqualTo(expected);

    }
}