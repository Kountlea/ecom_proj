package com.telusko.ecom_proj.controller;

import com.telusko.ecom_proj.model.Product;
import com.telusko.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api") // type "http://localhost:8080/api/"
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/greet")
    public  String greet(){
        return "Hello world" ;
    } // type on browser the url "http://localhost:8080/api/greet"

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product = service.getProductById(id);

        if(product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
            Product product1 = service.addProduct(product);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);

    }

    @PutMapping("/products")
    public ResponseEntity<String> upDateProduct(@PathVariable int id, @RequestBody Product product ){
        Product product2 = service.upDateProduct(id, product);
       if(product2 != null)
           return new ResponseEntity<>( "Updated", HttpStatus.OK);
       else
           return new ResponseEntity<>( "Failed to update", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id)
    {
        Product product3 = service.getProductById(id);
        if(product3 != null) {
            service.deleteProduct(id);
            return new ResponseEntity<>("Product deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }


    /*@PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile) {
        try {
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }*/
}
