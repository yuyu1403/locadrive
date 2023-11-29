package com.younes.locadrive.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @Column(name = "cli_id")
    private Integer clientId;

    @Column(name = "cli_licence", unique = true, nullable = false, length = 50)
    private String licenceNumber;

    @OneToOne(fetch = FetchType.LAZY)
    //establishes a 1to1 relation with the Utilisateur entity.Each Client => one Utilisateur
    //the FetchType.LAZY is an attribute of one to one which indicates that the utilisateur details
    // for a client won't be fetched from the DB until accessed in the code
    //(by default the fetch type is EAGER)
    // is practical because we don't necessary need all the user details for a client
    @MapsId // indicates that the the primary key cli_id is the same as the user_id
    @JoinColumn(name = "cli_id", referencedColumnName = "user_id")
    private Utilisateur utilisateur;

}
