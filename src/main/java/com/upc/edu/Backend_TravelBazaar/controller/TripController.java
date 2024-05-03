package com.upc.edu.Backend_TravelBazaar.controller;

import com.upc.edu.Backend_TravelBazaar.model.Trip;
import com.upc.edu.Backend_TravelBazaar.service.TripService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Trips", description = "the trip API")
@RestController
@RequestMapping("/api/travelbazaar/v1") //@RequestMapping("/api/tripstore/v1")
public class TripController {
    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    // Obtiene el Trip por ID
    @GetMapping("/trips/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable int id) {
        Trip trip = tripService.getTripById(id);
        if (trip != null) {
            return new ResponseEntity<>(trip, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtiene todos los Trips
    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripService.getAllTrips();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    // Crea el Trip
    @PostMapping("/trips")
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        Trip newTrip = tripService.saveTrip(trip);
        return new ResponseEntity<>(newTrip, HttpStatus.CREATED);
    }

    //elimina el trip por ID
    @DeleteMapping("/trips/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable int id) {
        tripService.deleteTrip(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
