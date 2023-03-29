package com.carrental.entity.composite_key;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RatingId implements Serializable {
    private Long carId;
    private Long userId;
}
