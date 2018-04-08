package ee.ituk.memberlist.server.user;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "users")

public class UserController {

    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }


    @GetMapping(value = "")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping(value = "{id}")
    public User getUserById(@PathVariable(value = "id") long id) {
        return userService.getUserById(id);
    }


    @PutMapping
    public User saveUserById(@RequestBody User user) {
        return userService.saveUser(user);
    }


}
