package atech.reg.backend.buh.dropdown.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuhDropdownControlRepository extends JpaRepository<BuhDropdownControlEntity, Long> {
    List<BuhDropdownControlEntity> findAllByOrderByIdAsc();

    @Query("SELECT c.id FROM BuhDropdownControlEntity c")
    List<Long> getAllIds();
}
