package com.sd.aptsys.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Getter
import lombok.Setter
import org.springframework.data.mongodb.core.mapping.Field

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToMany

@Entity
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @JsonIgnore
    String hashedPassword

    String firstName, lastName, username

    @JsonIgnore
    @Column(columnDefinition = "text")
     String lastestToken

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = [@JoinColumn(name = "user_id")], inverseJoinColumns = [@JoinColumn(name = "role_id")])
     Set<Role> roles = new HashSet<>()


    @Getter @Setter @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Appointment> appointments

    Date createDate
    Date updateDate

    boolean locked = false
    boolean banned = false


}
