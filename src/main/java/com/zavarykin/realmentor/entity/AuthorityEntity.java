package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "authorities")
@IdClass(AuthorityEntity.DerivedId.class)
public class AuthorityEntity {

    @Id
    @Column(name = "authority", nullable = false)
    private String authority;

    @Id
    private String username;

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false,
                foreignKey = @ForeignKey(name = "fk_authority_to_user"))
    private UserEntity userEntity;

    public AuthorityEntity(String authority, String username) {
        this.authority = authority;
        this.username = username;
    }

    public AuthorityEntity() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        AuthorityEntity that = (AuthorityEntity) object;
        return Objects.equals(authority, that.authority)
                && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authority, username);
    }

    public class DerivedId {

        private String authority;
        private String username;

        public DerivedId(String authority, String username) {
            this.authority = authority;
            this.username = username;
        }

        public String getAuthority() {
            return authority;
        }

        public String getUsername() {
            return username;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            DerivedId derivedId = (DerivedId) object;
            return Objects.equals(authority, derivedId.authority)
                    && Objects.equals(username, derivedId.username);
        }

        @Override
        public int hashCode() {
            return Objects.hash(authority, username);
        }
    }

}
