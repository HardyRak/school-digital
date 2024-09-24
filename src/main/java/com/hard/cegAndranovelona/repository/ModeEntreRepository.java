package com.hard.cegAndranovelona.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hard.cegAndranovelona.modeles.ModeEntre;

@Repository
public interface ModeEntreRepository extends JpaRepository<ModeEntre, Long> {
    Page<ModeEntre> findAll(Pageable pageable);
}
