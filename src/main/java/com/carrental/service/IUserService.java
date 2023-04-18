package com.carrental.service;

import com.carrental.dto.UserDTO;
import com.carrental.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    public UserDTO findByUsernameDTO(String username);
    public UserEntity findByUsername(String username);
    public boolean checkPassword(UserDetails userDetails, String password);

    public boolean checkValidPassword(String password);

    public boolean checkExistUser(String username);

    public UserDTO save(UserEntity user);
}
