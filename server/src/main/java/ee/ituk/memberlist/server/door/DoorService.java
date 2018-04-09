package ee.ituk.memberlist.server.door;

import org.springframework.stereotype.Service;


@Service
public class DoorService {
    private DoorRepository doorRepository;

    public DoorService(DoorRepository doorRepository) {
        this.doorRepository = doorRepository;
    }

    public Door addDoor(Door door) {
        return doorRepository.save(door);
    }

    public Door getDoorById(long id) {
        return doorRepository.findOne(id);
    }

    public Iterable<Door> getAllDoors() {
        return doorRepository.findAll();
    }

    public void deleteDoor(long id) {
        doorRepository.delete(id);
    }

    public Door saveDoor(Door door) {
        return doorRepository.save(door);
    }

}
