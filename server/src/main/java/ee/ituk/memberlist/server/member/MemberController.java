package ee.ituk.memberlist.server.member;


import org.springframework.web.bind.annotation.*;


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
    public Iterable<Member> getAllMembers() {
        return memberService.getAllMembers();
    }


    @GetMapping(value = "{id}")
    public Member getMemberById(@PathVariable(value = "id") long id) {
        return memberService.getMemberById(id);
    }


    @PutMapping
    public Member saveMemberById(@RequestBody Member member) {
        return memberService.addMember(member);
    }


}
