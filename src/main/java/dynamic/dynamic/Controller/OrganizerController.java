package dynamic.dynamic.Controller;
import dynamic.dynamic.Entity.Event;
import dynamic.dynamic.Entity.Organizer;
import dynamic.dynamic.Entity.User;
import dynamic.dynamic.Service.EventService;
import dynamic.dynamic.Service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {

    @Autowired
    private EventService eventService;
    @Autowired
    private OrganizerService organizerService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createOrganizer(@RequestBody(required = false) Organizer organizer) {
        if (organizer == null) {
            return ResponseEntity.badRequest().body("Request body cannot be null");
        }
        Organizer savedOrganizer = organizerService.saveOrganizer(organizer);
        return ResponseEntity.ok(savedOrganizer);
    }


    @GetMapping
    public List<Organizer> getAllOrganizers() {
        return organizerService.getAllOrganizers();
    }

    @GetMapping("/paginate")
    public Page<Organizer> paginateOrganizers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return organizerService.paginateOrganizers(pageable);
    }

    @GetMapping("/{id}")
    public Organizer getOrganizerById(@PathVariable Long id) {
        return organizerService.getOrganizerById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganizer(@PathVariable Long id) {
        organizerService.deleteOrganizer(id);
    }

    // ✅ GET ORGANIZER BY NAME
    @GetMapping("/name/{name}")
    public Organizer getOrganizerByName(@PathVariable String name) {
        return organizerService.getOrganizerByName(name);
    }
    @PostMapping("/{organizerId}/assignVenue/{venueId}")
    public ResponseEntity<Organizer> assignVenue(@PathVariable Long organizerId, @PathVariable Long venueId) {
        Organizer updatedOrganizer = organizerService.assignVenue(organizerId, venueId);
        return ResponseEntity.ok(updatedOrganizer);
    }//8.30 ku vaanga
    @GetMapping("/{organizerId}/events/{eventId}/users")
    public Set<User> getUsersByEvent(@PathVariable Long organizerId, @PathVariable Long eventId) {
        Event event = eventService.getEventById(eventId);

        if (event == null || !event.getOrganizer().getId().equals(organizerId)) {
            throw new RuntimeException("Event not found or does not belong to the organizer");
        }

        return event.getUsers();
    }
    @GetMapping("/{organizerId}/events")
    public List<Event> getEventsByOrganizer(@PathVariable Long organizerId) {
        Organizer organizer = organizerService.getOrganizerById(organizerId);

        if (organizer == null) {
            throw new RuntimeException("Organizer not found");
        }

        return organizer.getEvents();
    }
}
