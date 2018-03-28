import ee.ituk.memberlist.server.member.Member;
import ee.ituk.memberlist.server.member.MemberController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MemberController.class, secure = false)
@ContextConfiguration(classes = {TestContext.class, WebAppConfiguration.class})
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberController memberController;


    @Test
    public void getMemberByIdResposeIsOkTest() throws Exception {
        Member member = new Member();

        member.setName("testname");
        given(memberController.getMemberById(member.getId())).willReturn(member);

        mockMvc.perform(get("/members/" + member.getId()).contentType(APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getAllMembersResponseStatusIsOkTest() throws Exception {
        Member member = new Member();
        Member member1 = new Member();
        member.setName("testName1");
        member1.setName("testName2");
        given(memberController.getMemberById(member.getId())).willReturn(member);
        mockMvc.perform(get("/members").contentType(APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getMemberByIdTest() throws Exception {
        Member member = new Member();

        member.setName("koom");

        given(memberController.getMemberById(member.getId())).willReturn(member);

        mockMvc.perform(get("/members/" + member.getId()).contentType(APPLICATION_JSON)).andExpect(status().isOk()).andExpect((ResultMatcher) jsonPath("name", is(member.getName())));
    }


    @Test
    public void getAllMembersReturnsCorrectSizeTest() throws Exception {
        Member member = new Member();
        member.setName("uus");
        List<Member> allMembers = singletonList(member);

        given(memberController.getAllMembers()).willReturn(allMembers);

        mockMvc.perform(get("/members")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("members", hasSize(1)));

    }
}

