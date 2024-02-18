package atech.reg.backend.session.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findById(Long id);

    List<UserEntity> findByDeletedOrderByNameAsc(boolean deleted);

    UserEntity findByLoginAndPassword(String login, String password);
}
