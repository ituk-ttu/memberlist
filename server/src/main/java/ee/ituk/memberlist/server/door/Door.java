package ee.ituk.memberlist.server.door;

import ee.ituk.memberlist.server.access.Access;
import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Door {

    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String name;
    @OneToMany
    private List<Access> accesses;

}
