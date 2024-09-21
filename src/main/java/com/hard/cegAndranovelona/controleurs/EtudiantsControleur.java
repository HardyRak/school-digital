package com.hard.cegAndranovelona.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hard.cegAndranovelona.function.Function;
import com.hard.cegAndranovelona.modeles.AnneeScolaire;
import com.hard.cegAndranovelona.modeles.Avertissement;
import com.hard.cegAndranovelona.modeles.Etudiants;
import com.hard.cegAndranovelona.modeles.HistoriqueClasse;
import com.hard.cegAndranovelona.modeles.Niveau;
import com.hard.cegAndranovelona.modeles.Section;
import com.hard.cegAndranovelona.service.AnneeScolaireService;
import com.hard.cegAndranovelona.service.AvertissementService;
import com.hard.cegAndranovelona.service.EtudiantsService;
import com.hard.cegAndranovelona.service.NiveauService;
import com.hard.cegAndranovelona.service.SectionService;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class EtudiantsControleur {
    @Autowired
    private EtudiantsService service;
    @Autowired
    private AvertissementService avertissementService;
    @Autowired
    private NiveauService niveauService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private AnneeScolaireService anneeScolaireService;

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

    @GetMapping("/etudiant/certificat")
    public String certificatScolarite(@RequestParam long id_etudiant,Model model){
        Etudiants etudiants=service.getById(id_etudiant).get();
        model.addAttribute("etudiant",etudiants);
        model.addAttribute("date",Function.getCurrenDate());
        return "pages/etudiant/certifi";
    }

    @GetMapping("/etudiant/recherche")
    public String pageRecherche(){
        return "pages/etudiant/recherche";
    }

    @GetMapping("/etudiant/recherche/resultat")
    public ResponseEntity<List<Etudiants>> recherche(@RequestParam String parametre){
        try {
            Integer.parseInt(parametre);
            List<Etudiants> etudiants=service.getByMatricule(parametre);
            return new ResponseEntity<>(etudiants, HttpStatus.OK);
        } catch (Exception e) {
            List<Etudiants> etudiants=service.rechercheParNom(parametre);
            return new ResponseEntity<>(etudiants, HttpStatus.OK);
        }
    }

    @GetMapping("/etudiant/formulaire/ajout")
    public String formulaireAjout(Model model){
        List<Niveau> niveaux=niveauService.getAll();
        AnneeScolaire actu=anneeScolaireService.estActu();
        List<Section> sections=sectionService.getByAnneeEtNiveau(actu, niveaux.get(0));
        model.addAttribute("niveaux",niveaux);
        model.addAttribute("sections",sections);
        return "pages/etudiant/formulaireAjout";
    }

    @Transactional
    @PostMapping("/etudiant/ajout")
    public String validationAjout(
        @RequestParam String nom,@RequestParam String prenom,
        @RequestParam String naissance, @RequestParam String lieuNaissance,
        @RequestParam String adresse, @RequestParam String genre,
        @RequestParam String pere,@RequestParam String mere,
        @RequestParam String adresseParent, @RequestParam String contactParent,
        @RequestParam String tuteur, @RequestParam String adresseTuteur, 
        @RequestParam String contactTuteur,
        @RequestParam long id_niveau, @RequestParam long id_section,
        @RequestParam String ecoleAnterieur
    ){
        try {
            Etudiants etudiants = new Etudiants(
                "1235",nom, prenom, Function.stringToDate(naissance), lieuNaissance, adresse, genre,
                pere, mere, adresseParent, contactParent, tuteur, adresseTuteur,
                contactTuteur, sectionService.getById(id_section).get(), ecoleAnterieur
            );
            etudiants=service.saveOrUpdate(etudiants);
            Function.generateQR(etudiants);
            etudiants=service.saveOrUpdate(etudiants);
            HistoriqueClasse historiqueClasse=new HistoriqueClasse();
            historiqueClasse.setEtudiant(etudiants);
            historiqueClasse.setSection(etudiants.getSection());
            return "redirect:/etudiant/profil?id_etudiant="+etudiants.getIdEtudiants();
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/etudiant/formulaire/ajout?msg=" + e.getMessage() +
                   "&nom=" + nom +
                   "&prenom=" + prenom +
                   "&naissance=" + naissance +
                   "&lieuNaissance=" + lieuNaissance +
                   "&adresse=" + adresse +
                   "&pere=" + pere +
                   "&mere=" + mere +
                   "&adresseParent=" + adresseParent +
                   "&contactParent=" + contactParent +
                   "&tuteur=" + tuteur +
                   "&adresseTuteur=" + adresseTuteur +
                   "&contactTuteur=" + contactTuteur +
                   "&ecoleAnterieur=" + ecoleAnterieur;
        }

    }

}
