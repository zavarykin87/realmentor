package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

//@Entity
//@Table(name = "categories")
public class CategoryEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "name", nullable = false, unique = true)
//    private String name;
//
//    @ManyToMany(mappedBy = "categories")
//    private Set<MentorEntity> mentorEntities;
//
//    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<SpecializationEntity> specializationEntities;
//
//    public void addSpecialisation(SpecializationEntity specializationEntity) {
//        this.specializationEntities.add(specializationEntity);
//        specializationEntity.setCategory(this);
//    }
//
//    public void removeSpecialisation(SpecializationEntity specializationEntity) {
//        this.specializationEntities.remove(specializationEntity);
//        specializationEntity.setCategory(null);
//    }
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Set<MentorEntity> getMentorEntities() {
//        return mentorEntities;
//    }
//
//    public void setMentorEntities(Set<MentorEntity> mentorEntities) {
//        this.mentorEntities = mentorEntities;
//    }

}
