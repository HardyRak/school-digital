package com.hard.cegAndranovelona.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.hard.cegAndranovelona.modeles.Etudiants;
import com.hard.cegAndranovelona.modeles.Section;
import java.util.List;


@Repository
public interface EtudiantsRepository extends JpaRepository<Etudiants, Long> {
    Page<Etudiants> findAll(Pageable pageable);
    List<Etudiants> findBySection(Section section,Sort order);
    List<Etudiants> findByMatricule(String matricule);

    @Query("SELECT c FROM Etudiants c WHERE " +
       "LOWER(CONCAT(c.nom, ' ', c.prenom)) LIKE LOWER(CONCAT('%', :nom, '%'))")
    List<Etudiants> rechercheParNom(@Param("nom") String nom);


    
}