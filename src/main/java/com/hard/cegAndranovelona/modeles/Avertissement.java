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
@Entity
@Setter
@Table(name = "avertissement")
public class Avertissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAvertisement;
    private String cause;
    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiants etudiant;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_annee_scolaire")
    private AnneeScolaire anneeScolaire;
    private String motif;

    public void setCause(String cause) throws Exception {
        if (cause.equals("") || cause.isEmpty()) {
            throw new Exception("Cause obligatoire");
        }
        this.cause = cause;
    }
    public void setMotif(String motif) throws Exception {
        if (motif.equals("") || motif.isEmpty()) {
            throw new Exception("motif obligatoire");
        }
        this.motif = motif;
    }

}
