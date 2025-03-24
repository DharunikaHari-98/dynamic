package dynamic.dynamic.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Event name cannot be null")
    @NotBlank(message = "Event name cannot be blank")
    @Column(unique = true)
    private String eventName;

    private String location;
    private String date;
    private String time;
    private int availableSeats;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organizer_id")

    private Organizer organizer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "venue_id")

    private Venue venue;

    @ManyToMany(mappedBy = "events")
    @JsonIgnore
    private Set<User> users;


    public Event()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Event name cannot be null") @NotBlank(message = "Event name cannot be blank") String getEventName() {
        return eventName;
    }

    public void setEventName(@NotNull(message = "Event name cannot be null") @NotBlank(message = "Event name cannot be blank") String eventName) {
        this.eventName = eventName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Event(Long id, String eventName, String location, String date, String time, int availableSeats, Organizer organizer, Venue venue, Set<User> users) {
        this.id = id;
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.time = time;
        this.availableSeats = availableSeats;
        this.organizer = organizer;
        this.venue = venue;
        this.users = users;
    }
}