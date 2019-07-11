package com.rest.employee.entity;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBO {
    public static Connection con;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vicky18",
                    "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getCon() {
        return con;
    }

}
