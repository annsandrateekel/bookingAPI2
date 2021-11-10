package com.example.bookingAPI.service;

import com.example.bookingAPI.model.Passenger;
import com.example.bookingAPI.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    private PassengerRepository passengerRepository;

    public PassengerService() {
    }

    //constructor
    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    // CRUD operations

    // C: Create Operation
    // Save a passenger in DB and return the ID of the created passenger
    public String save(Passenger passenger) {
        return passengerRepository.save(passenger).getPassengerId();
    }

    // R: Read Operation

    // R1 - find by id
    public Optional<Passenger> findById(String passengerId) {
        return passengerRepository.findById(passengerId);
    }

    // R2 - find all
    public List<Passenger> getAll() {
        return passengerRepository.findAll();
    }

    // R3 - find by last name
    public Optional<List<Passenger>> findByLastName(String lastName) {
        return passengerRepository.findPassengerByLastName(lastName);
    }

    // R4 - find by email (unique)
    public Optional<Passenger> findByEmail(String email) {
        return passengerRepository.findByEmail(email);
    }

    // U
    public void update(String id, Passenger updatedPassenger) {
        Optional<Passenger> oldPassenger = passengerRepository.findById(id);
        oldPassenger.ifPresent(old -> {
            updatedPassenger.setPassengerId(old.getPassengerId());
            passengerRepository.save(updatedPassenger);
        });
    }

    // D
    public void delete(String id) {
        Optional<Passenger> passenger = passengerRepository.findById(id);
        passenger.ifPresent(p -> passengerRepository.delete(p));
    }

}

