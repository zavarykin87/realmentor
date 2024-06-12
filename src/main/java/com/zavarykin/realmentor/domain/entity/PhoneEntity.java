package com.zavarykin.realmentor.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "phones")
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private String number;

    @OneToOne()
    private UserEntity userEntity;
}
