package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tokens")
public class VerificationTokenEntity {

    @Id
    @Column(name = "token", nullable = false, unique = true)
    private String token;

    // TODO добавить поле expiryDate

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, unique = true,
            foreignKey = @ForeignKey(name = "fk_token_to_user"))
    private UserEntity userEntity;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


}
