package com.boot.springbootapi.service;

import com.boot.springbootapi.model.Product;
import com.boot.springbootapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> fetchAllProducts();
    Optional<Product> fetchProductById(Long id);
    Product updateProduct(Long id, Product updatedProduct);
    ResponseEntity<String> deleteProduct(Long id);



}
