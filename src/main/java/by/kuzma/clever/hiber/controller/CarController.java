package by.kuzma.clever.hiber.controller;

import by.kuzma.clever.hiber.dto.CarDto;
import by.kuzma.clever.hiber.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }


    @GetMapping("/")
    public ResponseEntity<List<CarDto>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable UUID id) {

        CarDto carDto = service.findById(id);
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto) {
        return new ResponseEntity<>(service.addCar(carDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto, @PathVariable UUID id) {
        return new ResponseEntity<>(service.update(carDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCar(@PathVariable UUID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }




}
