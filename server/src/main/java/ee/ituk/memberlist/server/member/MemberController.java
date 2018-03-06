package ee.ituk.memberlist.server.member;


import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "members")

public class MemberController {

    private MemberService memberService;


    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping(value = "add")
    public Member addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }


    @GetMapping(value = "")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }


    @GetMapping(value = "{Id}")
    public Member getMemberById(long id) {
        return memberService.getMemberById(id);
    }


}
