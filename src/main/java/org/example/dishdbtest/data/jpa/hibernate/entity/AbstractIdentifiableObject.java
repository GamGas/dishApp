package org.example.dishdbtest.data.jpa.hibernate.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("PMD")
@MappedSuperclass
@ToString
public class AbstractIdentifiableObject {
    /**
     * Common id field
     */
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private long id;
}
