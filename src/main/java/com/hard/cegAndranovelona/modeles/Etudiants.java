package com.hard.cegAndranovelona.modeles;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
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

    @OneToMany(mappedBy = "etudiant",fetch = FetchType.EAGER)
    private List<HistoriqueClasse> historiqueClasses;
}