package com.carrental.entity;


import com.carrental.enums.RepeatedCalendarPriority;
import com.carrental.enums.RepeatedCalendarType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "repeated_calendar")
@Getter
@Setter
public class RepeatedCalendarEntity extends BaseEntity {
    private String value;
    @Enumerated(EnumType.ORDINAL)
    private RepeatedCalendarType type;
    private Date startDate;
    private Date endDate;
    @Enumerated(EnumType.ORDINAL)
    private RepeatedCalendarPriority priority;
}
