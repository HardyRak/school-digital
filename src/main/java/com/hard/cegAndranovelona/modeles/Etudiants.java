package com.hard.cegAndranovelona.modeles;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
