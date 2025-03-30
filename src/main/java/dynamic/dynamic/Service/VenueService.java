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
    }

    public boolean deleteVenue(Long id) {
        if (venueRepository.existsById(id)) {
            Venue venue = venueRepository.findById(id).orElse(null);
            if (venue != null) {
                if (venue.getEvents() != null && !venue.getEvents().isEmpty()) {

                    venue.getEvents().forEach(event -> event.setVenue(null));
                }


                venueRepository.save(venue);


                venueRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }
    public List<Venue> getVenuesByLocation(String location) {
        return venueRepository.findVenuesByLocation(location);
    }


}