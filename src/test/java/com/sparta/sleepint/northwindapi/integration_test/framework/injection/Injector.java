package com.sparta.sleepint.northwindapi.integration_test.framework.injection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.sleepint.northwindapi.integration_test.framework.connection.ConnectionManager;

public class Injector {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getDTO(String connectionPath, Class<T> dto){
        String json = ConnectionManager.getBody(connectionPath);
        T dtoResponse = null;
        try {
            dtoResponse = mapper.readValue(json, dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return dtoResponse;
    }

    public static <T> T[] getDTOArray(String connectionPath, Class<T> dto){
        String json = ConnectionManager.getBody(connectionPath);
        T[] dtoResponse = null;
        try {
            dtoResponse = (T[]) mapper.readValue(json, dto.arrayType());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return dtoResponse;
    }
}
