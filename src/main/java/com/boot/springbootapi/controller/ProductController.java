package com.boot.springbootapi.controller;

import com.boot.springbootapi.model.Product;
import com.boot.springbootapi.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    /**
     * Saves a new product
     * @param product
     * @return
     */
    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    /**
     * Return a list of all products
     * @return
     */
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.fetchAllProducts());
    }

    /**
     * Find a product but id.
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> optional = productService.fetchProductById(id);
        if(optional == null){
            return ResponseEntity.notFound().build();
        }
        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    /**
     * Update the information of product.
     * @param product
     * @param id
     * @return
     */
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){
        Product savedProduct = productService.updateProduct(id, product);
        if(savedProduct != null){
            return ResponseEntity.ok(savedProduct);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Delete a product by id.
     * @param id
     * @return
     */
    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
}
