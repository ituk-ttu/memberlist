package ee.ituk.memberlist.server.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MemberController.class, secure = false)
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberController memberController;

    @Test
    public void getMemberByIdTest() throws Exception {
        Member member = new Member();

        member.setName("koom");
        member.setId(1L);

        given(memberController.getMemberById(member.getId()))
                .willReturn(member);

        mockMvc.perform(get("/members/" + member.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(member.getName()));
    }

    @Test
    public void getAllMembersReturnsCorrectSizeTest() throws Exception {
        Member member = new Member();
        member.setName("uus");
        member.setId(1L);
        List<Member> allMembers = singletonList(member);

        given(memberController.getAllMembers()).willReturn(allMembers);

        mockMvc.perform(get("/members")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void deleteMemberTest() throws Exception {
        Member member = new Member();

        member.setName("koom");
        member.setId(1L);

        given(memberController.getMemberById(member.getId()))
                .willReturn(member);

        mockMvc.perform(get("/members/delete" + member.getId())
                .contentType(APPLICATION_JSON));

        given(memberController.getMemberById(member.getId()))
                .willReturn(null);
    }
}

