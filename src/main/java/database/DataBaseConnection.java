package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String url = "jdbc:mysql://localhost:3307/tap_db";
    private static final String user="root";
    private static final String password="";
    protected static Connection connection = null;

    public static Connection getConnection() {
        if(connection == null){
            connect();
        }
        return connection;
    }

    private static void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user,password);
            System.out.println("Database connected!");
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public static boolean disconnect(){
        try{
            connection.close();
            return true;
        }catch (SQLException e){
            return false;
        }
    }


}
