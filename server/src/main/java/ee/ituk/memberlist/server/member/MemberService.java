package ee.ituk.memberlist.server.member;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public Member getMemberById(long id) {
        return memberRepository.findOne(id);
    }

    public List<Member> getAllMembers() {
        return (List<Member>) memberRepository.findAll();
    }

    public void deleteMember(long id) {
        memberRepository.delete(id);
    }

}
