package ir.dotin.service;

import ir.dotin.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void deactivateUser(Long userId);

    User getUserStatus(Long userId);

    List<User> getAllUsers();

}
