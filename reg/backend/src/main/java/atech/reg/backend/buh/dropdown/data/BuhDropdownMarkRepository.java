package atech.reg.backend.buh.dropdown.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuhDropdownMarkRepository extends JpaRepository<BuhDropdownMarkEntity, Long> {
    List<BuhDropdownMarkEntity> findAllByOrderByIdAsc();

    @Query("SELECT m.id FROM BuhDropdownMarkEntity m")
    List<Long> getAllIds();
}
