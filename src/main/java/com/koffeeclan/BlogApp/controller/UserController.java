package com.koffeeclan.BlogApp.controller;

import com.koffeeclan.BlogApp.dto.UserDTO;
import com.koffeeclan.BlogApp.entity.Users;
import com.koffeeclan.BlogApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody Users users) {
        System.out.println(users);
        Users createdUsers = userService.createUser(users);
        return new ResponseEntity<>(userService.toUserDTO(createdUsers), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Users users = userService.getUserById(id);
        if(users!=null) {
            return ResponseEntity.ok(userService.toUserDTO(users));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO usersDTO) {
        Users updatedUsers = userService.updateUser(id, usersDTO);
        if(updatedUsers!=null)
            return ResponseEntity.ok(userService.toUserDTO(updatedUsers));
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean res=userService.deleteUser(id);
        if (res)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
