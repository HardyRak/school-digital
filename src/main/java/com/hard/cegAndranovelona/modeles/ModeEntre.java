package com.hard.cegAndranovelona.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mode_entre")
@Getter
@Setter
public class ModeEntre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMode;
    private String mode;
}