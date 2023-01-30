package com.example.demo;

import com.example.demo.model.UserModel;
import com.example.demo.util.DateHelper;
import com.example.demo.util.RestClient;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RestClientTest {
    @InjectMocks
    RestClient restClient;
    @Mock
    RestOperations restOperationsMock;

    private RestTemplate restTemplate;
    @Before
    public void setup() {

    }
    @Test
    public void testConvertTimeStamptoLocalDate() {
        UserModel um = new UserModel();
        um.setId(100L);
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.IMAGE_JPEG);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        when(restOperationsMock.exchange("u;",
                HttpMethod.GET, entity, String.class, 1)).thenReturn(new ResponseEntity<String>("Ok",HttpStatus.OK));
        restClient.callApi();
    }

}
