package com.moodshare.backendusers.service;

import com.moodshare.backendusers.dto.UserRegistroDTO;
import com.moodshare.backendusers.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    public User guardar(UserRegistroDTO registroDTO);
}
