package com.app.ecom;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private List<User> userlist = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<User> getAllUsers() {
        return userlist;
    }

    @Override
    public void addUser(User user) {
        user.setId(nextId++);
        userlist.add(user);
    }

    @Override
    public User getUserById(Long id) {
        for (User user : userlist) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
