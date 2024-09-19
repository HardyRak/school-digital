package com.hard.cegAndranovelona.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hard.cegAndranovelona.modeles.Niveau;
import com.hard.cegAndranovelona.repository.NiveauRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import org.springframework.data.domain.Sort;

@Service
public class NiveauService {
    @Autowired
    private NiveauRepository repository;

    public Niveau saveOrUpdate(Niveau entity) {
        return repository.save(entity);
    }

    public Optional<Niveau> getById(Long id) {
        return repository.findById(id);
    }

    public Page<Niveau> getAll(int page, int size, String fieldTri) {
        Sort sort = null;
        if (fieldTri != null && !fieldTri.isEmpty()) {
            String[] split = fieldTri.split(",");
            sort = Sort.by(Sort.Direction.fromString(split[1]), split[0]);
            return repository.findAll(PageRequest.of(page, size, sort));
        }
        return repository.findAll(PageRequest.of(page, size));
    }

    public List<Niveau> getAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
