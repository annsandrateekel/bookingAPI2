package com.example.bookingAPI.repository;

import com.example.bookingAPI.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

    Optional<Booking> findBookingByPassenger_PassengerId(String id);

    Optional<Booking> findBookingByPassenger_LastName(String lastName);

    Optional<List<Booking>> findBookingsByFlight_FlightId(String id);

    Optional<List<Booking>> findBookingsByFlight_Departure(String departure);

    Optional<List<Booking>> findBookingsByFlight_Arrival(String arrival);

    Optional<List<Booking>> findBookingsByFlight_DepartureDate(Date departureDate);

    Optional<List<Booking>> findBookingsByFlight_ArrivalDate(Date arrivalDate);
}
