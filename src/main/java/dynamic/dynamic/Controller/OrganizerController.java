package dynamic.dynamic.Controller;


import dynamic.dynamic.Entity.Organizer;
import dynamic.dynamic.Service.OrganizerService;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {
    @Autowired
    private OrganizerService organizerService;

    @PostMapping
    public Organizer createOrganizer(@RequestBody Organizer organizer) {
        return organizerService.createOrganizer(organizer);
    }

    @GetMapping
    public List<Organizer> getAllOrganizers() {
        return organizerService.getAllOrganizers();
    }

    @GetMapping("/paginate")
    public Page<Organizer> paginateOrganizers(Pageable pageable) {
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
    @GetMapping("/name/{name}")
    public Organizer getOrganizerByName(@PathVariable String name) {
        return organizerService.getOrganizerByName(name);
    }

}