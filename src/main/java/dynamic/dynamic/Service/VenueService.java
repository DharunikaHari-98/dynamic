package dynamic.dynamic.Service;

import dynamic.dynamic.Entity.Venue;
import dynamic.dynamic.Repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VenueService {
    @Autowired
    private VenueRepository venueRepository;

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public Venue getVenueById(Long id) {
        return venueRepository.findById(id).orElse(null);
    }

    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public Venue updateVenue(Long id, Venue venue) {
        if (venueRepository.existsById(id)) {
            venue.setId(id);
            return venueRepository.save(venue);
        }
        return null;
    }//ena ena change pannanum ma

    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }
    public List<Venue> getVenuesByLocation(String location) {
        return venueRepository.findVenuesByLocation(location);
    }


}