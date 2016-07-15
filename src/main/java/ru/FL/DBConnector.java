package ru.FL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by F4KEFLY on 15.07.2016.
 * Description: класс для подключения к базе данных.
 */
public class DBConnector
{

    private final String HOST = "jdbc:mysql://localhost:3306/ws_log";
    private final String USER = "root";
    private final String PASS = "root";
    private Connection connection;

    DBConnector()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection()
    {
        return connection;
    }
}
