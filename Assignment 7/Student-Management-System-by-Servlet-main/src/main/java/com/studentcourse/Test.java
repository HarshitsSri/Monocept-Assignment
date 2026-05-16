package com.studentcourse;

import java.sql.Connection;

import com.studentcourse.util.DBConnection;

public class Test {

    public static void main(String[] args) {

        Connection con = DBConnection.getConnection();

        if(con != null) {

            System.out.println("Database Connected Successfully");

        } else {

            System.out.println("Connection Failed");
        }
    }
}