package com.younes.locadrive.restcontrollers;

import com.younes.locadrive.dto.UtilisateurDTO;
import com.younes.locadrive.model.Client;
import com.younes.locadrive.model.Utilisateur;
import com.younes.locadrive.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        Client newClient = utilisateurService.createClient(client, client.getUtilisateur());
        return ResponseEntity.ok(newClient);
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

                                            //**** DELETE METHODS ***
//Many errors when trying to delete a client. For the moment it will be done on request directly
//by admin in the DB.
}
