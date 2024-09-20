package com.hard.cegAndranovelona.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hard.cegAndranovelona.modeles.HistoriqueClasse;
import com.hard.cegAndranovelona.modeles.Section;
import com.hard.cegAndranovelona.repository.HistoriqueClasseRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import org.springframework.data.domain.Sort;

@Service
public class HistoriqueClasseService {
    @Autowired
    private HistoriqueClasseRepository repository;

    public HistoriqueClasse saveOrUpdate(HistoriqueClasse entity) {
        return repository.save(entity);
    }

    public Optional<HistoriqueClasse> getById(Long id) {
        return repository.findById(id);
    }

    public Page<HistoriqueClasse> getAll(int page, int size, String fieldTri) {
        Sort sort = null;
        if (fieldTri != null && !fieldTri.isEmpty()) {
            String[] split = fieldTri.split(",");
            sort = Sort.by(Sort.Direction.fromString(split[1]), split[0]);
            return repository.findAll(PageRequest.of(page, size, sort));
        }
        return repository.findAll(PageRequest.of(page, size));
    }

    public List<HistoriqueClasse> getAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<HistoriqueClasse> getBySection(Section section){
        Sort sort = Sort.by("idHistorique").ascending();
        return repository.findBySection(section, sort);
    }

}
