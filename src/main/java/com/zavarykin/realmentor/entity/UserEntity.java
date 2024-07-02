package com.zavarykin.realmentor.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "email_id", referencedColumnName = "id", nullable = false, unique = true)
    private EmailEntity email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "mentor_id", referencedColumnName = "id", unique = true)
    private MentorEntity mentor;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "student_id", referencedColumnName = "id", unique = true)
    private StudentEntity student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public EmailEntity getEmail() {
        return email;
    }

    public void setEmail(EmailEntity emailEntity) {
        this.email = emailEntity;
    }

    public MentorEntity getMentor() {
        return mentor;
    }

    public void setMentor(MentorEntity mentor) {
        this.mentor = mentor;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }
}
