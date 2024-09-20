package com.hard.cegAndranovelona.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.WriterException;
import com.hard.cegAndranovelona.function.Function;
import com.hard.cegAndranovelona.modeles.Avertissement;
import com.hard.cegAndranovelona.modeles.Etudiants;
import com.hard.cegAndranovelona.service.AvertissementService;
import com.hard.cegAndranovelona.service.EtudiantsService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class EtudiantsControleur {
    @Autowired
    private EtudiantsService service;
    @Autowired
    private AvertissementService avertissementService;
    @CrossOrigin(origins = "*")
    @GetMapping("/api/etudiants")
    public ResponseEntity<List<Etudiants>> getAllEtudiantsApi() {
        List<Etudiants> entities = service.getAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/etudiants")
    public String getAlletudiants(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue="") String tri, Model model) {
        Page<Etudiants> entities = service.getAll(page,5,tri);
        model.addAttribute("etudiantss",entities);
        return "pages/etudiants/liste";
    }

    @GetMapping("/etudiants/ajout")
    public String formInsert(Model model) {
        return "pages/etudiants/ajout";
    }

    @GetMapping("/etudiant/profil")
    public String getProfilEtudiant(@RequestParam long id_etudiant,Model model) {
        Etudiants etudiants=service.getById(id_etudiant).get();
        List<Avertissement> avertissements=avertissementService.getByEtudiant(etudiants);
        model.addAttribute("etudiant",etudiants);
        model.addAttribute("avertissements",avertissements);
        return "pages/etudiant/profil";
    }

    @GetMapping("/findQR")
    public String findQr(){
        Etudiants etudiants=service.getById((long)2).get();
        Function.generateQR(etudiants);
        service.saveOrUpdate(etudiants);
        return "hello";
    }
}
