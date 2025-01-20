package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "specialization")
public class Specialization {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "name", nullable = false)
    private Category category;
}
