package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mentors")
public class MentorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;

    @ManyToMany
    @JoinTable(name = "mentor_categories",
            joinColumns = @JoinColumn(name = "mentor_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryEntity> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "mentor_specializations",
            joinColumns = @JoinColumn(name = "mentor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id"))
    private Set<SpecializationEntity> specializationEntities = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "mentor_skills",
            joinColumns = @JoinColumn(name = "mentor_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<SkillEntity> skillEntities = new HashSet<>();


    public void addCategory(CategoryEntity categoryEntity) {
        this.categories.add(categoryEntity);
        categoryEntity.getMentorEntities().add(this);
    }

    public void removeCategory(CategoryEntity categoryEntity) {
        this.categories.remove(categoryEntity);
        categoryEntity.getMentorEntities().remove(this);
    }

    public void addSpecialization(SpecializationEntity specializationEntity) {
        this.specializationEntities.add(specializationEntity);
        specializationEntity.getMentorEntities().add(this);
    }

    public void removeSpecialization(SpecializationEntity specializationEntity) {
        this.specializationEntities.remove(specializationEntity);
        specializationEntity.getMentorEntities().remove(this);
    }

    public void addSkill(SkillEntity skillEntity) {
        this.skillEntities.add(skillEntity);
        skillEntity.getMentorEntities().add(this);
    }

    public void removeSkill(SkillEntity skillEntity) {
        this.skillEntities.remove(skillEntity);
        skillEntity.getMentorEntities().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public Set<SpecializationEntity> getSpecializations() {
        return specializationEntities;
    }

    public Set<SkillEntity> getSkills() {
        return skillEntities;
    }
}
