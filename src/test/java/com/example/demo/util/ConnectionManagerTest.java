package com.example.demo.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConnectionManagerTest {

    @InjectMocks
    private ConnectionManager connectionManager;

    @Test
    public void testConnectionTimeOut() {
        connectionManager.getConnection();
    }
}
