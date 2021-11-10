package com.example.bookingAPI;

import com.example.bookingAPI.model.Booking;
import com.example.bookingAPI.model.BookingDao;
import com.example.bookingAPI.model.Flight;
import com.example.bookingAPI.model.Passenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BookingApiApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(BookingApiApplication.class, args);

		showMenu();
	}

	private static void showMenu() throws IOException {
		Scanner scanner = new Scanner(System.in);

		System.out.println("If you would like to add a booking press 1:");

		int topLevelChoice = scanner.nextInt();
		scanner.nextLine();

		if(topLevelChoice == 1) {
			addBooking(scanner);
		} else {
			System.out.println("Invalid input, please try again");
			showMenu();
		}
	}

	private static void addBooking(Scanner scanner) throws IOException {

		Flight flight = addFlight(scanner);
		String departure = flight.getDeparture();
		String arrival = flight.getArrival();
		String departureDate = flight.getDepartureDate();
		String arrivalDate = flight.getArrivalDate();

		Passenger passenger = addPassenger(scanner);

		if (BookingDao.findCertainFlight(departure, arrival, departureDate, arrivalDate) == null){

			// Make a booking
			Booking newBooking = new Booking();
			newBooking.setPassenger(passenger);
			newBooking.setFlight(flight);

			// Add to database
			BookingDao.saveFlight(flight);
			BookingDao.savePassenger(passenger);
			BookingDao.saveBooking(newBooking);

			/*// Assign the booking to a flight
			List<Booking> newFlightBookings = new ArrayList<>();
			newFlightBookings.add(newBooking);
			flight.setBookings(newFlightBookings);

			BookingDao.saveFlight(flight);*/

			System.out.println("\n" +
					"The booking has been successfully made!" +
					"\n");

			showMenu();

		} else {

			Flight existingFlight = BookingDao.findCertainFlight(
					departure, arrival, departureDate, arrivalDate);

			// Make a booking
			Booking newBooking = new Booking();
			newBooking.setPassenger(passenger);
			newBooking.setFlight(existingFlight);

			// Add to database
			BookingDao.saveFlight(existingFlight);
			BookingDao.savePassenger(passenger);
			BookingDao.saveBooking(newBooking);

			// Assign the booking to a flight
			/*List<Booking> existingFlightBookings = new ArrayList<>();
			existingFlightBookings.add(newBooking);
			flight.setBookings(existingFlightBookings);

			BookingDao.saveFlight(existingFlight);*/

			System.out.println("\n" +
					"The booking has been successfully made!" +
					"\n");

			showMenu();
		}

		scanner.close();
	}

	private static Flight addFlight(Scanner scanner) {

		// insert flight details
		System.out.println("Departure:");
		String newDeparture = scanner.nextLine();

		System.out.println("Arrival:");
		String newArrival = scanner.nextLine();

		System.out.println("Departure date:");
		String newDepartureDate = scanner.nextLine();

		System.out.println("Arrival date:");
		String newArrivalDate = scanner.nextLine();

		Flight flight = new Flight(newDeparture, newArrival, newDepartureDate, newArrivalDate);

		return flight;

	}

	private static Passenger addPassenger(Scanner scanner) {

		// insert passenger details
		System.out.println("Enter passenger's first name:");
		String firstName = scanner.nextLine();

		System.out.println("Enter passenger's last name:");
		String lastName = scanner.nextLine();

		System.out.println("Enter passenger's email:");
		String email = scanner.nextLine();

		Passenger passenger = new Passenger(firstName, lastName, email);

		return passenger;

	}




}
