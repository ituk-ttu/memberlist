package ee.ituk.memberlist.server.user;

import ee.ituk.memberlist.server.accesscollection.AccessCollection;
import ee.ituk.memberlist.server.member.Member;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Member member;
    @OneToOne
    private AccessCollection accessCollection;
}
