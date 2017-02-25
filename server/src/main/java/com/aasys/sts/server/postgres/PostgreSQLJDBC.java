package com.aasys.sts.server.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by aasys on 2/25/2017.
 */
public class PostgreSQLJDBC {

    private static String DB_USER = "as3828";
    private static String DB_PASSWORD = "ohwpdhbc";
    private static String DB_SID = "as3828_cs500";
    private static String DB_HOST = "twilbury.cs.drexel.edu";
    private static int PORT = -1;

    private static Connection _connection = null;

    public static Connection getConnection()  throws SQLException,
            ClassNotFoundException{
        if (_connection == null)
            _connection = openDBConnection(DB_USER, DB_PASSWORD, DB_SID, DB_HOST, PORT);
        return _connection;
    }
    private static Connection openDBConnection(String user, String pass,
                                              String dbSID, String host, int port) throws SQLException,
            ClassNotFoundException {

        String driver = "org.postgresql.Driver";
        Class.forName(driver);

        String url = "jdbc:postgresql://" + host + "/" + dbSID;
        String username = user;
        String password = pass;

        return DriverManager.getConnection(url, username, password);
    }
}
