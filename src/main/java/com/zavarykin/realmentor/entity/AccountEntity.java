package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "registration", nullable = false)
    private LocalDate registration;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "mentor_id", referencedColumnName = "id", unique = true)
    private MentorEntity mentor;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "mentee_id", referencedColumnName = "id", unique = true)
    private MenteeEntity mentee;

    @OneToOne(mappedBy = "account")
    private UserEntity user;
}
