package com.moodshare.backendusers.service;

import com.moodshare.backendusers.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    User save(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, User updatedUser);

    void deleteUser(Long id);
}
