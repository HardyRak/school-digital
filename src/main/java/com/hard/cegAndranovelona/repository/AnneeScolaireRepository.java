package com.hard.cegAndranovelona.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hard.cegAndranovelona.modeles.AnneeScolaire;

@Repository
public interface AnneeScolaireRepository extends JpaRepository<AnneeScolaire, Long> {
    Page<AnneeScolaire> findAll(Pageable pageable);
    AnneeScolaire findByEstActu(int estActu);
}