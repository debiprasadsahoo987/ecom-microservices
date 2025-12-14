package com.ecom.app.user.controllers;

import com.ecom.app.user.payload.UserRequestDTO;
import com.ecom.app.user.payload.UserResponseDTO;
import com.ecom.app.user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserRequestDTO userRequestDTO) {
        userService.addUser(userRequestDTO);
        return new ResponseEntity<>("User added successfully",  HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
        log.info("Request received for user id {}", id);
        log.info("Info Level Logs");
        log.trace("Trace Level Logs");
        log.debug( "Debug Level Logs");
        log.warn( "Warn Level Logs");
        log.error( "Error Level Logs");
        return  userService.getUserById(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id,  @RequestBody UserRequestDTO updatedUserRequestDTO) {
        boolean updated = userService.updateUser(id, updatedUserRequestDTO);
        if(updated) {
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

}
