package dynamic.dynamic.Controller;


import dynamic.dynamic.Entity.Event;
import dynamic.dynamic.Entity.User;
import dynamic.dynamic.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    //http://localhost:8080/api/users

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //http://localhost:8080/api/users/{id}
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
    //http://localhost:8080/api/users/{id}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    //http://localhost:8080/api/users/paginate?page=0&size=10
    @GetMapping("/paginate")
    public Page<User> paginateUsers(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.paginateUsers(pageable);
    }
//http://localhost:8080/api/users/email/{email}
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
    @PostMapping("/{userId}/registerEvent/{eventId}")
    public String registerEvent(@PathVariable Long userId, @PathVariable Long eventId) {

        return userService.registerEvent(userId,eventId);
    }
    @GetMapping("/{userId}/events")
    public Set<Event> getRegisteredEvents(@PathVariable Long userId) {
        return userService.getRegisteredEvents(userId);
    }

}