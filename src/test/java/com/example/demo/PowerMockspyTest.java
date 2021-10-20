package com.example.demo;

import static org.mockito.Mockito.doNothing;

import com.example.demo.util.TestApstractClass;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class PowerMockspyTest {
    @Test
    public void testMockApstractClass() {
        TestApstractClass mock = PowerMockito.spy(new TestApstractClass(){
        });
        doNothing().when(mock).isSupportOperator();
        Assert.assertTrue(true);
    }
}
