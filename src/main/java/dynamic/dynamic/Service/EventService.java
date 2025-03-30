package dynamic.dynamic.Service;

import dynamic.dynamic.Entity.Event;  // Correct import statement
import dynamic.dynamic.Entity.User;
import dynamic.dynamic.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public boolean deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            Event event = eventRepository.findById(id).orElse(null);
            if (event != null) {
                // Clear the relationship between users and this event
                event.getUsers().forEach(user -> user.getEvents().remove(event));
                event.setUsers(null); // Remove all associated users


                eventRepository.save(event);

                eventRepository.deleteById(id);
                return true;
            }
        }
        return false;
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

    public List<Event> getEventsByUserId(Long userId) {
        return eventRepository.findEventsByUserId(userId);
    }



    public long countUsersByEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new IllegalArgumentException("Event ID does not exist");
        }
        return eventRepository.countUsersByEventId(eventId);
    }


    public List<User> getUsersByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event ID does not exist"));
        return new ArrayList<>(event.getUsers());
    }


}
