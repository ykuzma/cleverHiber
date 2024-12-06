package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.TestHelper;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    TestHelper testHelper;

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarServiceImpl carService;

    public CarServiceImplTest() {
        testHelper = new TestHelper();
    }

    @Test
    void shouldAddCar() {
        //given
        Car car = new Car();

        //when
        carService.addCar(car);
        //then
        verify(carRepository).save(car);
    }
}