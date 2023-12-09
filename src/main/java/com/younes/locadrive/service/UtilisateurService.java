package com.younes.locadrive.service;

import com.younes.locadrive.dto.UtilisateurDTO;
import com.younes.locadrive.model.Client;
import com.younes.locadrive.model.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    //Creating a client:
    Client createClient(Client client, Utilisateur utilisateur);

    //Deleting a client:
    void deleteClient(Integer clientId);

    //Getting one client by id:
    Optional<Client> getClientById(Integer clientId);

    //Getting one client by email:
    Optional<Utilisateur> getUtilisateurByEmail(String email);

    //Updating a client:
    Utilisateur updateUtilisateur(Utilisateur utilisateur);

    //Getting all users:
    List<UtilisateurDTO> getAllUtilisateurs();

    //Getting all clients:
    List<UtilisateurDTO> getAllClients();

    //Entity to DTO converting method:
    UtilisateurDTO convertEntityToDto(Utilisateur utilisateur);

}
