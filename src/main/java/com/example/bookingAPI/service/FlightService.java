package com.example.bookingAPI.service;

import com.example.bookingAPI.model.Flight;
import com.example.bookingAPI.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private FlightRepository flightRepository;

    public FlightService() {
    }

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // C
    public String save(Flight flight) {
        return flightRepository.save(flight).getFlightId();
    }

    // R
    public Optional<Flight> findById(String flightId) {
        return flightRepository.findById(flightId);
    }

    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    public Optional<Flight> findByDepartureAndArrivalPlacesAndDates(String departure, String arrival, String departureDate, String arrivalDate){
        return flightRepository.findFlightByDepartureAndDepartureDateAndArrivalAndArrivalDate(departure, arrival, departureDate, arrivalDate);
    }

    public Flight getByDepartureAndArrivalPlacesAndDates(String departure, String arrival, String departureDate, String arrivalDate){
        return flightRepository.getFlightByDepartureAndDepartureDateAndArrivalAndArrivalDate(departure, arrival, departureDate, arrivalDate);
    }

    public Optional<List<Flight>> findByDepartureDate(String departureDate){
        return flightRepository.findByDepartureDate(departureDate);
    }

    public Optional<List<Flight>> findByArrivalDate(String arrivalDate){
        return flightRepository.findByArrivalDate(arrivalDate);
    }

    // U
    public void update(String id, Flight newFlight) {
        Optional<Flight> oldFlight = flightRepository.findById(id);
        oldFlight.ifPresent(old -> {
            newFlight.setFlightId(old.getFlightId());
            flightRepository.save(newFlight);
        });

    }

    // D
    public void delete(String id) {
        Optional<Flight> flight = flightRepository.findById(id);
        flight.ifPresent(f -> flightRepository.delete(f));
    }

}
