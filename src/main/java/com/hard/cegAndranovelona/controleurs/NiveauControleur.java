package com.hard.cegAndranovelona.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hard.cegAndranovelona.modeles.AnneeScolaire;
import com.hard.cegAndranovelona.modeles.Niveau;
import com.hard.cegAndranovelona.modeles.Section;
import com.hard.cegAndranovelona.service.AnneeScolaireService;
import com.hard.cegAndranovelona.service.NiveauService;
import com.hard.cegAndranovelona.service.SectionService;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class NiveauControleur {
    @Autowired
    private NiveauService service;
    @Autowired 
    private AnneeScolaireService anneeScolaireService;
    @Autowired
    private SectionService sectionService;

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

    @GetMapping("/niveau/sections")
    public String listeSectionNiveauAnnee(@RequestParam long id_niveau,@RequestParam long id_anneScolaire,Model model){
        Niveau niveau=service.getById(id_niveau).get();
        AnneeScolaire anneeScolaire=anneeScolaireService.getById(id_anneScolaire).get();
        List<Section> sections=sectionService.getByAnneeEtNiveau(anneeScolaire, niveau);
        model.addAttribute("sections",sections);
        return "pages/section/liste";
    }

    @GetMapping("api/niveau/sections")
    public ResponseEntity<List<Section>> listeSectionNiveau(@RequestParam long id_niveau){
        AnneeScolaire actu=anneeScolaireService.estActu();
        Niveau niveau=service.getById(id_niveau).get();
        List<Section> sections=sectionService.getByAnneeEtNiveau(actu, niveau);
        return new ResponseEntity<>(sections, HttpStatus.OK);
    }

}
