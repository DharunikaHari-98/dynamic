package dynamic.dynamic.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String location;
    private int capacity;
    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Event> events;
    @ManyToMany(mappedBy = "venues",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Organizer> organizers;
    public Venue() {}

    public Venue(Long id, String name, String location, int capacity, List<Event> events, List<Organizer> organizers) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.events = events;
        this.organizers = organizers;
    }
    public List<Organizer> getOrganizers() {
        return organizers;
    }

    public void setOrganizers(List<Organizer> organizers) {
        this.organizers = organizers;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public List<Event> getEvents() { return events; }
    public void setEvents(List<Event> events) { this.events = events; }
}
