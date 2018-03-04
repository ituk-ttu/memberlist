package ee.ituk.memberlist.server.access;

import ee.ituk.memberlist.server.door.Door;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class AccessCollection {


    @Id @GeneratedValue
    private final long id;
    private List<Door> doors;


    public void addDoors(List<Door> doorsToAdd) {
        this.doors.addAll(doorsToAdd);
    }

    public void removeDoors(List<Door> doorsToRemove) {
        this.doors.removeAll(doorsToRemove);
    }

}
