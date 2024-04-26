package com.upc.edu.Backend_TravelBazaar.repository;

import com.upc.edu.Backend_TravelBazaar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUsernameAndPassword(String username, String password);
}
