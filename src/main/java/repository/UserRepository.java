package repository;

import database.DataBaseConnection;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends DataBaseConnection{
    public List<User> getAllUsers(){
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");

            List<User> users = new ArrayList<>();

            while (rs.next()){
                users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
