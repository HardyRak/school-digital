package com.hard.cegAndranovelona.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hard.cegAndranovelona.modeles.Etudiants;
import com.hard.cegAndranovelona.repository.EtudiantsRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import org.springframework.data.domain.Sort;

@Service
public class EtudiantsService {
    @Autowired
    private EtudiantsRepository repository;

    public Etudiants saveOrUpdate(Etudiants entity) {
        return repository.save(entity);
    }

    public Optional<Etudiants> getById(Long id) {
        return repository.findById(id);
    }

    public Page<Etudiants> getAll(int page, int size, String fieldTri) {
        Sort sort = null;
        if (fieldTri != null && !fieldTri.isEmpty()) {
            String[] split = fieldTri.split(",");
            sort = Sort.by(Sort.Direction.fromString(split[1]), split[0]);
            return repository.findAll(PageRequest.of(page, size, sort));
        }
        return repository.findAll(PageRequest.of(page, size));
    }

    public List<Etudiants> getAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
