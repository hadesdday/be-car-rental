package com.carrental.dto;

import com.carrental.entity.*;
import com.carrental.enums.Gender;
import com.carrental.enums.Role;
import com.carrental.enums.UserStatus;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private LocalDateTime dob;
    private Gender gender;
    private String phone;
}
