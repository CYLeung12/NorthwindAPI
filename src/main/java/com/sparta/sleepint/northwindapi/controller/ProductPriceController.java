package com.sparta.sleepint.northwindapi.controller;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sparta.sleepint.northwindapi.entity.Product;
import com.sparta.sleepint.northwindapi.exceptions.ControllerExceptionHandler;
import com.sparta.sleepint.northwindapi.exceptions.ResourceException;
import com.sparta.sleepint.northwindapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductPriceController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductPriceController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("product/all")
   public List<Product> getAllProducts(){
        return productRepository.findAll();
   }

   @GetMapping("product/{id}")
   public Product getProductbyId(@PathVariable Integer id){
        return productRepository.findById(id).get();
        }

    @PatchMapping(value = "product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePrice(@PathVariable Integer id, @RequestParam BigDecimal unitPrice) {
        ResponseEntity<String> response;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        Product product = productRepository.findById(id).orElseThrow(
                () ->new ResourceException(HttpStatus.OK, "We were unable to find any product with this ID.")
        );
            product.setUnitPrice(unitPrice);
            productRepository.save(product);
        try {
            response = new ResponseEntity<>(objectMapper.writeValueAsString(product), HttpStatus.OK);
        } catch (ResourceException e ) {
            response = ControllerExceptionHandler.handleException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return response;
    }


}
