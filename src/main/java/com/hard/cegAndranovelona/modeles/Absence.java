package com.hard.cegAndranovelona.modeles;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "absence")
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAbsence;

    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiants etudiant;
    private Date date_debut;
    private Date date_fin;
    @ManyToOne
    @JoinColumn(name = "id_annee_scolaire")
    private AnneeScolaire anneeScolaire;
    private String motif;
}