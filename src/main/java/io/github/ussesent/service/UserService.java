package io.github.ussesent.service;

import io.github.ussesent.exceptions.InvalidCredentialsException;
import io.github.ussesent.exceptions.InvalidPasswordException;
import io.github.ussesent.exceptions.InvalidUsernameException;
import io.github.ussesent.exceptions.UsernameAlreadyExistsException;
import io.github.ussesent.model.User;
import io.github.ussesent.repositories.UserRepository;

import javax.sql.DataSource;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(DataSource dataSource) {
        this.userRepository = new UserRepository(dataSource);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public void registerUser(String username, String password) throws UsernameAlreadyExistsException, InvalidUsernameException, InvalidPasswordException {

        if (userRepository.userExists(username)) {
            throw new UsernameAlreadyExistsException("Данное имя занято");
        }

        if (username == null || username.isEmpty() || username.contains(" ") || username.length() <= 1) {
            throw new InvalidUsernameException("Некорректное пользовательское имя.<br>Имя должно состоять из 2 и более символов и не содержать пробелов");
        }

        if (password == null || password.isEmpty() || password.contains(" ") || password.length() <= 5) {
            throw new InvalidPasswordException("Пароль должен состоят минимум из 6 символов и не может содержать пробелы");
        }

        userRepository.registerUser(username, password);
    }

    public User loginUser(String username, String password) throws InvalidCredentialsException {
        User user =  userRepository.loginUser(username, password);

        if (user == null) {
            throw new InvalidCredentialsException("Неверный логин или пароль");
        }

        return user;
    }

    public void deleteUser(int id) {
        userRepository.deleteUserById(id);
    }

    public boolean makeAdmin(int id) {

        return userRepository.makeAdmin(id);
    }

    public boolean makeUser(int id) {
        return userRepository.makeUser(id);
    }

    public long countAdmins() {
        return userRepository.countAdmins();
    }
}
