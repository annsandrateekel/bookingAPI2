package com.example.bookingAPI.repository;

import com.example.bookingAPI.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String> {

    Optional<List<Passenger>> findPassengerByLastName(String lastName);

    Optional<Passenger> findByEmail(String email);

}
