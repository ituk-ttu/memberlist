package ee.ituk.memberlist.server.user;

import ee.ituk.memberlist.server.accesscollection.AccessCollection;
import ee.ituk.memberlist.server.accesscollection.AccessCollectionService;
import ee.ituk.memberlist.server.member.Member;
import ee.ituk.memberlist.server.member.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;


@RestController
@RequestMapping(value = "users")

public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private MemberService memberService;
    @Resource
    private AccessCollectionService accessService;


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
        member.setLastModified(LocalDateTime.now());
        user.setMember(memberService.addMember(member));
        return userService.saveUser(user);
    }

    @PutMapping(value = "/access/{id}")
    public User updateAccessInfo(@RequestBody AccessCollection newAccess, @PathVariable(value="id") long id) {
        User user = userService.getUserById(id);
        AccessCollection oldAccess = user.getAccessCollection();
        newAccess.setPreviousVersion(oldAccess);
        newAccess.setLastModified(LocalDateTime.now());
        user.setAccessCollection(accessService.addCollection(newAccess));
        return userService.saveUser(user);
    }

    @GetMapping("/whoami")
    public Principal whoAmI(HttpServletRequest request) {
        return request.getUserPrincipal();
    }
}
