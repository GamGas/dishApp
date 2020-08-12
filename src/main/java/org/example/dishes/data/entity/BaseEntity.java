package org.example.dishes.data.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class BaseEntity {
    /**
     * Common id field
     */
    @Id
    @GeneratedValue
    private Long id;
}
