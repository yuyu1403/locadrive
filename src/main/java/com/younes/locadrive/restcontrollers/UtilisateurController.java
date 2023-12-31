package com.younes.locadrive.restcontrollers;

import com.younes.locadrive.dto.ClientUpdateDTO;
import com.younes.locadrive.dto.UtilisateurDTO;
import com.younes.locadrive.model.Client;
import com.younes.locadrive.service.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

                                            //**** CREATE METHODS ****

    //Create a new client account:
    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client){
        Client newClient = utilisateurService.createClient(client, client.getUtilisateur());
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }
                                            //**** READ METHODS ****

    @GetMapping
    public ResponseEntity<List<UtilisateurDTO>> getAllUtilisateurs() {
        List<UtilisateurDTO> utilisateurs = utilisateurService.getAllUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<UtilisateurDTO>> getAllClients() {
        List<UtilisateurDTO> clients = utilisateurService.getAllClients();
        return ResponseEntity.ok(clients);
    }


                                            //**** UPDATE METHODS ****
    @PutMapping("/clients/{id}")
    public ResponseEntity<ClientUpdateDTO> updateClient(@PathVariable Integer id, @RequestBody ClientUpdateDTO clientUpdateDTO) {

        if (!clientUpdateDTO.getUserId().equals(id)) {
            return ResponseEntity.badRequest().body(null);
        }

        ClientUpdateDTO updatedClient = utilisateurService.updateClient(clientUpdateDTO);
        return ResponseEntity.ok(updatedClient);
    }
                                            //**** DELETE METHODS ***
//Many errors when trying to delete a client. For the moment it will be done on request directly
//by admin in the DB.
}
