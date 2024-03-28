package com.example.dancersbase;
import java.sql.*;
public class ConnectorMananger {
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+BaseInfo.Host+":"+BaseInfo.Port+
                    "/"+BaseInfo.NAME_Base, BaseInfo.User,BaseInfo.PasswordUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

public static Connection getConnection() throws SQLException {
   return  connection;
}
}
