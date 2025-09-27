package com.app.ecom;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    Optional<User> getUserById(Long id);

    boolean updateUser(Long id, User updatedUser);
}
