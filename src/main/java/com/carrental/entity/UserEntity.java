package com.carrental.entity;

import com.carrental.enums.Gender;
import com.carrental.enums.Role;
import com.carrental.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private LocalDateTime dob;
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    private String phone;
    @Enumerated(EnumType.ORDINAL)
    private UserStatus status;
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Collection<CarRatingEntity> ratings;
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Collection<UserAvatarEntity> avatars;
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Collection<UserCoversEntity> covers;
    @OneToMany(mappedBy = "user")
    private Collection<FavoriteCar> favorites;
    @OneToMany(mappedBy = "user")
    private Collection<NotificationUser> notificationUsers;
    @OneToMany(mappedBy = "user")
    private Collection<CarRentalEntity> rentals;
}
