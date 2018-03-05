package ee.ituk.memberlist.server.access;

import ee.ituk.memberlist.server.door.Door;
import ee.ituk.memberlist.server.member.Member;
import lombok.Data;


import javax.persistence.*;


@Entity
@Data
public class Access {


    @Id @GeneratedValue
    private long id;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Door door;


}
