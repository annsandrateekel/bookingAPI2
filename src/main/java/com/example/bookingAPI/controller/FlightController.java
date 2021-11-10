package com.example.bookingAPI.controller;

import com.example.bookingAPI.model.Flight;
import com.example.bookingAPI.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getAll() {
        return flightService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Flight> flight(@PathVariable String id) {
        Optional<Flight> flight = flightService.findById(id);
        return flight.map(body -> ResponseEntity.ok(body))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public String save(@RequestBody Flight flight) {
        return flightService.save(flight);
    }

    @PutMapping("{id}")
    public void update(@PathVariable String id, @RequestBody Flight newFlight) {
        Optional<Flight> oldFlight = flightService.findById(id);
        if(oldFlight.isPresent()) {
            flightService.update(id, newFlight);
        } else {
            flightService.save(newFlight);
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        flightService.delete(id);
    }

}
