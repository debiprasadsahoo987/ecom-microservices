package com.app.ecom;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    User getUserById(Long id);
}
