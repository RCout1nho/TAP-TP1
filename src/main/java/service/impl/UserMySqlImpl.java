package service.impl;

import dto.CreateUserDto;
import model.User;
import service.UserService;

import java.util.List;
import java.util.Optional;

public class UserMySqlImpl implements UserService {
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
    public Optional<User> login(String email, String password) {
        return null;
    }
}
