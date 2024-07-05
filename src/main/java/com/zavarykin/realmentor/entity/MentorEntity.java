package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mentors")
public class MentorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "mentor")
    private AccountEntity account;

}
