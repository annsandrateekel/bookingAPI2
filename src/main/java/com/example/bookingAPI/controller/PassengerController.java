package com.example.bookingAPI.controller;

import com.example.bookingAPI.model.Passenger;
import com.example.bookingAPI.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<Passenger> getAll() {
        return passengerService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Passenger> passenger(@PathVariable String id) {
        Optional<Passenger> passenger = passengerService.findById(id);
        return passenger.map(body -> ResponseEntity.ok(body))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public String save(@RequestBody Passenger passenger) {
        return passengerService.save(passenger);
    }

    @PutMapping("{id}")
    public void update(@PathVariable String id, @RequestBody Passenger newPassenger) {
        Optional<Passenger> oldPassenger = passengerService.findById(id);
        if(oldPassenger.isPresent()) {
            passengerService.update(id, newPassenger);
        } else {
            passengerService.save(newPassenger);
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        passengerService.delete(id);
    }

}
