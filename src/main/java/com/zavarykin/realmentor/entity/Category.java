package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    // IT, Business (marketing, management), Design, Engineering, Science, Language
//    Category:
//        - Specializations:
//            - Skills

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Specialization> specializations;
}
