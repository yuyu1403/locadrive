package com.younes.locadrive.repos;

import com.younes.locadrive.model.Utilisateur;
import com.younes.locadrive.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Spring repository is a Java interface that extends JpaRepository interface.
// This interface has all the needed methods to be able to work with data (save, findById...)
// Entities and their corresponding repositories work in pairs to form the application's data layer
// kind of like pairing sql tables and their corresponding stored procedures

@Repository
public interface UtilisateurRepository extends JpaRepository <Utilisateur, Integer> {
    //Parameters: Entity, IDType
    // when leaving the interface empty this means we inherit all the methods provided by JpaRepository
    // if needed we can add custom methods here.

    Optional<Utilisateur> findByEmail(String email);

    List<Utilisateur> findByRole(UserRole role);

}
