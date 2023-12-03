package com.younes.locadrive.repos;

import com.younes.locadrive.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring repository is a Java interface that extends JpaRepository interface.
// This interface has all the needed methods to be able to work with data (save, findById...)
// Entities and their corresponding repositories work in pairs to form the application's data layer
// kind of like pairing sql tables and their corresponding stored procedures

public interface ClientRepository extends JpaRepository <Client, Integer> {
    //Parameters: Entity, IDType
    // when leaving the interface empty this means we inherit all the methods provided by JpaRepository
    // if needed we can add custom methods here.

}

