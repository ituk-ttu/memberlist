package ee.ituk.memberlist.server.door;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "doors")
public class DoorController {
    private DoorService doorService;

    public DoorController(DoorService doorService) {
        this.doorService = doorService;
    }

    @PostMapping
    public Door addDoor(@RequestBody Door door) {
        return doorService.addDoor(door);
    }

    @GetMapping(value = "")
    public Iterable<Door> getAllDoors() {
        return doorService.getAllDoors();
    }

    @GetMapping(value = "{id}")
    public Door getDoorById(@PathVariable(value = "id") long id) {
        return doorService.getDoorById(id);
    }

    @PutMapping
    public Door saveDoorById(@RequestBody Door door) {
        return doorService.saveDoor(door);
    }


}
