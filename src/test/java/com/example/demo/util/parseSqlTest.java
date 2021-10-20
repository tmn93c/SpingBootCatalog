package com.example.demo.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class parseSqlTest {
    @InjectMocks
    private ParseSql parseSql;
    @Test
    public void testparseSql() {
        String sql = "select * from customers";
        parseSql.checknow(sql);
    }
}
