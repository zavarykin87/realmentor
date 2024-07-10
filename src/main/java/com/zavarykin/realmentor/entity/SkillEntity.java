package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "skills")
public class SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "skills")
    private Set<MentorEntity> mentors;
}
