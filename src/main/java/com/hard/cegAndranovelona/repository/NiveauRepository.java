package com.hard.cegAndranovelona.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hard.cegAndranovelona.modeles.Niveau;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Long> {
    Page<Niveau> findAll(Pageable pageable);
}
