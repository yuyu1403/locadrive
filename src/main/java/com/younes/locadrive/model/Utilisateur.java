package com.younes.locadrive.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.younes.locadrive.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//for general comments on entity implementations, see Vehicle class


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UTILISATEUR")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "user_name", nullable = false, length = 50)
    private String name;

    @Column(name = "user_email", unique = true,nullable = false, length = 100)
    private String email;

    @Column(name = "user_adress", nullable = false, length = 250)
    private String address;

    @Column(name = "user_phone", unique = true, nullable = false, length = 20)
    private String phone;

    @Column(name = "user_pwd", nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole role;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    //mappedBy indicates the field/variable in Client entity that owns the relationship (utilisateur).
    //this field in Client in expected to have a corresponding 1to1 annotation pointing back
    // CascadeType.ALL indicates that all operations performed on a parent entity should cascade to its child entity
    // cascade in JPA is essential for managing the persistence state of entity relationships (see doc)
    // optional = true means that a utilisateur must not always be a client [in DB: foreign key in Client can be null]
    private Client client;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Staff staff;

    @OneToMany(mappedBy = "utilisateur")
    private List<Reservation> reservations; //needs to be a container as we can store multiple reservations for one user


}
