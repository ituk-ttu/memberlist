package ee.ituk.memberlist.server.door;

import ee.ituk.memberlist.server.access.Access;
import lombok.Data;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Door {

    @Id
    @GeneratedValue
    private final long id;
    private String name;
    @OneToMany
    private List<Access> accesses;

}
