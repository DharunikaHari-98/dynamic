package dynamic.dynamic.Repository;

import dynamic.dynamic.Entity.Event;  // Correct import statement
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganizerId(Long organizerId);

    @Query("SELECT e FROM Event e WHERE e.organizer.id = :organizerId")
    List<Event> findEventsByOrganizer(@Param("organizerId") Long organizerId);

    }
