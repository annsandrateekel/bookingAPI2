package com.example.bookingAPI.repository;

import com.example.bookingAPI.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

    Optional<List<Flight>> findByDeparture(String departure);

    Optional<List<Flight>> findByArrival(String arrival);

    Optional<List<Flight>> findByDepartureDate(String departureDate);

    Optional<List<Flight>> findByArrivalDate(String arrivalDate);

    Optional<Flight> findFlightByDepartureAndDepartureDateAndArrivalAndArrivalDate(
            String departure, String arrival, String departureDate, String arrivalDate
    );

    Flight getFlightByDepartureAndDepartureDateAndArrivalAndArrivalDate(
            String departure, String arrival, String departureDate, String arrivalDate
    );

}
