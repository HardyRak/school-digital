package com.hard.cegAndranovelona.modeles;

import java.util.List;

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
@Table(name = "section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSection;
    private String section;
    @ManyToOne
    @JoinColumn(name = "id_niveau", nullable = false)
    private Niveau niveau;
    @OneToMany(mappedBy = "section",fetch = FetchType.EAGER)
    private List<HistoriqueClasse> historiqueClasses;
}
