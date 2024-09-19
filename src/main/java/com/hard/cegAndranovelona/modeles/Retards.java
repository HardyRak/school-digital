package com.hard.cegAndranovelona.modeles;

import java.time.ZonedDateTime;

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
@Table(name = "retards")
public class Retards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRetard;
    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiants etudiant;
    private ZonedDateTime dateHeureEntre;
    @ManyToOne
    @JoinColumn(name = "id_annee_scolaire")
    private AnneeScolaire anneeScolaire;
    private String motif;
}
