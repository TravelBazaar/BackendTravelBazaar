package com.upc.edu.Backend_TravelBazaar.service.impl;

import com.upc.edu.Backend_TravelBazaar.model.Product;
import com.upc.edu.Backend_TravelBazaar.repository.ProductRepository;
import com.upc.edu.Backend_TravelBazaar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product productToUpdate = productRepository.findById(id).orElse(null);
        if (productToUpdate != null) {
            productToUpdate.setProductName(product.getProductName());
            productToUpdate.setProductDescription(product.getProductDescription());
            productToUpdate.setProductPrice(product.getProductPrice());
            productToUpdate.setProductImageUrl(product.getProductImageUrl());
            productToUpdate.setProductRating(product.getProductRating());
            return productRepository.save(productToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public Product getProductByCategory(String productCategory) {
        return productRepository.findByProductCategory(productCategory);
    }

    @Override
    public Product getProductByRating(Double productRating) {
        return productRepository.findByProductRating(productRating);
    }
}
