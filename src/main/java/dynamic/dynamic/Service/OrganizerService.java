package dynamic.dynamic.Service;
import dynamic.dynamic.Entity.Organizer;
import dynamic.dynamic.Entity.Venue;
import dynamic.dynamic.Repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class OrganizerService {
    private final OrganizerRepository organizerRepository;
    @Autowired
    public OrganizerService(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }
    @Autowired
    private VenueService vs;
    @Transactional
    public Organizer saveOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }
    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }
    public Page<Organizer> paginateOrganizers(Pageable pageable) {
        return organizerRepository.findAll(pageable);
    }
    public Organizer getOrganizerById(Long id) {
        return organizerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organizer not found with ID: " + id));
    }
    @Transactional
    public void deleteOrganizer(Long id) {
        if (!organizerRepository.existsById(id)) {
            throw new RuntimeException("Organizer not found with ID: " + id);
        }
        organizerRepository.deleteById(id);
    }
    public Organizer getOrganizerByName(String name) {
        return Optional.ofNullable(organizerRepository.findByName(name))
                .orElseThrow(() -> new RuntimeException("Organizer not found with name: " + name));
    }
    public Organizer assignVenue(Long organizerId, Long venueId) {
        Organizer organizer = organizerRepository.findById(organizerId)
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        Venue venue = vs.getVenueById(venueId);
        organizer.getVenues().add(venue);
        return organizerRepository.save(organizer);
    }
}
