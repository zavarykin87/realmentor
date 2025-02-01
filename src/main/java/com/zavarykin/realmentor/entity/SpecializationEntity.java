package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "specializations")
public class SpecializationEntity {

    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "specializations")
    private Set<MentorEntity> mentorEntities = new HashSet<>();

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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SpecializationEntity that = (SpecializationEntity) object;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
