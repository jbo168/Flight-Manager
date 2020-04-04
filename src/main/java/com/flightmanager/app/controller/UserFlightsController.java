package com.flightmanager.app.controller;

import com.flightmanager.app.adaptor.BaseReview;
import com.flightmanager.app.adaptor.BookingAdaptor;
import com.flightmanager.app.adaptor.Review;
import com.flightmanager.app.adaptor.ReviewService;
import com.flightmanager.app.model.Booking;
import com.flightmanager.app.model.Flight;
import com.flightmanager.app.model.User;
import com.flightmanager.app.service.BookingService;
import com.flightmanager.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserFlightsController {

    @Autowired
    private UserService checkUserService;
    @Autowired
    private BookingService checkBookingService;

    public Booking aBooking;

        @GetMapping(value = "/userFlights")
        public String listFlights(Model model) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            User currentUser = checkUserService.findByEmail(currentPrincipalName);

            try {
                ArrayList<Flight> myFlights = checkBookingService.findAll((int) currentUser.getUser_ID());
                model.addAttribute("usersCurrentFlights", checkBookingService.findAll((int) currentUser.getUser_ID()));
                model.addAttribute("user", currentUser);
            }catch (Exception e){
                e.printStackTrace();
                return "userFlightsNoBookings";
            }
            return "userFlights";

        }

        @GetMapping(value = "/addReview/{id}")
        public String add(@PathVariable int id,Model model){

            Booking newBooking = checkBookingService.findByID(id).get();
            aBooking = newBooking;

        ReviewService reviewAdaptor = new BookingAdaptor(newBooking);

            model.addAttribute("reviewAdapter" , reviewAdaptor);  //Push a Review Adaptor On Client
            return "addReview";
        }

        // Adapt Review into a Booking
        @PostMapping(value = "/userFlights")
        public String processReview(Review reviewAdaptor){

        aBooking.setComment(reviewAdaptor.getComment());
        aBooking.setScore(reviewAdaptor.getScore());
        Booking addedReview = checkBookingService.update(aBooking);
        return "redirect:/userFlights";

    }

    @GetMapping("/cancelFlightBooking/{flightId}/{userId}")
    public String cancelFlightBooking(@PathVariable("flightId") int flightId,
                                      @PathVariable("userId") int userId) {
        List<Booking> booking = checkBookingService.findByUserIdAndFlightId(userId,flightId);
        for (Booking bookings: booking) {
            checkBookingService.deleteById(bookings.getBooking_ID());
        }

        return "redirect:/userFlights";
    }
}
