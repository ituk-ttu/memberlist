package ee.ituk.memberlist.server.member;

import ee.ituk.memberlist.server.access.AccessCollection;
import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue
    private final long id;
    private String name;
    private String persoalCode;
    private String studentCode;
    private String email;
    private MemberStatus status;
    private String cardNr;
    private String phoneNr;
    private LocalDate dateOfJoining;
    private AccessCollection accessCollection;

}
