package service.impl;

import dto.CreateUserDto;
import model.User;
import repository.UserRepository;
import service.UserService;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserMySqlImpl implements UserService {
    private UserRepository userRepository;

    public UserMySqlImpl(Connection connection) {
        userRepository = new UserRepository(connection);
    }

    @Override
    public User createUser(CreateUserDto createUserDto) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserById(Integer id) {
        return null;
    }

    @Override
    public void removeUser(Integer id) {

    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.getUserByEmailAndPassword(email, password);
        return user;
    }
}
