package com.sparta.sleepint.northwindapi.integration_test;

import com.sparta.sleepint.northwindapi.integration_test.framework.connection.ConnectionManager;
import com.sparta.sleepint.northwindapi.integration_test.framework.dto.ProductDTO;
import com.sparta.sleepint.northwindapi.integration_test.framework.injection.Injector;
import org.junit.jupiter.api.BeforeAll;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class ProductEndpointTest {
    private static ProductDTO productDTO;
    private static ProductDTO[] productDTOs;

    @BeforeAll
    static void setupAll(){
        //Get product array
        String findAllPath = ConnectionManager.getConnectionPath("product/all");
        productDTOs = Injector.getDTOArray(findAllPath, ProductDTO.class);

        //Get the list of product id and generate a random product id
        List<Integer> idList = Arrays.stream(productDTOs)
                                .map(ProductDTO::getId)
                                .toList();
        int randomIndex = new Random().nextInt(idList.size());
        //Get a random product
        String findById = ConnectionManager.getConnectionPath("product", idList.get(randomIndex));
        productDTO = Injector.getDTO(findById, ProductDTO.class);
    }


}
