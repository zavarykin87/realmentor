package com.zavarykin.realmentor.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_details")
public class UserDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private UserDetailsEntity.Gender gender;

    public enum Gender {
        MALE,
        FEMALE
    }

}
