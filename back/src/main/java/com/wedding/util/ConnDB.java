package com.wedding.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnDB {

    private static String url = "jdbc:mysql://sh-cynosdbmysql-grp-5qjydfg0.sql.tencentcdb.com:29004/wedding?useUnicode=true&characterEncoding=utf8";

    private static String username = "root";

    private static String password = "161250114haha";

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
