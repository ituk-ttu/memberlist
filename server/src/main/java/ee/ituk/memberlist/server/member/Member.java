package ee.ituk.memberlist.server.member;

import ee.ituk.memberlist.server.user.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String personalCode;
    private String studentCode;
    private String email;
    private MemberStatus status;
    private String cardNr;
    private String phoneNr;
    private LocalDate dateOfJoining;
    @OneToOne
    private Member previousVersion;
    @ManyToOne
    private User creator;
    private LocalDateTime lastModified;
}
