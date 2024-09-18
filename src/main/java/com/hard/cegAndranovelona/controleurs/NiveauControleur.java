package com.hard.cegAndranovelona.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hard.cegAndranovelona.modeles.Niveau;
import com.hard.cegAndranovelona.service.NiveauService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class NiveauControleur {
    @Autowired
    private NiveauService service;
    @CrossOrigin(origins = "*")
    @GetMapping("/api/niveau")
    public ResponseEntity<List<Niveau>> getAllNiveauApi() {
        List<Niveau> entities = service.getAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/niveau")
    public String getAllniveau(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue="") String tri, Model model) {
        Page<Niveau> entities = service.getAll(page,5,tri);
        model.addAttribute("niveaus",entities);
        return "pages/niveau/liste";
    }

    @GetMapping("/niveau/ajout")
    public String formInsert(Model model) {
        return "pages/niveau/ajout";
    }
}
