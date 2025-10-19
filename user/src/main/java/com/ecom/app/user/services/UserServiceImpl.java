package com.ecom.app.user.services;

import com.ecom.app.user.models.Address;
import com.ecom.app.user.models.User;
import com.ecom.app.user.payload.UserRequestDTO;
import com.ecom.app.user.payload.UserResponseDTO;
import com.ecom.app.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserResponseDTO.class)).toList();
    }

    @Override
    public void addUser(UserRequestDTO userRequestDTO) {
        userRepository.save(modelMapper.map(userRequestDTO, User.class));
    }

    @Override
    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id).map(user -> modelMapper.map(user, UserResponseDTO.class));
    }

    @Override
    public boolean updateUser(Long id, UserRequestDTO dto) {
        return userRepository.findById(id).map(user -> {
            // Option A: manual null-safe updates
            if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
            if (dto.getLastName()  != null) user.setLastName(dto.getLastName());
            if (dto.getEmail()     != null) user.setEmail(dto.getEmail());
            if (dto.getPhone()     != null) user.setPhone(dto.getPhone());
            if (dto.getAddress() != null) {
                if (user.getAddress() == null) {
                    // create new Address and map fields from AddressDTO
                    var newAddress = new Address(); // entity creation stays internal
                    modelMapper.map(dto.getAddress(), newAddress);
                    user.setAddress(newAddress);
                } else {
                    // merge AddressDTO fields into existing Address entity
                    modelMapper.map(dto.getAddress(), user.getAddress());
                }
            }
            userRepository.save(user);
            return true;
        }).orElse(false);
    }
}
