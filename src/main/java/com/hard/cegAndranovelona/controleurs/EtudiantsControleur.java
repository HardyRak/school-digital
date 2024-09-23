package com.hard.cegAndranovelona.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hard.cegAndranovelona.function.Function;
import com.hard.cegAndranovelona.modeles.Absence;
import com.hard.cegAndranovelona.modeles.AnneeScolaire;
import com.hard.cegAndranovelona.modeles.Avertissement;
import com.hard.cegAndranovelona.modeles.Etudiants;
import com.hard.cegAndranovelona.modeles.HistoriqueClasse;
import com.hard.cegAndranovelona.modeles.Niveau;
import com.hard.cegAndranovelona.modeles.Section;
import com.hard.cegAndranovelona.service.AbsenceService;
import com.hard.cegAndranovelona.service.AnneeScolaireService;
import com.hard.cegAndranovelona.service.AvertissementService;
import com.hard.cegAndranovelona.service.EtudiantsService;
import com.hard.cegAndranovelona.service.HistoriqueClasseService;
import com.hard.cegAndranovelona.service.NiveauService;
import com.hard.cegAndranovelona.service.SectionService;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;


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
    @Autowired
    private HistoriqueClasseService historiqueClasseService;
    @Autowired
    private AbsenceService absenceService;
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
        List<Absence> absences=absenceService.getAbsenceByEtudiant(etudiants);
        model.addAttribute("etudiant",etudiants);
        model.addAttribute("avertissements",avertissements);
        model.addAttribute("absences",absences);
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
    public ResponseEntity<?> recherche(@RequestParam String parametre){
        if (parametre.toCharArray().length<3) {
            return new ResponseEntity<>("3 lettres minimum",HttpStatus.BAD_REQUEST);   
        }
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
            historiqueClasseService.saveOrUpdate(historiqueClasse);
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

    @PostMapping("/etudiant/modification")
    public String modification(
        @RequestParam long id_etudiant,
        @RequestParam String nom, @RequestParam String prenom,
        @RequestParam String naissance, @RequestParam String lieuNaissance,@RequestParam String adresse,
        @RequestParam String pere,@RequestParam String mere, @RequestParam String tuteur,
        @RequestParam String adresseParent,@RequestParam String adresseTuteur, @RequestParam String contactParent, @RequestParam String contactTuteur
    ){

        try {
            Etudiants etudiants=service.getById(id_etudiant).get();
            etudiants.setNom(nom);
            etudiants.setPrenom(prenom);
            etudiants.setDateNaissance(Function.stringToDate(naissance));
            etudiants.setLieuNaissance(lieuNaissance);
            etudiants.setAdresse(adresse);
            etudiants.setNomPere(pere);
            etudiants.setNomMere(mere);
            etudiants.setNomTuteur(tuteur);
            etudiants.setAdresseParent(adresseParent);
            etudiants.setAdresseTuteur(adresseTuteur);
            etudiants.setContactParent(contactParent);
            etudiants.setContactTuteur(contactTuteur);
            Function.generateQR(etudiants);
            service.saveOrUpdate(etudiants);
            return "redirect:/etudiant/profil?id_etudiant="+etudiants.getIdEtudiants()+"&msgV=Modification reussi";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/etudiant/profil?msg=" + e.getMessage() +
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
                   "&contactTuteur=" + contactTuteur+
                   "&id_etudiant="+id_etudiant;
        }
        
    }

    @PostMapping("/etudiant/avertissement")
    public String avertissement(@RequestParam String cause,@RequestParam String motif,@RequestParam long id_etudiant){
        Etudiants etudiants=service.getById(id_etudiant).get();
        Avertissement avertissement=new Avertissement();
        try {
            avertissement.setCause(cause);
            avertissement.setMotif(motif);
            avertissement.setDate(Function.getCurrenDate());
            avertissement.setEtudiant(etudiants);
            avertissement.setAnneeScolaire(etudiants.getSection().getAnneeScolaire());
            avertissementService.saveOrUpdate(avertissement);
            return "redirect:/etudiant/profil?id_etudiant="+etudiants.getIdEtudiants()+"#avertissement";
        } catch (Exception e) {
            return "redirect:/etudiant/profil?msg="+e.getMessage()+"&id_etudiant="+id_etudiant+"#avertissement";
        }
    }

    @PostMapping("/etudiant/absence")
    public String postMethodName(@RequestParam String date_debut,@RequestParam long id_etudiant,@RequestParam String motif) {
        Etudiants etudiants=service.getById(id_etudiant).get();
        Absence absence=new Absence();
        try {
            absence.setAnneeScolaire(etudiants.getSection().getAnneeScolaire());
            absence.setDate_debut(Function.stringToDate(date_debut));
            absence.setEtudiant(etudiants);
            absence.setMotif(motif);
            absence.setDate_fin(Function.getCurrenDate());
            absenceService.saveOrUpdate(absence);
            return "redirect:/etudiant/profil?id_etudiant="+etudiants.getIdEtudiants()+"#absence";
        } catch (Exception e) {
            return "redirect:/etudiant/profil?msg="+e.getMessage()+"&id_etudiant="+id_etudiant+"#avertissement";
        }
    }
    

}
