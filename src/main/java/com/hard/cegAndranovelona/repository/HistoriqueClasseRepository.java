package com.hard.cegAndranovelona.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.hard.cegAndranovelona.modeles.HistoriqueClasse;
import com.hard.cegAndranovelona.modeles.Section;

@Repository
public interface HistoriqueClasseRepository extends JpaRepository<HistoriqueClasse, Long> {
    Page<HistoriqueClasse> findAll(Pageable pageable);
    List<HistoriqueClasse> findBySection(Section section,Sort sort);
}
