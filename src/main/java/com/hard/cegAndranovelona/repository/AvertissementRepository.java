package com.hard.cegAndranovelona.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hard.cegAndranovelona.modeles.Avertissement;
import com.hard.cegAndranovelona.modeles.Etudiants;

import java.util.List;


@Repository
public interface AvertissementRepository extends JpaRepository<Avertissement, Long> {
    Page<Avertissement> findAll(Pageable pageable);
    List<Avertissement> findByEtudiant(Etudiants etudiant);
}
