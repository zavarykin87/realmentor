package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@IdClass(SpecializationEntity.DerivedId.class)
@Table(name = "specializations")
public class SpecializationEntity {

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Id
    private String category;

    @ManyToOne
    @MapsId("category")
    @JoinColumn(name = "category", referencedColumnName = "name", nullable = false,
            foreignKey = @ForeignKey(name = "fk_specialization_to_category"))
    private CategoryEntity categoryEntity;


    @ManyToMany(mappedBy = "specializations")
    private Set<MentorEntity> mentorEntities = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public Set<MentorEntity> getMentorEntities() {
        return mentorEntities;
    }

    public void setMentorEntities(Set<MentorEntity> mentorEntities) {
        this.mentorEntities = mentorEntities;
    }

    //
//
//    @OneToMany(mappedBy = "specializationEntity", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<SkillEntity> skillEntities = new HashSet<>();
//
//    public void addSkill(SkillEntity skillEntity) {
//        this.skillEntities.add(skillEntity);
//        skillEntity.setSpecialization(this);
//    }
//
//    public void removeSkill(SkillEntity skillEntity) {
//        this.skillEntities.remove(skillEntity);
//        skillEntity.setSpecialization(null);
//    }
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
//
//    public CategoryEntity getCategory() {
//        return categoryEntity;
//    }
//
//    public void setCategory(CategoryEntity categoryEntity) {
//        this.categoryEntity = categoryEntity;
//    }

    public class DerivedId {

        private String name;
        private String category;

        public DerivedId(String name, String category) {
            this.name = name;
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            DerivedId derivedId = (DerivedId) object;
            return Objects.equals(name, derivedId.name) && Objects.equals(category, derivedId.category);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, category);
        }

        @Override
        public String toString() {
            return "DerivedId{" +
                    "name='" + name + '\'' +
                    ", category='" + category + '\'' +
                    '}';
        }
    }
}
