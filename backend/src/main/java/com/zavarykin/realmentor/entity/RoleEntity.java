package com.zavarykin.realmentor.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(
        name = "uk_role_name_user",
        columnNames = {
                "name", "user_id"
        }
))
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private RoleName name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public RoleEntity(RoleName name) {
        this.name = name;
    }
}

