package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specialization")
public class SpecializationEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "specializationEntities")
    private Set<MentorEntity> mentorEntities = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "name", nullable = false)
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "specializationEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SkillEntity> skillEntities = new HashSet<>();

    public void addSkill(SkillEntity skillEntity) {
        this.skillEntities.add(skillEntity);
        skillEntity.setSpecialization(this);
    }

    public void removeSkill(SkillEntity skillEntity) {
        this.skillEntities.remove(skillEntity);
        skillEntity.setSpecialization(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MentorEntity> getMentorEntities() {
        return mentorEntities;
    }

    public void setMentorEntities(Set<MentorEntity> mentorEntities) {
        this.mentorEntities = mentorEntities;
    }

    public CategoryEntity getCategory() {
        return categoryEntity;
    }

    public void setCategory(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }
}
