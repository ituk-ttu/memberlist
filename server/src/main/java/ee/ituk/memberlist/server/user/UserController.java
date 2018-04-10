package ee.ituk.memberlist.server.user;

import ee.ituk.memberlist.server.member.Member;
import ee.ituk.memberlist.server.member.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "users")

public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private MemberService memberService;


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

    @PutMapping(value = "/update/{id}")
    public User updateMemberInfo(@RequestBody Member member, @PathVariable(value="id") long id) {
        User user = userService.getUserById(id);
        Member previousMember = user.getMember();
        member.setPreviousVersion(previousMember);
        user.setMember(memberService.addMember(member));
        return userService.saveUser(user);
    }
}
