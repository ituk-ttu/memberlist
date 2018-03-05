package ee.ituk.memberlist.server.member;


import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private HashMap<Long, Member> memberList;


    public void addMember(Member member) {
        memberList.put(member.getId(), Member.builder().id(member.getId())
                                                       .name(member.getName())
                                                       .personalCode(member.getPersonalCode())
                                                       .studentCode(member.getStudentCode())
                                                       .email(member.getEmail())
                                                       .status(member.getStatus())
                                                       .cardNr(member.getCardNr())
                                                       .phoneNr(member.getPhoneNr())
                                                       .dateOfJoining(member.getDateOfJoining())
                                                       .accessCollection(member.getAccessCollection())
                                                       .build());
    }


    public Collection<Member> getMemberList() {
        return memberList.values();
    }


    public Member getMemberById(long id) {
        return memberList.get(id);
    }


}
