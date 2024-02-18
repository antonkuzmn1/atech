package atech.reg.backend.session.group;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, String> {

    GroupEntity findById(Long id);

    List<GroupEntity> findByDeletedOrderByNameAsc(boolean deleted);

}
