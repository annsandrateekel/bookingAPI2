package com.example.bookingAPI.controller;

import com.example.bookingAPI.model.Booking;
import com.example.bookingAPI.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getAll() {
        return bookingService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Booking> booking(@PathVariable String id) {
        Optional<Booking> booking = bookingService.findById(id);
        return booking.map(body -> ResponseEntity.ok(body))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public String save(@RequestBody Booking booking) {
        return bookingService.save(booking);
    }

    @PutMapping("{id}")
    public void update(@PathVariable String id, @RequestBody Booking newBooking) {
        Optional<Booking> oldBooking = bookingService.findById(id);
        if(oldBooking.isPresent()) {
            bookingService.update(id, newBooking);
        } else {
            bookingService.save(newBooking);
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        bookingService.delete(id);
    }

}
