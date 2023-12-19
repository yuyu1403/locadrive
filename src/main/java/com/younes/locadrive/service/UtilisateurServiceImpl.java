package com.younes.locadrive.service;

import com.younes.locadrive.dto.ClientUpdateDTO;
import com.younes.locadrive.dto.UtilisateurDTO;
import com.younes.locadrive.model.Client;
import com.younes.locadrive.model.Utilisateur;
import com.younes.locadrive.model.enums.UserRole;
import com.younes.locadrive.repos.ClientRepository;
import com.younes.locadrive.repos.UtilisateurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//COMMENT THIS CODE
@Service
public class UtilisateurServiceImpl implements UtilisateurService{

    private final UtilisateurRepository utilisateurRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, ClientRepository clientRepository, PasswordEncoder passwordEncoder){
        this.utilisateurRepository = utilisateurRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional
    public Client createClient(Client client, Utilisateur utilisateur) {
        String encodedPassword = passwordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(encodedPassword);

        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        client.setUtilisateur(savedUtilisateur);
        return clientRepository.save(client);
    }

    //delete is not implemented in the controller for the moment
//    @Override
//    @Transactional
//    public void deleteClient(Integer clientId) {
//        clientRepository.deleteById(clientId);
//        utilisateurRepository.deleteById(clientId);
//    }

    @Override
    public List<UtilisateurDTO> getAllUtilisateurs() {
        return utilisateurRepository.findAll() //
                .stream()
                //converts the list of users into a Java Stream which is a Java API that allows to perform operations such as (map, filter, reduce)
                .map(this::convertEntityToDto)
                //.map takes convertEntityToDto as a param, and applies it to each element (kind of a for each loop): Utilisateur => UtilisateurDTO
                .collect(Collectors.toList()); //after mapping, collect gathers the transformed objects into a new list.
    }

    @Override
    public List<UtilisateurDTO> getAllClients() {
        return utilisateurRepository.findByRole(UserRole.CLIENT).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    //to do
    @Override
    public Optional<Client> getClientById(Integer clientId) {
        return Optional.empty();
    }

    @Override
    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    // change to update client ? also for client creation?
//    @Override
//    @Transactional
//    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
//        return utilisateurRepository.findById(utilisateur.getUserId())
//                .map(existingUtilisateur -> {
//                    existingUtilisateur.setName(utilisateur.getName());
//                    existingUtilisateur.setSurname(utilisateur.getSurname());
//                    // Set other fields as necessary
//                    return utilisateurRepository.save(existingUtilisateur);
//                })
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + utilisateur.getUserId()));
//    }

    // update doesn't work but it used to????
    @Override
    @Transactional
    public ClientUpdateDTO updateClient(ClientUpdateDTO clientUpdateDTO){
        //Fetch existing Utilisateur
        Utilisateur existingUtilisateur = utilisateurRepository.findById(clientUpdateDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + clientUpdateDTO.getUserId()));

        // Update only the fields present in the DTO
        if (clientUpdateDTO.getSurname() != null) existingUtilisateur.setSurname(clientUpdateDTO.getSurname());
        if (clientUpdateDTO.getName() != null) existingUtilisateur.setName(clientUpdateDTO.getName());
        if (clientUpdateDTO.getEmail() != null) existingUtilisateur.setEmail(clientUpdateDTO.getEmail());
        if (clientUpdateDTO.getAddress() != null) existingUtilisateur.setAddress(clientUpdateDTO.getAddress());
        if (clientUpdateDTO.getPhone() != null) existingUtilisateur.setPhone(clientUpdateDTO.getPhone());
        if (clientUpdateDTO.getPassword() != null && !clientUpdateDTO.getPassword().isEmpty()) {
            existingUtilisateur.setPassword(passwordEncoder.encode(clientUpdateDTO.getPassword()));
        }
        // Handle password separately

        // Save the updated Utilisateur
        utilisateurRepository.save(existingUtilisateur);

        Client existingClient = clientRepository.findById(clientUpdateDTO.getUserId())
                .orElseThrow(() -> new RuntimeException(("User not found with id: " + clientUpdateDTO.getUserId())));

        // Update only the fields present in the DTO
        if (clientUpdateDTO.getLicenceNumber() != null) existingClient.setLicenceNumber(clientUpdateDTO.getLicenceNumber());

        clientRepository.save(existingClient);

        return clientUpdateDTO;
    }

    //DTO conversion methods:
    @Override
    public UtilisateurDTO convertEntityToDto(Utilisateur utilisateur) {
        // the builder design pattern allows to write cleaner and simpler code than if we had
        // used the new keyword.
        return UtilisateurDTO.builder()
                .userId(utilisateur.getUserId())
                .surname(utilisateur.getSurname())
                .name(utilisateur.getName())
                .email(utilisateur.getEmail())
                .address(utilisateur.getAddress())
                .phone(utilisateur.getPhone())
                .role(utilisateur.getRole())
                .build();
    }
    @Override
    public ClientUpdateDTO convertClientAndUtilisateurToClientUpdateDto(Client client, Utilisateur utilisateur){
        return ClientUpdateDTO.builder()
                .surname(utilisateur.getSurname())
                .name(utilisateur.getName())
                .email(utilisateur.getEmail())
                .address(utilisateur.getAddress())
                .phone(utilisateur.getPhone())
                .password(utilisateur.getPassword())
                .licenceNumber(client.getLicenceNumber())
                .build();
    }

    @Override
    public Utilisateur convertClientUpdateDtoToUtilisateur(ClientUpdateDTO clientUpdateDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setSurname(clientUpdateDTO.getSurname());
        utilisateur.setName(clientUpdateDTO.getName());
        utilisateur.setEmail(clientUpdateDTO.getEmail());
        utilisateur.setAddress(clientUpdateDTO.getAddress());
        utilisateur.setPhone(clientUpdateDTO.getPhone());
        utilisateur.setPassword(clientUpdateDTO.getPassword()); // Handle password encryption if necessary
        // Role is not included in the DTO, set it as needed or leave it unchanged
        return utilisateur;
    }

    @Override
    public Client convertClientUpdateDtoToClient(ClientUpdateDTO clientUpdateDTO) {
        Client client = new Client();
        client.setLicenceNumber(clientUpdateDTO.getLicenceNumber());
        return client;
    }

}