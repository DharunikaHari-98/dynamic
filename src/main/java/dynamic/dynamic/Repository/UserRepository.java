package dynamic.dynamic.Repository;
import dynamic.dynamic.Entity.Event;
import dynamic.dynamic.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {
            boolean existsByUsername(String username);
        @Query("SELECT u.events FROM User u WHERE u.id = :userId")
        Set<Event> findEventsByUserId(@Param("userId") Long userId);
        @Query("SELECT u FROM User u WHERE u.email = :email")
        User findUserByEmail(@Param("email") String email);
        @Query("SELECT u FROM User u WHERE u.events IS EMPTY")
        List<User> findUsersNotRegisteredForAnyEvent();
    }

