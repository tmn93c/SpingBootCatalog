package com.example.demo;

import javax.validation.constraints.AssertTrue;

// import com.example.demo.util.CheckString;
import com.example.demo.util.DateHelper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)

public class DateHelperTest {
    @Autowired
    DateHelper dateHelper;

    @Test
    public void testStrToIntant() {
        dateHelper.strToIntance("09:10");
    }

    @Test
    public void testCheckString() {
        // boolean check =  CheckString.isSupportOperator(">=");
        // Assert.assertEquals(check,true);
    }
}
