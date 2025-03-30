package dynamic.dynamic.Service;
import dynamic.dynamic.Entity.Event;
import dynamic.dynamic.Entity.User;
import dynamic.dynamic.Repository.EventRepository;
import dynamic.dynamic.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public Page<User> paginateUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

        public String registerEvent(Long userId, Long eventId) {
            Optional<User> userOptional = userRepository.findById(userId);
            Optional<Event> eventOptional = eventRepository.findById(eventId);

            if (userOptional.isEmpty() || eventOptional.isEmpty()) {
                return "User or Event not found.";
            }
            User user = userOptional.get();
            Event event = eventOptional.get();
            if (event.getUsers().contains(user)) {
                return "User is already registered for this event.";
            }
            if (event.getAvailableSeats() > 0) {
                event.getUsers().add(user); 
                user.getEvents().add(event); 
                event.setAvailableSeats(event.getAvailableSeats() - 1); 

                userRepository.save(user);
                eventRepository.save(event);

                return "User " + user.getId() + " successfully registered for event " + event.getId();
            } else {
                return "Event is full. No available seats.";
            }
        }
        public Set<Event> getRegisteredEvents(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return userOptional.get().getEvents(); 
    }
    public List<User> getUsersNotRegisteredForAnyEvent() {
        return userRepository.findUsersNotRegisteredForAnyEvent();
    }


}

