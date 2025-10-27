package com.etiya.authservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;


    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private Set<UserRole> userRoles;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name.toUpperCase(); //ADMIN
    }
}