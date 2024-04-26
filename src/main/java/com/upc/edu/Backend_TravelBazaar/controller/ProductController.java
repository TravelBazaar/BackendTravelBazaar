package com.upc.edu.Backend_TravelBazaar.controller;

import com.upc.edu.Backend_TravelBazaar.exception.ValidationException;
import com.upc.edu.Backend_TravelBazaar.model.Product;
import com.upc.edu.Backend_TravelBazaar.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Products", description = "the product API")
@RestController
@RequestMapping("/api/travelbazaar/v1")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // EndPoint: /api/travelbazaar/v1/products
    // Method: GET
    // Obtiene todos los Products
    @Transactional(readOnly = true)
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    // EndPoint: /api/travelbazaar/v1/products/{id}
    // Method: GET
    // Obtiene el Product por ID
    @Transactional(readOnly = true)
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    // EndPoint: /api/travelbazaar/v1/products
    // Method: POST
    // Crea el Product
    @Transactional
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        validateProduct(product);
        existsProductByProductName(product);
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    // EndPoint: /api/travelbazaar/v1/products/{id}
    // Method: PUT
    // Actualiza el Product
    @Transactional
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        validateProduct(product);
        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
    }

    // EndPoint: /api/travelbazaar/v1/products/{id}
    // Method: DELETE
    // Elimina el Product por ID
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product with id: " + id + " was deleted", HttpStatus.OK);
    }

    //valid que el producto tenga nombre, descripcion y precio
    public void validateProduct(Product product) {
        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
            throw new ValidationException("Product name is required");
        }
        if (product.getProductDescription() == null || product.getProductDescription().trim().isEmpty()) {
            throw new ValidationException("Product description is required");
        }
        if (product.getProductPrice() == null || product.getProductPrice() <= 0) {
            throw new ValidationException("Product price is required");
        }
    }

    //valid que no exista un producto con el mismo nombre
    private void existsProductByProductName(Product product) {
        if (productService.getProductByProductName(product.getProductName()) != null) {
            throw new ValidationException("Product name already exists");
        }
    }
}
