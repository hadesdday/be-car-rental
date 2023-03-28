package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "notificaiton")
@Getter
@Setter
public class NotificationEntity extends BaseEntity{
    private String title;
    private String message;
}
