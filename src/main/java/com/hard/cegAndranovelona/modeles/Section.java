package com.hard.cegAndranovelona.modeles;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSection;
    private String section;
    @ManyToOne
    @JoinColumn(name = "id_equipe", nullable = false)
    private Niveau niveau;
}
