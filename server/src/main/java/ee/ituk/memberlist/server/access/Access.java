package ee.ituk.memberlist.server.access;

import ee.ituk.memberlist.server.accessCollection.AccessCollection;
import ee.ituk.memberlist.server.door.Door;
import ee.ituk.memberlist.server.member.Member;
import lombok.Data;


import javax.persistence.*;


@Entity
@Data
public class Access {


    @Id
    @GeneratedValue
    private long id;
    private AccessCollection accessCollection;
    @ManyToOne
    private Door door;


}
