package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "profiles")
public class ProfileEntity {

    @Id
    private String username;

    @Column(name = "firstname", nullable = false)
    private String firstname;
    // TODO добавить валидацию на минимальное количество символов
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @OneToOne
    @MapsId
    @JoinColumn(name = "username", referencedColumnName = "username", unique = true, nullable = false,
            foreignKey = @ForeignKey(name = "fk_profile_to_user"))
    private UserEntity userEntity;

    @OneToOne(mappedBy = "profileEntity")
    private MentorEntity mentorEntity;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public MentorEntity getMentorEntity() {
        return mentorEntity;
    }

    public void setMentorEntity(MentorEntity mentorEntity) {
        this.mentorEntity = mentorEntity;
    }
}
