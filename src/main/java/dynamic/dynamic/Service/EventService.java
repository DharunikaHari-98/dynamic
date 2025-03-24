package dynamic.dynamic.Service;

import dynamic.dynamic.Entity.Event;  // Correct import statement
import dynamic.dynamic.Entity.User;
import dynamic.dynamic.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event event) {
        if (eventRepository.existsById(id)) {
            event.setId(id);
            return eventRepository.save(event);
        }
        return null;
    }
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
    public List<Event> getEventsByOrganizer(Long organizerId) {
        return eventRepository.findEventsByOrganizer(organizerId);
    }
    public List<Event> getEventsByUser(Long userId) {
        return eventRepository.findAll()
                .stream()
                .filter(event -> event.getUsers().stream().anyMatch(user -> user.getId().equals(userId)))
                .toList();
    }

}
