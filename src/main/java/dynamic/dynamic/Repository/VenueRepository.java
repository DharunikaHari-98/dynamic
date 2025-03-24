package dynamic.dynamic.Repository;
import dynamic.dynamic.Entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
    @Query("SELECT v FROM Venue v WHERE v.location = :location")
    List<Venue> findVenuesByLocation(@Param("location") String location);
}