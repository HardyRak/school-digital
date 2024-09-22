package com.hard.cegAndranovelona.modeles;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "etudiants")
public class Etudiants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEtudiants;

    @Column(unique = true)
    private String matricule;

    private String nom;

    private String prenom;

    private Date dateNaissance;

    private String lieuNaissance;

    private String adresse;

    private String nomPere;
    private String nomMere;
    private String contactParent;
    private String adresseParent;

    @Column(nullable = true)
    private String nomTuteur;
    @Column(nullable = true)
    private String contactTuteur;
    @Column(nullable = true)
    private String adresseTuteur;

    private String ecoleAnterieur;

    @ManyToOne
    @JoinColumn(name = "id_section", nullable = false)
    private Section section;

    private String genre;

    @Column(columnDefinition = "text",nullable = true)
    private String image;

    @Column(columnDefinition = "text",nullable = true)
    private String qr;

    public void setNom(String nom) throws Exception{
        if (nom.equals("") || nom.isEmpty()) {
            throw new Exception("Vous avez oublié le nom");
        }
        this.nom=nom;
    }

    public void setPrenom(String preNom) throws Exception{
        if (preNom.equals("") || preNom.isEmpty()) {
            throw new Exception("Vous avez oublié le prenom");
        }
        this.prenom=preNom;
    }

    public void setDateNaissance(Date dateNaissance) throws Exception{
        if (dateNaissance==null) {
            throw new Exception("Vous avez oublié le date de naissance");
        }
        this.dateNaissance=dateNaissance;
    }

    public void setAdresse(String adresse) throws Exception{
        if (adresse.equals("") || adresse.isEmpty()) {
            throw new Exception("Vous avez oublié l'adresse de l'etudiant");
        }
        this.adresse=adresse;
    }

    public void setGenre(String genre) throws Exception{
        if (genre.equals("") || genre.isEmpty()) {
            throw new Exception("Vous avez oublié le genre de l'etudiant");
        }
        if (!genre.equals("F") && !genre.equals("G")) {
            throw new Exception(genre+" Ce genre n'existe pas");
        }
        this.genre=genre;
    }

    public void setEcoleAnterieur(String ecoleAnterieur) throws Exception{
        if (ecoleAnterieur.equals("") || ecoleAnterieur.isEmpty()) {
            throw new Exception("Vous avez oublié l'ecole anterieur");
        }
        this.ecoleAnterieur=ecoleAnterieur;
    }

    public Etudiants(String matricule,String nom, String prenom, Date naissance, String lieuNaissance, 
                     String adresse, String genre, String pere, String mere, 
                     String adresseParent, String contactParent, String tuteur, 
                     String adresseTuteur, String contactTuteur, Section section, 
                     String ecoleAnterieur) throws Exception {
        setNom(nom);
        setPrenom(prenom);
        setDateNaissance(naissance);
        setLieuNaissance(lieuNaissance);
        setAdresse(adresse);
        setGenre(genre);
        setNomPere(pere);
        setNomMere(mere);
        setAdresseParent(adresseParent);
        setContactParent(contactParent);
        setNomTuteur(tuteur);
        setAdresseTuteur(adresseTuteur);
        setContactTuteur(contactTuteur);
        setSection(section);
        setEcoleAnterieur(ecoleAnterieur);
        setMatricule(matricule);
    }


}