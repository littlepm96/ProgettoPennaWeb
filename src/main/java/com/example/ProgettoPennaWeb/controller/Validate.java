package com.example.ProgettoPennaWeb.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Validate {
    public static boolean checkUser(String email, String pass) {
        boolean st = false;
        try {

            //loading drivers for mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            //creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pennaweb?ServerTimezone=UTC", "root", "Admin_96");
            PreparedStatement ps = con.prepareStatement("select * from pennaweb.utente where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            st = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }
}
