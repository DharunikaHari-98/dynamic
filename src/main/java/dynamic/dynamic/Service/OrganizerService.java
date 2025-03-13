package dynamic.dynamic.Service;


import dynamic.dynamic.Entity.Organizer;
import dynamic.dynamic.Repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrganizerService {
    @Autowired
    private OrganizerRepository organizerRepository;

    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }

    public Organizer getOrganizerById(Long id) {
        return organizerRepository.findById(id).orElse(null);
    }

    public Organizer createOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    public void deleteOrganizer(Long id) {
        organizerRepository.deleteById(id);
    }

    public Page<Organizer> paginateOrganizers(Pageable pageable) {
        return organizerRepository.findAll(pageable);
    }
    public Organizer getOrganizerByName(String name) {
        return organizerRepository.findOrganizerByName(name);
    }
}