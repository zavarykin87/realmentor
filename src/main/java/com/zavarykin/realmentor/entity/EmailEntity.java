package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "emails")
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "address", nullable = false, unique = true)
    private String address;
    @OneToOne(mappedBy = "email")
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity userEntity) {
        this.user = userEntity;
    }
}
