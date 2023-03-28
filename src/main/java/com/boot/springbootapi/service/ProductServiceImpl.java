package com.boot.springbootapi.service;

import com.boot.springbootapi.model.Product;
import com.boot.springbootapi.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    @Override
    public Product saveProduct(@RequestBody Product product) {
        Product createdProduct = productRepository.save(product);
        return createdProduct;
    }

    @Override
    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> fetchProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.isPresent() ? optionalProduct : null;
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(!optionalProduct.isPresent()){
            return null;
        }

        Product existingProduct = optionalProduct.get();
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        Product savedProduct = productRepository.save(existingProduct);

        return savedProduct;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<String> deleteProduct(Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
