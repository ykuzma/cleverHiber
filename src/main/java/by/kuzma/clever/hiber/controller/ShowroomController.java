package by.kuzma.clever.hiber.controller;

import by.kuzma.clever.hiber.dto.CarShowroomFindAllResponse;
import by.kuzma.clever.hiber.dto.CarShowroomRequest;
import by.kuzma.clever.hiber.dto.CarShowroomResponse;
import by.kuzma.clever.hiber.service.CarShowroomService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/showrooms")
@RequiredArgsConstructor
public class ShowroomController {

    private final CarShowroomService service;

    @GetMapping("/car")
    public ResponseEntity<List<@Valid CarShowroomResponse>> getShowroomWithAllCars() {
        return new ResponseEntity<>(service.getShowroomWithAllCars(), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<@Valid CarShowroomFindAllResponse>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarShowroomResponse> getShowroom(@PathVariable @NotNull UUID id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<@Valid CarShowroomResponse> addShowroom(@RequestBody @Valid CarShowroomRequest showroomRequest) {
        return new ResponseEntity<>(service.addShowroom(showroomRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<@Valid CarShowroomResponse> updateShowroom(@RequestBody @Valid CarShowroomRequest showroomRequest, @PathVariable @NotNull UUID id) {
        return new ResponseEntity<>(service.update(showroomRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteShowroom(@PathVariable @NotNull UUID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
