package com.hard.cegAndranovelona.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hard.cegAndranovelona.modeles.Etudiants;

@Repository
public interface EtudiantsRepository extends JpaRepository<Etudiants, Long> {
    Page<Etudiants> findAll(Pageable pageable);
}
