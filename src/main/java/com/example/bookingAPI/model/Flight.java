package com.example.bookingAPI.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "flights")
@Data
public class Flight {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "flight_id")
    private String flightId;
    @Column(name = "departure",length = 3)
    private String departure;
    @Column(name = "arrival",length = 3)
    private String arrival;
    @Column(name = "departure_date")
    private String departureDate;
    @Column(name = "arrival_date")
    private String arrivalDate;
    /*@JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();*/

    public Flight() {
    }

    public Flight(String departure, String arrival, String departureDate, String arrivalDate) {
        this.departure = departure;
        this.arrival = arrival;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }
}
