package dynamic.dynamic.Repository;
import dynamic.dynamic.Entity.Event;  // Correct import statement
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganizerId(Long organizerId);

    @Query("SELECT e FROM Event e WHERE e.organizer.id = :organizerId")
    List<Event> findEventsByOrganizer(@Param("organizerId") Long organizerId);
    @Query("SELECT e FROM Event e JOIN e.users u WHERE u.id = :userId")
    List<Event> findEventsByUserId(@Param("userId") Long userId);
    @Query("SELECT COUNT(u.id) FROM Event e JOIN e.users u WHERE e.id = :eventId")
    long countUsersByEventId(@Param("eventId") Long eventId);

}
