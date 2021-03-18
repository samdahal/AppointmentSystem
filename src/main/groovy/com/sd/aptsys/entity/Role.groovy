package com.sd.aptsys.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
@NoArgsConstructor
public class Role {
    @Id @GeneratedValue
     Long id;

     String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
     Set<User> users = new HashSet<>();


    public Role(String role) {
        this.role = role;
    }

    Role() {
    }

    @Override
    public String toString() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return role.equals(role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }
}

