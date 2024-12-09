package by.kuzma.clever.hiber.controller;

import by.kuzma.clever.hiber.dto.ClientBuyCar;
import by.kuzma.clever.hiber.dto.ClientDto;
import by.kuzma.clever.hiber.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping("/")
    public ResponseEntity<List<ClientDto>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable @NotNull UUID id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ClientDto> addClient(@RequestBody @Valid ClientDto clientDto) {
        return new ResponseEntity<>(service.addClient(clientDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@RequestBody @Valid ClientDto clientDto, @PathVariable @NotNull UUID id) {
        return new ResponseEntity<>(service.update(clientDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteClient(@PathVariable @NotNull UUID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PatchMapping("/car")
    ResponseEntity<Void> buyCar(@RequestBody @Valid ClientBuyCar buyCar) {
        service.buyCar(buyCar);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
