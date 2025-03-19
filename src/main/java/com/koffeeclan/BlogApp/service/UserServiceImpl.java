package com.koffeeclan.BlogApp.service;

import com.koffeeclan.BlogApp.dto.UserDTO;
import com.koffeeclan.BlogApp.entity.Users;
import com.koffeeclan.BlogApp.repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public Users createUser(Users users) {
        return userRepo.save(users);
    }

    @Override
    public Users getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public Users updateUser(Long id, UserDTO updatedUsers) {
        Users existingUsers = getUserById(id);
        if(existingUsers!=null) {
            existingUsers.setUsername(updatedUsers.getUsername());
            existingUsers.setEmail(updatedUsers.getEmail());
            return userRepo.save(existingUsers);
        }
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        Users users = getUserById(id);
        if(users!=null){
            userRepo.delete(users);
            return true;
        }
        return false;
    }

    @Override
    public Users getUserByUsername(String authorName) {
        return userRepo.findByUsername(authorName)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + authorName));
    }

    @Override
    public UserDTO toUserDTO(Users users){
        UserDTO userDTO= new UserDTO();
        userDTO.setId(users.getId());
        userDTO.setUsername(users.getUsername());
        userDTO.setEmail(users.getEmail());

        return userDTO;
    }
}
