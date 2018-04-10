package ee.ituk.memberlist.server.door;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DoorController.class, secure = false)
public class DoorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DoorController doorController;


    @Test
    public void getAllDoors() throws Exception {
        Door door = new Door();
        List<Door> allDoors = singletonList(door);

        given(doorController.getAllDoors()).willReturn(allDoors);

        mvc.perform(get("/doors").contentType(APPLICATION_JSON))
                .andExpect(status()
                        .isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getDoorById() throws Exception {
        Door door = new Door();
        door.setName("testName");
        given(doorController.getDoorById(door.getId())).willReturn(door);

        mvc.perform(get("/doors/" + door.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(door.getName()));
    }

}
