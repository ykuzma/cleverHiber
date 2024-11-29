package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.TestHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    TestHelper testHelper = new TestHelper();

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarService carService;

    @Test
    void shouldAddCar() {
        //given

        //when

        //then
    }
}