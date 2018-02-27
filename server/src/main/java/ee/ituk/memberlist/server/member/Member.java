package ee.ituk.memberlist.server.member;

import ee.ituk.memberlist.server.access.AccessCollection;
import ee.ituk.memberlist.server.door.Door;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Member {

    @Id @GeneratedValue @Getter
    private final long id;
    private String name;
    private String personalCode;
    private String studentCode;
    private String email;
    private MemberStatus status;
    private String cardNr;
    private String phoneNr;
    private LocalDate dateOfJoining;
    private AccessCollection accessCollection;

    public void modifyAccessCollection(List<Door> doorsToRemove, List<Door> doorsToAdd) {
       //TODO: mõelda, kes see on kõige parem viis või on midagi paremat
        this.accessCollection.addDoors(doorsToAdd);
        this.accessCollection.removeDoors(doorsToRemove);
    }
}
