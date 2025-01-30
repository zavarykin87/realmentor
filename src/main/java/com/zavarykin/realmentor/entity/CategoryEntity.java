package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<MentorEntity> mentorEntities = new HashSet<>();

    @OneToMany(mappedBy = "categoryEntity", orphanRemoval = true)
    private Set<SpecializationEntity> specializationEntities = new HashSet<>();

//    public void addSpecialisation(SpecializationEntity specializationEntity) {
//        this.specializationEntities.add(specializationEntity);
//        specializationEntity.setCategory(this);
//    }
//
//    public void removeSpecialisation(SpecializationEntity specializationEntity) {
//        this.specializationEntities.remove(specializationEntity);
//        specializationEntity.setCategory(null);
//    }


    public CategoryEntity(String name) {
        this.name = name;
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

    public Set<SpecializationEntity> getSpecializationEntities() {
        return specializationEntities;
    }

    public void setSpecializationEntities(Set<SpecializationEntity> specializationEntities) {
        this.specializationEntities = specializationEntities;
    }

    public void addSpecialization(SpecializationEntity specialization) {
        this.getSpecializationEntities().add(specialization);
        specialization.setCategoryEntity(this);
    }

    public void removeSpecialization(SpecializationEntity specialization) {
        this.getSpecializationEntities().remove(specialization);
        specialization.setCategoryEntity(null);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CategoryEntity that = (CategoryEntity) object;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
