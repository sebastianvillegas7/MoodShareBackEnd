package com.moodshare.backendusers.service;

import com.moodshare.backendusers.dto.UserRegistroDTO;
import com.moodshare.backendusers.models.Rol;
import com.moodshare.backendusers.models.User;
import com.moodshare.backendusers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override // todo: verrrrrrrrrrrrr
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
