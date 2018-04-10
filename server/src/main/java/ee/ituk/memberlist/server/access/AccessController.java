package ee.ituk.memberlist.server.access;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "accesses")
public class AccessController {

    private AccessService accessService;


    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }


    @PostMapping(value = "add")
    public Access addAccess(@RequestBody Access access) {
        return accessService.addAccess(access);
    }


    @GetMapping(value = "")
    public Iterable<Access> getAllAccesss() {
        return accessService.getAllAccesses();
    }


    @GetMapping(value = "{id}")
    public Access getAccessById(@PathVariable(value = "id") long id) {
        return accessService.getAccessById(id);
    }


    @PutMapping
    public Access saveAccessById(@RequestBody Access access) {
        return accessService.saveAccess(access);
    }


}