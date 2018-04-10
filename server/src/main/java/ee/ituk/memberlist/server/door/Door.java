package ee.ituk.memberlist.server.door;

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

}
