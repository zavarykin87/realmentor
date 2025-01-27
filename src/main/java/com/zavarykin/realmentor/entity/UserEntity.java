package com.zavarykin.realmentor.entity;


import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Set;


@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "userEntity")
    private Set<AuthorityEntity> authorities;

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userEntity")
//    private ProfileEntity profileEntity;
//
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userEntity")
//    private VerificationTokenEntity verificationTokenEntity;
//
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userEntity")
//    private EmailEntity email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(AuthorityEntity authority) {
        this.authorities.add(authority);
        authority.setUserEntity(this);
    }

    public void removeAuthority(AuthorityEntity authority) {
        this.authorities.remove(authority);
        authority.setUserEntity(null);
    }

//    public ProfileEntity getProfileEntity() {
//        return profileEntity;
//    }
//
//    public void setProfileEntity(ProfileEntity profileEntity) {
//        this.profileEntity = profileEntity;
//    }
//
//    public VerificationTokenEntity getVerificationToken() {
//        return verificationTokenEntity;
//    }
//
//    public void setVerificationToken(VerificationTokenEntity verificationTokenEntity) {
//        this.verificationTokenEntity = verificationTokenEntity;
//    }
//
//    public EmailEntity getEmail() {
//        return email;
//    }
//
//    public void setEmail(EmailEntity email) {
//        this.email = email;
//    }
}
