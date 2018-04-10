package ee.ituk.memberlist.server.user;

import ee.ituk.memberlist.server.accessCollection.AccessCollection;
import ee.ituk.memberlist.server.member.Member;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Member member;
    private AccessCollection accessCollection;
}
