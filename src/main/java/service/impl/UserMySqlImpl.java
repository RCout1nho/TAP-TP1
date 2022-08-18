package service.impl;

import dto.CreateUserDto;
import model.User;
import repository.UserRepository;
import service.UserService;
import util.EncryptPassword;

import java.util.List;

public class UserMySqlImpl implements UserService {
    private final UserRepository userRepository;

    public UserMySqlImpl() {
        userRepository = new UserRepository();
    }

    @Override
    public Boolean createUser(CreateUserDto createUserDto) {
        createUserDto.setPassword(EncryptPassword.encrypt(createUserDto.getPassword()));
        return userRepository.createUser(createUserDto);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public List<User> getClientUsers() {
        return userRepository.getClientUsers();
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
        String encryptedPassword = EncryptPassword.encrypt(password);
        return userRepository.getUserByEmailAndPassword(email, encryptedPassword);
    }
}
