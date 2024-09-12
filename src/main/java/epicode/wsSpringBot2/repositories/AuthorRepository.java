package epicode.wsSpringBot2.repositories;

import epicode.wsSpringBot2.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    Optional<Author> findByEmail(String email);

    boolean existsByEmail(String email);
}
