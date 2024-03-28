package com.example.dancersbase;
import java.sql.*;
public class ConnectorMananger {
    private final static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+BaseInfo.Host+":"+BaseInfo.Port+
                    "/"+BaseInfo.NAME_Base, BaseInfo.User,BaseInfo.PasswordUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

public static Connection getConnection()  {
   return  connection;
}

}
