package com.hard.cegAndranovelona.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hard.cegAndranovelona.modeles.Avertissement;
import com.hard.cegAndranovelona.service.AvertissementService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class AvertissementControleur {
    @Autowired
    private AvertissementService service;
    @CrossOrigin(origins = "*")
    @GetMapping("/api/avertissement")
    public ResponseEntity<List<Avertissement>> getAllAvertissementApi() {
        List<Avertissement> entities = service.getAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/avertissement")
    public String getAllavertissement(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue="") String tri, Model model) {
        Page<Avertissement> entities = service.getAll(page,5,tri);
        model.addAttribute("avertissements",entities);
        return "pages/avertissement/liste";
    }

    @GetMapping("/avertissement/ajout")
    public String formInsert(Model model) {
        return "pages/avertissement/ajout";
    }
}
