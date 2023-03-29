package com.carrental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "model")
@Getter
@Setter
public class ModelEntity extends BaseEntity {
    private String name;
    private String type;
    @OneToMany(mappedBy = "model")
    private Collection<CarEntity> cars;
}
