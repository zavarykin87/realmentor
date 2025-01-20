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

    @Column(name = "about")
    private String about;

    @Column(name = "jobTitle")
    private String jobTitle;

    @Column(name = "company")
    private String company;

    @Column(name = "confirm")
    private boolean confirm = false; // в схему бд нужно добавить значение по умолчанию

    @Column(name = "experience")
    private int experience;


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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
