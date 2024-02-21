package atech.reg.backend.buh.table.all;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableAllRepository extends JpaRepository<TableAllEntity, Long> {
}
