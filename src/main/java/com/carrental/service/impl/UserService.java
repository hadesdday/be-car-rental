package com.carrental.service.impl;

import com.carrental.dto.UserDTO;
import com.carrental.entity.UserEntity;
import com.carrental.repository.IUserRepository;
import com.carrental.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class UserService  implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ModelMapper mp;

    @Override
    public UserDTO findByUsername(String username) {
        return this.mp.map(this.userRepository.findByUsername(username), UserDTO.class);
    }

//    @Override
//    public boolean checkPassword(UserDetails userDetails, String password) {
//        return this.passwordEncoder.matches(password, userDetails.getPassword());
//    }
    public boolean checkPassword(UserDetails userDetails, String password) {
        return userDetails.getPassword().equals(password);
    }
}
