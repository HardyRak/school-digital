package com.hard.cegAndranovelona.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hard.cegAndranovelona.modeles.AnneeScolaire;
import com.hard.cegAndranovelona.repository.AnneeScolaireRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import org.springframework.data.domain.Sort;

@Service
public class AnneeScolaireService {
    @Autowired
    private AnneeScolaireRepository repository;

    public AnneeScolaire saveOrUpdate(AnneeScolaire entity) {
        return repository.save(entity);
    }

    public Optional<AnneeScolaire> getById(Long id) {
        return repository.findById(id);
    }

    public Page<AnneeScolaire> getAll(int page, int size, String fieldTri) {
        Sort sort = null;
        if (fieldTri != null && !fieldTri.isEmpty()) {
            String[] split = fieldTri.split(",");
            sort = Sort.by(Sort.Direction.fromString(split[1]), split[0]);
            return repository.findAll(PageRequest.of(page, size, sort));
        }
        return repository.findAll(PageRequest.of(page, size));
    }

    public List<AnneeScolaire> getAll() {
     
        Sort sort = Sort.by("fin").ascending();
        return repository.findAll(sort);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public AnneeScolaire estActu(){
        return repository.findByEstActu(1);
    }
}
