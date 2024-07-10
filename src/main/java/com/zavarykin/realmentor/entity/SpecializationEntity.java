package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "specializations")
public class SpecializationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToMany(mappedBy = "specializations")
    private Set<MentorEntity> mentors;

}
