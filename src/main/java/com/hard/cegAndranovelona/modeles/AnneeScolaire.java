package com.hard.cegAndranovelona.modeles;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "annee_scolaire")
public class AnneeScolaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAnnee;
    private int debut;
    private int fin;
    private int estActu;
    @OneToMany(mappedBy = "anneeScolaire",fetch = FetchType.EAGER)
    private List<HistoriqueClasse> historiqueClasse;
}
