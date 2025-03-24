package dynamic.dynamic.Repository;
import dynamic.dynamic.Entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
    Organizer findByName(String name);
}
