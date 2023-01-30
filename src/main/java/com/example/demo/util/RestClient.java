package com.example.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestClient {

    @Autowired
    RestOperations restOperations;
    public void callApi() {

        String json = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"," +
                "\"roles\":[\"Member\",\"Admin\"],\"admin\":true}";

        // convert JSON string to Java Map

        try {
            Map<String, Object> map = new ObjectMapper().readValue(json, Map.class);
            String name = (String) map.get("name");
            System.out.println(name);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // Prepare header
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.IMAGE_JPEG);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // Send the request as GET
        ResponseEntity<String> result =
                restOperations.exchange("https://jsonplaceholder.typicode.com/todos/{id}",
                        HttpMethod.GET, entity, String.class, 1);

        String url = "http://localhost:8089/";
        Map<String,Object> reqBody = new HashMap<>();
        reqBody.put("storeId","1");
        ResponseEntity<String> result1 = restOperations.exchange(url, HttpMethod.GET, new HttpEntity<Map>(reqBody), String.class, 1);
    }

    public void callApi2() {

        String json = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\"," +
                "\"roles\":[\"Member\",\"Admin\"],\"admin\":true}";

        // convert JSON string to Java Map

        try {
            Map<String, Object> map = new ObjectMapper().readValue(json, Map.class);
            String name = (String) map.get("name");
            System.out.println(name);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // Prepare header
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.IMAGE_JPEG);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        // Send the request as GET
        ResponseEntity<String> result =
                restOperations.exchange("https://jsonplaceholder.typicode.com/todos/{id}",
                        HttpMethod.GET, entity, String.class, 1);

        String url = "http://localhost:8089/";
        Map<String,Object> reqBody = new HashMap<>();
        reqBody.put("storeId","1");
        ResponseEntity<String> result1 = restOperations.exchange(url, HttpMethod.GET, new HttpEntity<Map>(reqBody), String.class, 1);
    }
}
