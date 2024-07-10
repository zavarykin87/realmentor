package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "mentors")
public class MentorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mentor_to_specialization",
               joinColumns = @JoinColumn(name = "mentor_id"),
               inverseJoinColumns = @JoinColumn(name = "specialization_id"))
    private Set<SpecializationEntity> specializations;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "mentor_to_skill",
            joinColumns = @JoinColumn(name = "mentor_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<SkillEntity> skills;

    @OneToOne(mappedBy = "mentor")
    private AccountEntity account;

}
