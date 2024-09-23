package com.hard.cegAndranovelona.modeles;

import java.sql.Date;

import com.hard.cegAndranovelona.function.Function;

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
@Entity
@Setter
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

    public void setDate_debut(Date date_debut) throws Exception {
        if (date_debut==null || date_debut.after(Function.getCurrenDate())) {
            throw new Exception("Date imposible");
        }
        this.date_debut = date_debut;
    }

    public void setMotif(String motif) throws Exception {
        if (motif.equals("") || motif.isEmpty()) {
            throw new Exception("Motif obligatoire");
        }
        this.motif = motif;
    }


}