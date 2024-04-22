package com.moodshare.backendusers.service;

import com.moodshare.backendusers.dto.UserRegistroDTO;
import com.moodshare.backendusers.models.User;

public interface IUserService {
    public User guardar(UserRegistroDTO registroDTO);
}
