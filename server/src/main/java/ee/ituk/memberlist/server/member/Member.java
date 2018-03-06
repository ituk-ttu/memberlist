package ee.ituk.memberlist.server.member;

import ee.ituk.memberlist.server.access.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue
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
