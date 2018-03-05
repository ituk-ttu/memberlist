package ee.ituk.memberlist.server.member;

import ee.ituk.memberlist.server.access.Access;
import ee.ituk.memberlist.server.door.Door;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
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
    @OneToMany
    private List<Access> accessesCollection;

}
