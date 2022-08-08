package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static String url = "";
    private static String user="root";
    private static String password="root";
    protected static Connection connection = null;

    public DataBaseConnection(){
        if(connection == null){
            connect();
        }
    }

    private static boolean connect(){
        try{
            connection = DriverManager.getConnection(url, user,password);
            return true;
        }catch (SQLException e){
            return false;
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
