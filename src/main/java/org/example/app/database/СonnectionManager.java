package org.example.app.database;

import org.example.app.utils.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class СonnectionManager {
    private СonnectionManager(){

    }



    public static Connection open(){
        try {
            return DriverManager.getConnection(Constant.HOST, Constant.USERNAME, Constant.PASSWORD);

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
