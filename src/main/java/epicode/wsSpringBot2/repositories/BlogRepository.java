package epicode.wsSpringBot2.repositories;

import epicode.wsSpringBot2.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogRepository extends JpaRepository<Blog, UUID> {
}
