package atech.reg.backend.buh.contractor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractorService {

    @Autowired
    private ContractorRepository repo;

    public List<ContractorEntity> get() {
        return repo.findAll();
    }

    public ContractorEntity getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Long> getAllIds() {
        return repo.getAllIds();
    }

    public ContractorEntity insert(String name) {
        ContractorEntity entity = repo.findByName(name);
        if (entity == null) {
            repo.save(new ContractorEntity(name));
            return repo.findByName(name);
        } else
            return entity;
    }

}
