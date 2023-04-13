package com.carrental.service;

import com.carrental.dto.UserDTO;
import com.carrental.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    public UserDTO findByUsername(String username);
    public boolean checkPassword(UserDetails userDetails, String password);
}
