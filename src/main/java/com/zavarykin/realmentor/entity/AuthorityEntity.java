package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "authorities")
public class AuthorityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authority", nullable = false)
    private String authority;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false,
                foreignKey = @ForeignKey(name = "fk_authority_to_user"))
    private UserEntity userEntity;

    public AuthorityEntity(String authority, UserEntity userEntity) {
        this.authority = authority;
        this.userEntity = userEntity;
    }

    public AuthorityEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AuthorityEntity authority1 = (AuthorityEntity) object;
        return Objects.equals(id, authority1.id) && Objects.equals(authority, authority1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }
}
