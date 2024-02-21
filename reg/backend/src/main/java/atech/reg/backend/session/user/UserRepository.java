package atech.reg.backend.session.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByDeletedOrderByNameAsc(boolean deleted);

    UserEntity findByLoginAndPassword(String login, String password);
}
