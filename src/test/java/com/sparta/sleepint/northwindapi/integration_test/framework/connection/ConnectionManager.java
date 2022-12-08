package com.sparta.sleepint.northwindapi.integration_test.framework.connection;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectionManager {
    private static final String BASEURL = "http://localhost:8080/";

    public static String getConnectionPath(){
        return BASEURL;
    }

    public static String getConnectionPath(String endpointPath) {
        return BASEURL + endpointPath;
    }


    public static String getConnectionPath(String key, String value) {
        return BASEURL + "?" + key + "=" + value;
    }

    public static String getConnectionPath(String endpointPath, int id) {
        return BASEURL + endpointPath + "/" + id;
    }

    private static HttpResponse<String> getResponse(String uri){
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getStatusCode(String uri){
        return getResponse(uri).statusCode();
    }

    public static String getHeader(String uri, String key){
        return getResponse(uri).headers().firstValue(key).orElse("Key not found");
    }

    public static String getBody(String uri){
        return getResponse(uri).body();
    }





}
