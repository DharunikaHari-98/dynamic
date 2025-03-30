package dynamic.dynamic.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    @JsonIgnore
    private Set<Event> events;

    public User() {}



    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<Event> getEvents() {
        return events;
    }

    public User(Long id, String username, String email, Set<Event> events) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.events = events;
        // this.organizer = organizer;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

//    public Organizer getOrganizer() { return organizer; }
//    public void setOrganizer(Organizer organizer) { this.organizer = organizer; }
}