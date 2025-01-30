package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mentors")
public class MentorEntity {

    @Id
    private String username;

    @OneToOne
    @MapsId
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, unique = true,
            foreignKey = @ForeignKey(name = "fk_mentor_to_profile"))
    private ProfileEntity profileEntity;

    @Column(name = "about", nullable = false)
    private String about;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "confirm", nullable = false)
    private boolean confirm;

    @ManyToMany
    @JoinTable(name = "mentor_category",
            joinColumns = @JoinColumn(name = "mentor"),
            inverseJoinColumns = @JoinColumn(name = "category"))
    private Set<CategoryEntity> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "mentor_specialization",
            joinColumns = @JoinColumn(name = "mentor"),
            inverseJoinColumns = {@JoinColumn(name = "specialization"), @JoinColumn(name = "category")})
    private Set<SpecializationEntity> specializations = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "mentor_skill",
            joinColumns = @JoinColumn(name = "mentor"),
            inverseJoinColumns = @JoinColumn(name = "skill"))
    private Set<SkillEntity> skills = new HashSet<>();

    @Column(name = "experience", nullable = false)
    private int experience;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ProfileEntity getProfileEntity() {
        return profileEntity;
    }

    public void setProfileEntity(ProfileEntity profileEntity) {
        this.profileEntity = profileEntity;
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

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }

    public void addCategory(CategoryEntity category) {
        this.categories.add(category);
        category.getMentorEntities().add(this);
    }

    public void removeCategory(CategoryEntity category) {
        this.categories.remove(category);
        category.getMentorEntities().remove(category);
    }

    public Set<SpecializationEntity> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<SpecializationEntity> specializations) {
        this.specializations = specializations;
    }

    public void addSpecialization(SpecializationEntity specialization) {
        this.getSpecializations().add(specialization);
        specialization.getMentorEntities().add(this);
    }

    public void removeSpecialization(SpecializationEntity specialization) {
        this.getSpecializations().remove(specialization);
        specialization.getMentorEntities().remove(this);
    }

    public Set<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillEntity> skills) {
        this.skills = skills;
    }

    public void addSkill(SkillEntity skill) {
        this.getSkills().add(skill);
        skill.getMentorEntities().add(this);
    }

    public void removeSkill(SkillEntity skill) {
        this.getSkills().remove(skill);
        skill.getMentorEntities().remove(this);
    }

}
