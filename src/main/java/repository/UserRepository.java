package repository;

import database.DataBaseConnection;
import dto.CreateUserDto;
import model.User;
import model.enumerators.UserTypeEnum;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository{
    private final Connection connection;

    public UserRepository() {
        this.connection = DataBaseConnection.getConnection();
    }

    public Boolean createUser(CreateUserDto user){
        try {
            Statement st = connection.createStatement();
            String query = String.format("INSERT INTO tap_db.users (name, email, type, password) VALUES ('%s', '%s', '%s', '%s')",
                    user.getName(), user.getEmail(), user.getType(), user.getName()
                    );
            st.executeUpdate(query);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<User> getAllUsers(){
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tap_db.users");

            List<User> users = new ArrayList<>();

            while (rs.next()){
                users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), UserTypeEnum.valueOf(rs.getString("type"))));
            }
            return users;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<User> getClientUsers(){
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tap_db.users u WHERE u.type = 'CLIENT'");

            List<User> users = new ArrayList<>();

            while (rs.next()){
                users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), UserTypeEnum.valueOf(rs.getString("type"))));
            }
            return users;
        } catch (SQLException e) {
            return null;
        }
    }

    public User getUserByEmailAndPassword(String email, String pasword){
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(String.format("SELECT * FROM tap_db.users WHERE email = '%s' AND password = '%s'", email, pasword));
                if(rs.next()){
                    System.out.println(rs.getString("name"));
                    User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), UserTypeEnum.valueOf(rs.getString("type")));
                    return user;
                }
                return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
