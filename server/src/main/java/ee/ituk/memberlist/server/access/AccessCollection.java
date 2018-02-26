package ee.ituk.memberlist.server.access;

import ee.ituk.memberlist.server.door.Door;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class AccessCollection {

    @Id
    @GeneratedValue
    private final long id;
    private List<Door> rooms;
}
