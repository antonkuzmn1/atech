package atech.reg.backend.buh.contractor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorRepository extends JpaRepository<ContractorEntity, Long> {
    ContractorEntity findByName(String name);

    @Query("SELECT c.id FROM ContractorEntity c")
    List<Long> getAllIds();
}
