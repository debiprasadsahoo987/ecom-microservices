package com.app.ecom.service;

import com.app.ecom.model.User;
import com.app.ecom.payload.UserRequestDTO;
import com.app.ecom.payload.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponseDTO> getAllUsers();

    void addUser(UserRequestDTO userRequestDTO);

    Optional<UserResponseDTO> getUserById(Long id);

    boolean updateUser(Long id, UserRequestDTO updatedUserRequestDTO);
}
