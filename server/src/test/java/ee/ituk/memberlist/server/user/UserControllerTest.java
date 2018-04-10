package ee.ituk.memberlist.server.user;

import ee.ituk.memberlist.server.member.Member;
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
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserController userController;

    @Test
    public void getAllUsersTest() throws Exception {
        User user = new User();
        List<User> allUsers = singletonList(user);

        given(userController.getAllUsers()).willReturn(allUsers);

        mvc.perform(get("/users").contentType(APPLICATION_JSON))
                .andExpect(status()
                        .isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getUserByIdTest() throws Exception {
        Member member = new Member();
        User user = new User();

        member.setName("testName");
        member.setEmail("testmail@gmail.com");

        user.setMember(member);

        given(userController.getUserById(user.getId()))
                .willReturn(user);

        mvc.perform(get("/users/" + user.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("member").value(user.getMember()));
    }

}
