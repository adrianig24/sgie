package es.sgie.back.repository;

import es.sgie.back.domain.User;
import es.sgie.back.domain.enums.Kind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByPic(String pic);

    @Query("SELECT u FROM User u WHERE u.kind = ?1")
    List<User> findAllUsersByKind(Kind kind);
}
