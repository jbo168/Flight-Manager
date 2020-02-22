package com.flightmanager.app.serviceImpl;

import com.flightmanager.app.model.Flight;
import com.flightmanager.app.services.FlightService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private ArrayList<Flight> flights;

    public FlightServiceImpl(){
        getFlights();
    }

    @Override
    public List<Flight> listAllFlights() {
            return new ArrayList<Flight>(flights);
    }

    private void getFlights(){
        flights = new ArrayList<>();

        Flight flight1 = new Flight();
        flight1.setId(1);
        flight1.setAirline("Ryanair");
        flight1.setFrom("Cork");
        flight1.setTo("Ibiza");
        flight1.setCost(201);
        flight1.setNoOfTickets(150);

        flights.add(flight1);

        Flight flight2 = new Flight();
        flight2.setId(2);
        flight2.setAirline("Aerlingus");
        flight2.setFrom("Shannon");
        flight2.setTo("Luexmbourg");
        flight2.setCost(89);
        flight2.setNoOfTickets(180);

        flights.add(flight2);
    }
}