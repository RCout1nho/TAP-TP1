package tp01.repository;

import tp01.database.DataBaseConnection;
import tp01.dto.CreateUserDto;
import tp01.model.User;
import tp01.model.enumerators.UserTypeEnum;

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
                    user.getName(), user.getEmail(), user.getType(), user.getPassword()
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
            ResultSet rs = st.executeQuery(String.format("SELECT * FROM tap_db.users u WHERE u.email = '%s' AND u.password = '%s' AND u.type = 'ADMIN'", email, pasword));
                if(rs.next()){
                    User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), UserTypeEnum.valueOf(rs.getString("type")));
                    return user;
                }
                return null;
        }catch (SQLException e){
            return null;
        }
    }
}
