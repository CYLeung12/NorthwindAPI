package com.sparta.sleepint.northwindapi.integration_test;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.sleepint.northwindapi.controller.ProductPriceController;
import com.sparta.sleepint.northwindapi.entity.Product;
import com.sparta.sleepint.northwindapi.repositories.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductPriceControllerTest {
    static List<Product> fakeProductList = new ArrayList<>();
    Product product1;
    @Mock
    private ProductRepository mockRepo;
    @InjectMocks
    ProductPriceController productPriceController;

    @BeforeEach
    void setup(){
//        Annotation @InjectMocks already created an instance of the controller and passing the mock repo to it, so no need to do it manually
//        productPriceController = new ProductPriceController(mockRepo);
        product1 = new Product();
        product1.setId(-1);
        product1.setProductName("Pepsi Max");
        product1.setUnitPrice(new BigDecimal("10.2"));
        Product product2 = new Product();
        product2.setId(-2);
        product2.setProductName("Tango Orange");
        product2.setUnitPrice(new BigDecimal("15.3"));
        fakeProductList.add(product1);
        fakeProductList.add(product2);
    }

    @AfterEach
    void cleanup(){
        fakeProductList.clear();
    }


    @Test
    @DisplayName("Test get all product method with size")
    void testGetAllProductMethodWithSize() {
        Mockito.when(mockRepo.findAll()).thenReturn(fakeProductList);
        Assertions.assertEquals(2, productPriceController.getAllProducts().size());
    }

    @Test
    @DisplayName("Test get all method return right result")
    void testGetAllMethodReturnRightResult() {
        Mockito.when(mockRepo.findAll()).thenReturn(fakeProductList);
        Assertions.assertEquals(fakeProductList, productPriceController.getAllProducts());
    }

    @Test
    @DisplayName("Test the price is updated")
    void testThePriceIsUpdated() {
        BigDecimal newPrice = new BigDecimal("14.4");
        Mockito.when(mockRepo.findById(product1.getId())).thenReturn(Optional.of(product1));

        //Get the return response from the controller method to be tested
        ResponseEntity<String> updatedResponse = productPriceController.updatePrice(-1, newPrice);

        //Map the response json string to a new product instance for assertion
        ObjectMapper mapper = new ObjectMapper();
        Product updatedProduct = null;
        try {
            updatedProduct = mapper.readValue(updatedResponse.getBody(), Product.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(newPrice, updatedProduct.getUnitPrice());


    }




}
