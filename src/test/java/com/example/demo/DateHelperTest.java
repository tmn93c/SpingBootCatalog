package com.example.demo;

import java.sql.Timestamp;
import java.time.LocalDate;

// import com.example.demo.util.CheckString;
import com.example.demo.util.DateHelper;

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
    public void testConvertTimeStamptoLocalDate() {
        Timestamp ts1 = Timestamp.valueOf("2018-09-01 09:01:15");  
        System.out.println("Timestamp : "+ts1);  
        LocalDate lc = ts1.toLocalDateTime().toLocalDate();
      //returns a string object in JDBC timestamp escape format .         
        String str=ts1.toString();  
        System.out.println("New Timespan : "+str);  
    }

    @Test
    public void testCheckString() {
        // boolean check =  CheckString.isSupportOperator(">=");
        // Assert.assertEquals(check,true);
    }
}
