package com.example.bookingAPI.service;

import com.example.bookingAPI.model.Booking;
import com.example.bookingAPI.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

    public BookingService() {
    }

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // C
    public String save(Booking booking) {
        return bookingRepository.save(booking).getBookingId();
    }

    // R
    public Optional<Booking> findById(String bookingId) {
        return bookingRepository.findById(bookingId);
    }

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> findByPassenger(String id) {
        return bookingRepository.findBookingByPassenger_PassengerId(id);
    }

    public Optional<List<Booking>> findByFlight(String id) {
        return bookingRepository.findBookingsByFlight_FlightId(id);
    }

    public Optional<List<Booking>> findByDeparture(String departure) {
        return bookingRepository.findBookingsByFlight_Departure(departure);
    }

    // U
    public void update(String id, Booking updatedBooking) {
        Optional<Booking> oldBooking = bookingRepository.findById(id);
        oldBooking.ifPresent(old -> {
            updatedBooking.setBookingId(old.getBookingId());
            bookingRepository.save(updatedBooking);
        });
    }

    // D
    public void delete(String id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        booking.ifPresent(b -> bookingRepository.delete(b));
    }
}
