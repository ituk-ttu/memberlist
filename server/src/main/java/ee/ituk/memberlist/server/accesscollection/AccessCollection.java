package ee.ituk.memberlist.server.accesscollection;

import ee.ituk.memberlist.server.door.Door;
import ee.ituk.memberlist.server.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class AccessCollection {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private AccessCollection previousVersion;
    @ManyToMany
    private List<Door> doors;
    private LocalDateTime lastModified;
    @ManyToOne
    private User creator;
}
