package tp01.service.impl;

import tp01.dto.CreateUserDto;
import tp01.model.User;
import tp01.repository.UserRepository;
import tp01.service.UserService;
import tp01.util.EncryptPassword;

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
