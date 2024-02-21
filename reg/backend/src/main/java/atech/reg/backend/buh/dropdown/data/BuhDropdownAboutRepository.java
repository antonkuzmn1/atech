package atech.reg.backend.buh.dropdown.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuhDropdownAboutRepository extends JpaRepository<BuhDropdownAboutEntity, Long> {
    List<BuhDropdownAboutEntity> findAllByOrderByIdAsc();

    @Query("SELECT a.id FROM BuhDropdownAboutEntity a")
    List<Long> getAllIds();
}
