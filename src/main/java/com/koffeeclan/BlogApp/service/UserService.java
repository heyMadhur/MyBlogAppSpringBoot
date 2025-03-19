package com.koffeeclan.BlogApp.service;

import com.koffeeclan.BlogApp.dto.UserDTO;
import com.koffeeclan.BlogApp.entity.Users;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Users createUser(Users users);
    Users getUserById(Long id);
    Users updateUser(Long id, UserDTO updatedUsers);
    boolean deleteUser(Long id);

    Users getUserByUsername(@NotBlank(message = "Author name is required") String authorName);

    UserDTO toUserDTO(Users users);

}
