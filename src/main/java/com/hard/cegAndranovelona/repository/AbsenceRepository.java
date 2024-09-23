package com.hard.cegAndranovelona.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hard.cegAndranovelona.modeles.Absence;
import com.hard.cegAndranovelona.modeles.Etudiants;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    Page<Absence> findAll(Pageable pageable);
    List<Absence> findByEtudiant(Etudiants etudiant);
}
