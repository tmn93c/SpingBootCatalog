package com.example.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconf {

    private String connstr;
    private Connection connect;

    public Dbconf() {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch(ClassNotFoundException e) {
                System.out.println(e.toString());
            }
    }
    public Connection getConnection() {
        connstr = "jdbc:oracle:thin:@localhost:1521:xe";

        try {
            String uname = "tamnd";
            String pass = "tamnd";
            
            connect = DriverManager.getConnection(connstr, uname, pass);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return connect;
    }
    public void closeConnection() {
        try {
            this.connect.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
