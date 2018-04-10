package ee.ituk.memberlist.server.accessCollection;

import ee.ituk.memberlist.server.access.Access;
import ee.ituk.memberlist.server.user.User;
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
    private long id;
    private User user;
    private AccessCollection previousVersion;
    private List<Access> accessList;
}
