package by.kuzma.clever.hiber.controller;

import by.kuzma.clever.hiber.dto.CarShowroomFindAllResponse;
import by.kuzma.clever.hiber.dto.CarShowroomRequest;
import by.kuzma.clever.hiber.dto.CarShowroomResponse;
import by.kuzma.clever.hiber.service.CarShowroomService;
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

    @GetMapping("/")
    public ResponseEntity<List<CarShowroomFindAllResponse>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarShowroomResponse> getShowroom(@PathVariable UUID id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CarShowroomResponse> addShowroom(@RequestBody CarShowroomRequest showroomRequest) {
        return new ResponseEntity<>(service.addShowroom(showroomRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarShowroomResponse> updateShowroom(@RequestBody CarShowroomRequest showroomRequest, @PathVariable UUID id) {
        return new ResponseEntity<>(service.update(showroomRequest, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCar(@PathVariable UUID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
