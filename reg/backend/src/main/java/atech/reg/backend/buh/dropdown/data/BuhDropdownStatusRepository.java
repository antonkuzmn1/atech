package atech.reg.backend.buh.dropdown.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuhDropdownStatusRepository extends JpaRepository<BuhDropdownStatusEntity, Long> {
    List<BuhDropdownStatusEntity> findAllByOrderByIdAsc();

    @Query("SELECT s.id FROM BuhDropdownStatusEntity s")
    List<Long> getAllIds();
}
