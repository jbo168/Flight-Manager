package com.flightmanager.app.dao;

import com.flightmanager.app.model.Booking;
import com.flightmanager.app.model.Flight;

import java.util.ArrayList;
import java.util.Optional;

public interface BookingDAO {

    void save(Booking booking);
    Booking update(Booking booking);
    Optional<Flight> findById(int id);
    ArrayList<Booking> findAll();

}
