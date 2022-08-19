package tp01.service;

import tp01.dto.CreateUserDto;
import tp01.model.User;

import java.util.List;

// TODO: Missing update method
public interface UserService {
    Boolean createUser(CreateUserDto createUserDto);
    List<User> getAllUsers();
    List<User> getClientUsers();
    User getUserById(Integer id);
    void removeUser(Integer id);
   User login(String email, String password);
}
