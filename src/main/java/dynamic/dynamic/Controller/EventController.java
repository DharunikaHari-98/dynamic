package dynamic.dynamic.Controller;

import dynamic.dynamic.Entity.Event;  // Correct import statement
import dynamic.dynamic.Entity.User;
import dynamic.dynamic.Service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;
    //http://localhost:8080/api/events
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE })
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {  // Added @Valid
        Event savedEvent = eventService.saveEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
    //http://localhost:8080/api/events/{id}
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id,@Valid @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }
    //http://localhost:8080/api/events/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
    //http://localhost:8080/api/events/organizer/{organizerId}
    @GetMapping("/organizer/{organizerId}")
    public List<Event> getEventsByOrganizer(@PathVariable Long organizerId) {
        return eventService.getEventsByOrganizer(organizerId);
    }
    // Get all events a specific user has registered for
    @GetMapping("/user/{userId}")
    public List<Event> getEventsByUser(@PathVariable Long userId) {
        return eventService.getEventsByUser(userId);
    }
    @GetMapping("/events")
    public List<Event> getAllEventsForUser() {
        // Fetch all events using the EventService
        return eventService.getAllEvents();
    }

    @GetMapping("/{eventId}/user-count")
    public ResponseEntity<?> getUserCountForEvent(@PathVariable Long eventId) {
        try {
            long userCount = eventService.countUsersByEvent(eventId);
            return ResponseEntity.ok(userCount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/events/{eventId}/users")
    public ResponseEntity<?> getUsersForEvent(@PathVariable Long eventId) {
        try {
            // Fetch and return users for the event
            List<User> users = eventService.getUsersByEventId(eventId);
            return ResponseEntity.ok(users);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
