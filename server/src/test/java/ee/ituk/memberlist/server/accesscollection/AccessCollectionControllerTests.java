package ee.ituk.memberlist.server.accesscollection;

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
@WebMvcTest(value = AccessCollectionController.class, secure = false)
public class AccessCollectionControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccessCollectionController collectionController;


    @Test
    public void getAccessCollectionById() throws Exception {
        AccessCollection collection = new AccessCollection();
        collection.setId(1);



        given(collectionController.getCollectionById(collection.getId()))
                .willReturn(collection);

        mvc.perform(get("/collections/" + collection.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(collection.getId()));
    }

    @Test
    public void getAllCollectionsReturnsCorrectSize() throws Exception {
        AccessCollection collection = new AccessCollection();
        List<AccessCollection> allCollections = singletonList(collection);

        given(collectionController.getAllCollections()).willReturn(allCollections);

        mvc.perform(get("/collections")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
