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
@Table(name = "avertissement")
public class Avertissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAvertisement;

    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiants etudiant;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_annee_scolaire")
    private AnneeScolaire anneeScolaire;
    private String motif;
}
