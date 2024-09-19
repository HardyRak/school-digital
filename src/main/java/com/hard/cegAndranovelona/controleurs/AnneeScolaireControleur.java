package com.hard.cegAndranovelona.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.hard.cegAndranovelona.modeles.AnneeScolaire;
import com.hard.cegAndranovelona.service.AnneeScolaireService;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class AnneeScolaireControleur {
    @Autowired
    private AnneeScolaireService service;
    @CrossOrigin(origins = "*")
    @GetMapping("/api/anneeScolaire")
    public ResponseEntity<List<AnneeScolaire>> getAllAnneeScolaireApi() {
        List<AnneeScolaire> entities = service.getAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/anneeScolaire")
    public String getAllanneeScolaire(Model model) {
        List<AnneeScolaire> entities = service.getAll();
        model.addAttribute("anneeScolaires",entities);
        return "pages/anneeScolaire/liste";
    }

    @GetMapping("/anneeScolaire/ajout")
    public String formInsert(Model model) {
        return "pages/anneeScolaire/ajout";
    }

}
