package com.younes.locadrive.service;

import com.younes.locadrive.dto.ClientUpdateDTO;
import com.younes.locadrive.dto.UtilisateurDTO;
import com.younes.locadrive.model.Client;
import com.younes.locadrive.model.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    //Creating a client:
    Client createClient(Client client, Utilisateur utilisateur);

    //Deleting a client:
//    void deleteClient(Integer clientId);

    //Getting one client by id:
    Optional<Client> getClientById(Integer clientId);

    //Getting one client by email:
    Optional<Utilisateur> getUtilisateurByEmail(String email);

    //Updating a client:
    ClientUpdateDTO updateClient(ClientUpdateDTO clientUpdateDTO);

    //Getting all users:
    List<UtilisateurDTO> getAllUtilisateurs();

    //Getting all clients:
    List<UtilisateurDTO> getAllClients();

    //Entity to DTO converting methods:
    UtilisateurDTO convertEntityToDto(Utilisateur utilisateur);

    // Convert ClientUpdateDTO to Client and Utilisateur entity
    Client convertClientUpdateDtoToClient(ClientUpdateDTO clientUpdateDTO);
    Utilisateur convertClientUpdateDtoToUtilisateur(ClientUpdateDTO clientUpdateDTO);

    // Convert Client and Utilisateur entities to ClientUpdateDTO
    ClientUpdateDTO convertClientAndUtilisateurToClientUpdateDto(Client client, Utilisateur utilisateur);



}
