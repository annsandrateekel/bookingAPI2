package com.example.bookingAPI.model;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.example.bookingAPI.utilities.HibernateUtil.getSessionFactory;


public class BookingDao {

    public static Flight saveFlight(Flight flight) {
        Transaction transaction = null;

        try {
            Session session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(flight);
            transaction.commit();
            return flight;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return flight;
    }

    public static Passenger savePassenger(Passenger passenger) {
        Transaction transaction = null;

        try {
            Session session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(passenger);
            transaction.commit();
            return passenger;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return passenger;
    }

    public static Booking saveBooking(Booking booking) {
        Transaction transaction = null;

        try {
            Session session = getSessionFactory().openSession();
            transaction = session.beginTransaction(); //--keep the state of the data at this point
            session.saveOrUpdate(booking);
            transaction.commit();
            return booking;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return booking;
    }

    public static Flight findCertainFlight(String departure, String arrival, String departureDate, String arrivalDate){
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("select f from Flight f where f.departure = :departure " +
                    "and f.arrival = :arrival and f.departureDate = :departureDate " +
                    "and f.arrivalDate = :arrivalDate", Flight.class)
                    .setParameter("departure", departure)
                    .setParameter("arrival", arrival)
                    .setParameter("departureDate", departureDate)
                    .setParameter("arrivalDate", arrivalDate).uniqueResult();
        }
    }

}











