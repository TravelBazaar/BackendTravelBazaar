package com.upc.edu.Backend_TravelBazaar.service;

import com.upc.edu.Backend_TravelBazaar.model.Trip;

import java.util.List;

public interface TripService {
    List<Trip> getAllTrips();
    Trip getTripById(int id);
    Trip saveTrip(Trip trip);
    void deleteTrip(int id);
}
