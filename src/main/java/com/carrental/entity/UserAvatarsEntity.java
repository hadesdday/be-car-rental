package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_covers")
@Getter
@Setter
public class UserAvatarEntity extends BaseEntity{
    private String imageUrl;
    private String status;
}
