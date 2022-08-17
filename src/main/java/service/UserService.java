package service;

import dto.CreateUserDto;
import model.User;

import java.util.List;
import java.util.Optional;

// TODO: Missing update method
public interface UserService {
    Boolean createUser(CreateUserDto createUserDto);
    List<User> getAllUsers();
    List<User> getClientUsers();
    User getUserById(Integer id);
    void removeUser(Integer id);
   User login(String email, String password);
}
