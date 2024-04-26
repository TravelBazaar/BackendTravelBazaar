package com.upc.edu.Backend_TravelBazaar.controller;

import com.upc.edu.Backend_TravelBazaar.exception.ResourceNotFoundException;
import com.upc.edu.Backend_TravelBazaar.exception.ValidationException;
import com.upc.edu.Backend_TravelBazaar.model.ShoppingCart;
import com.upc.edu.Backend_TravelBazaar.service.ShoppingCartService;
import com.upc.edu.Backend_TravelBazaar.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ShoppingCarts", description = "the shoppingCart API")
@RestController
@RequestMapping("/api/travelbazaar/v1")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    // Endpoint: /api/travelbazaar/v1/shopping-carts
    // Method: GET
    // Obtiene todos los ShoppingCarts
    @Transactional(readOnly = true)
    @GetMapping("/shopping-carts")
    public ResponseEntity<List<ShoppingCart>> getAllShoppingCarts() {
        return new ResponseEntity<>(shoppingCartService.getAllShoppingCarts(), HttpStatus.OK);
    }

    // Endpoint: /api/travelbazaar/v1/shopping-carts/{id}
    // Method: GET
    // Obtiene el ShoppingCart por ID
    @Transactional(readOnly = true)
    @GetMapping("/shopping-carts/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable int id) {
        return new ResponseEntity<>(shoppingCartService.getShoppingCartById(id), HttpStatus.OK);
    }

    // Endpoint: /api/travelbazaar/v1/shopping-carts/users/{id}
    // Method: GET
    // Obtiene el ShoppingCart por ID de User
    @Transactional(readOnly = true)
    @GetMapping("/shopping-carts/users/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartByUserId(@PathVariable int id) {
        notFoundUser(id);
        notFoundShoppingCartByUserId(id);
        return new ResponseEntity<>(shoppingCartService.getShoppingCartByUserId(id), HttpStatus.OK);
    }

    // Endpoint: /api/travelbazaar/v1/shopping-carts
    // Method: POST
    // Crea el ShoppingCart
    @Transactional
    @PostMapping("/shopping-carts")
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        validateShoppingCart(shoppingCart);
        notFoundUser(shoppingCart.getUser().getId());
        existsShoppingCartByUserId(shoppingCart.getUser().getId());
        return new ResponseEntity<>(shoppingCartService.saveShoppingCart(shoppingCart), HttpStatus.CREATED);
    }

    // Endpoint: /api/travelbazaar/v1/shopping-carts/{id}
    // Method: DELETE
    // Elimina el ShoppingCart por ID
    @DeleteMapping("/shopping-carts/{id}")
    public ResponseEntity<String> deleteShoppingCart(@PathVariable int id) {
        shoppingCartService.deleteShoppingCart(id);
        return new ResponseEntity<>("ShoppingCart with id: " + id + " was deleted", HttpStatus.OK);
    }

    public void validateShoppingCart(ShoppingCart shoppingCart) {
        if (shoppingCart.getUser() == null) {
            throw new ValidationException("User is required");
        }
    }

    private void existsShoppingCartByUserId(int id) {
        if (shoppingCartService.existsByUser_Id(id)) {
            throw new ValidationException("ShoppingCart with user id: " + id + " already exists");
        }
    }

    private void notFoundUser(int id) {
        if (userService.getUserById(id) == null) {
            throw new ResourceNotFoundException("User with id: " + id + " not found");
        }
    }

    private void notFoundShoppingCartByUserId(int id) {
        if (shoppingCartService.getShoppingCartByUserId(id) == null) {
            throw new ResourceNotFoundException("ShoppingCart with user id: " + id + " not found");
        }
    }
}
