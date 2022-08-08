package repository;

import database.DataBaseConnection;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository{
    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers(){
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tap_db.users");

            List<User> users = new ArrayList<>();

            while (rs.next()){
                users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByEmailAndPassword(String email, String pasword){
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(String.format("SELECT * FROM tap_db.users WHERE email = '%s' AND password = '%s'", email, pasword));
                if(rs.next()){
                    System.out.println(rs.getString("name"));
                    User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
                    return user;
                }
                return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
