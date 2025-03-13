package dynamic.dynamic.Repository;


import dynamic.dynamic.Entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {


    @Query("SELECT o FROM Organizer o WHERE o.name = :name")
    Organizer findOrganizerByName(@Param("name") String name);

}