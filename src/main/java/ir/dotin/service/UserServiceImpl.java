package ir.dotin.service;

import ir.dotin.model.Account;
import ir.dotin.model.User;
import ir.dotin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        Account account = new Account();
        account.setAmount(new Double(0));
        account.setDocuments(Collections.EMPTY_LIST);
        user.setAccount(account);
        user.setIsActive(true);
        userRepository.save(user);
    }

    @Override
    public void deactivateUser(Long userId) {
        userRepository.deactivateUser(userId);
    }

    @Override
    public User getUserStatus(Long userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
