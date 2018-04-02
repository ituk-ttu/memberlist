package ee.ituk.memberlist.server.auth.jwt;

import ee.ituk.memberlist.server.member.Member;
import ee.ituk.memberlist.server.member.MemberStatus;
import lombok.Data;

@Data
public class JwtUser {

    private long id;
    private String name;
    private MemberStatus status;

    public JwtUser(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.status = member.getStatus();
    }
}
