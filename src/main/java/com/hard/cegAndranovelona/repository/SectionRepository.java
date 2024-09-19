package com.hard.cegAndranovelona.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hard.cegAndranovelona.modeles.AnneeScolaire;
import com.hard.cegAndranovelona.modeles.Niveau;
import com.hard.cegAndranovelona.modeles.Section;
import java.util.List;


@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Page<Section> findAll(Pageable pageable);
    List<Section> findByAnneeScolaireAndNiveau(AnneeScolaire anneeScolaire,Niveau niveau);
}
    