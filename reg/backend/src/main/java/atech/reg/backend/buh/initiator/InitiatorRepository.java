package atech.reg.backend.buh.initiator;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InitiatorRepository extends JpaRepository<InitiatorEntity, Long> {
    InitiatorEntity findByName(String name);

    @Query("SELECT i.id FROM InitiatorEntity i")
    List<Long> getAllIds();
}
