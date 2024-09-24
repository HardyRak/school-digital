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
@Table(name = "Certificat_scolarite")
public class CertificatScolarite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCertificat;
    @ManyToOne
    @JoinColumn(name = "id_annee_scolaire", nullable = false)
    private AnneeScolaire anneeScolaire;
    @ManyToOne
    @JoinColumn(name = "id_etudiants", nullable = false)
    private Etudiants etudiants;
}