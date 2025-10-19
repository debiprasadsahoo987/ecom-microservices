package com.ecom.app.user.services;

import com.ecom.app.user.payload.UserRequestDTO;
import com.ecom.app.user.payload.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponseDTO> getAllUsers();

    void addUser(UserRequestDTO userRequestDTO);

    Optional<UserResponseDTO> getUserById(Long id);

    boolean updateUser(Long id, UserRequestDTO updatedUserRequestDTO);
}
