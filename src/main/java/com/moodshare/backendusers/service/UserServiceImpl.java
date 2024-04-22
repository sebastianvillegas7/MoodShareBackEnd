package com.moodshare.backendusers.service;

import com.moodshare.backendusers.dto.UserRegistroDTO;
import com.moodshare.backendusers.models.Rol;
import com.moodshare.backendusers.models.User;
import com.moodshare.backendusers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User guardar(UserRegistroDTO registroDTO) {
        User user = new User(registroDTO.getName(), registroDTO.getApellido(), registroDTO.getEmail(), registroDTO.getPassword(), Arrays.asList(new Rol("ROLE_USER")));

        return userRepository.save(user);
    }
}
