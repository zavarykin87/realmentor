package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "emails")
public class EmailEntity {

    @Id
    @Column(name = "address", nullable = false, unique = true)
    private String address;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, unique = true,
                foreignKey = @ForeignKey(name = "fk_email_to_user"))
    private UserEntity userEntity;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
