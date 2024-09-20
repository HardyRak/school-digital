package com.hard.cegAndranovelona.modeles;

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
@Table(name = "historique_classe")
public class HistoriqueClasse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idHistorique;

    @ManyToOne
    @JoinColumn(name = "id_section")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiants etudiant;

}
