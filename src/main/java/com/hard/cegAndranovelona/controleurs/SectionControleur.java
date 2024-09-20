package com.hard.cegAndranovelona.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hard.cegAndranovelona.modeles.HistoriqueClasse;
import com.hard.cegAndranovelona.modeles.Section;
import com.hard.cegAndranovelona.service.HistoriqueClasseService;
import com.hard.cegAndranovelona.service.SectionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class SectionControleur {
    @Autowired
    private SectionService service;
    @Autowired
    private HistoriqueClasseService historiqueClasseService;
    @CrossOrigin(origins = "*")
    @GetMapping("/api/section")
    public ResponseEntity<List<Section>> getAllSectionApi() {
        List<Section> entities = service.getAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/api/section/etudiants")
    public ResponseEntity<List<HistoriqueClasse>> getEtudiantSection(@RequestParam long id_section){
        Section section=service.getById(id_section).get();
        List<HistoriqueClasse> classes=historiqueClasseService.getBySection(section);
        return new ResponseEntity<>(classes,HttpStatus.OK);
    }

    @GetMapping("/section")
    public String getAllsection(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue="") String tri, Model model) {
        Page<Section> entities = service.getAll(page,5,tri);
        model.addAttribute("sections",entities);
        return "pages/section/liste";
    }

    @GetMapping("/section/ajout")
    public String formInsert(Model model) {
        return "pages/section/ajout";
    }
    
}
