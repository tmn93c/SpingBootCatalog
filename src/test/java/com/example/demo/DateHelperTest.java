package com.example.demo;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.util.DateHelper;

import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)

public class DateHelperTest {
    @Autowired
    DateHelper dateHelper;

    @Test
    public void testStrToIntant() {
        dateHelper.strToIntance("09:10");
    }
}
