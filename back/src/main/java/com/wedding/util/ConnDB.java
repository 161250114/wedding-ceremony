package com.nju.wedding.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnDB {

    private static String url = "jdbc:mysql://localhost:3306/graduation?useUnicode=true&characterEncoding=utf8";

    private static String username = "root";

    private static String password = "980328";

    private static Connection conn;

    public static Connection getConn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args){
        ConnDB.getConn();
    }

}
