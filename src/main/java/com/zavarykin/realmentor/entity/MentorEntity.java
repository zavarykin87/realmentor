package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "mentors")
public class MentorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;

    // получить список менторов по специализации

    private Set<Category> categories;

    private Set<Specialization> specializations;

    private Set<Skill> skills;

}
