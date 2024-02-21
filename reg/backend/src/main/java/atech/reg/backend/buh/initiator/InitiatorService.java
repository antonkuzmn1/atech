package atech.reg.backend.buh.initiator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitiatorService {

    @Autowired
    private InitiatorRepository repo;

    public List<InitiatorEntity> get() {
        return repo.findAll();
    }

    public InitiatorEntity getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Long> getAllIds() {
        return repo.getAllIds();
    }

    public InitiatorEntity insert(String name) {
        InitiatorEntity entity = repo.findByName(name);
        if (entity == null) {
            repo.save(new InitiatorEntity(name));
            return repo.findByName(name);
        } else
            return entity;
    }

}
