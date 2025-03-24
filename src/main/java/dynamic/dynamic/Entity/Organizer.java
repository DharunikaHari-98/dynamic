package dynamic.dynamic.Entity;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
@Entity
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Event> events;

    @ManyToMany
    @JoinTable(
            name="organ_venue",
            joinColumns = @JoinColumn(name="organ_id"),
            inverseJoinColumns = @JoinColumn(name="venue_id")
    )
    @JsonIgnore
    private List<Venue> venues;
    public Organizer() {}


    public Organizer(Long id, String name, List<Event> events, List<Venue> venues) {
        this.id = id;
        this.name = name;
        this.events = events;
        this.venues = venues;
    }

    public List<Venue> getVenues() {
        return venues;
    }
    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }

}