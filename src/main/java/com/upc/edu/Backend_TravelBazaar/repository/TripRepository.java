package com.upc.edu.Backend_TravelBazaar.repository;

import com.upc.edu.Backend_TravelBazaar.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
}
