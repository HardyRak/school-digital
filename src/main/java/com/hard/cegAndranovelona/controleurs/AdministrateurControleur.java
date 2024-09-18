package com.hard.cegAndranovelona.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hard.cegAndranovelona.modeles.Administrateur;
import com.hard.cegAndranovelona.service.AdministrateurService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class AdministrateurControleur {
    @Autowired
    private AdministrateurService service;
    @CrossOrigin(origins = "*")
    @GetMapping("/api/administrateur")
    public ResponseEntity<List<Administrateur>> getAllAdministrateurApi() {
        List<Administrateur> entities = service.getAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/administrateur")
    public String getAlladministrateur(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue="") String tri, Model model) {
        Page<Administrateur> entities = service.getAll(page,5,tri);
        model.addAttribute("administrateurs",entities);
        return "pages/administrateur/liste";
    }

    @GetMapping("/administrateur/ajout")
    public String formInsert(Model model) {
        return "pages/administrateur/ajout";
    }
}
