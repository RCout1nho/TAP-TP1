package service;

import dto.CreateUserDto;
import model.User;

import java.util.List;
import java.util.Optional;

// TODO: Missing update method
public interface UserService {
    User createUser(CreateUserDto createUserDto);
    List<User> getAllUsers();
    User getUserById(Integer id);
    void removeUser(Integer id);
   User login(String email, String password);
}
