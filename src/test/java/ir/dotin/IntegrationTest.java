package ir.dotin;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.dotin.model.Penalty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void create_user_penalty_punishment() throws Exception {
        Integer penaltyId;
        Integer userId;
        String userJson = "{\n" +
                "  \"name\":\"Sahar\",\n" +
                "  \"lastName\" : \"Shokouhi\"\n" +
                "}";

        String penaltyJson = "{\n" +
                "  \"amount\":\"135000\",\n" +
                "  \"definition\" : \"speaking at the same time at daily stand up ceremony\"\n" +
                "}";

        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        mvc.perform(post("/penalty")
                .contentType(MediaType.APPLICATION_JSON)
                .content(penaltyJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        mvc.perform(get("/penalty/get")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].amount",is(new Double(135000))));

        penaltyId = getPenaltyId();
        mvc.perform(patch("/penalty/change/{penaltyId}/{penaltyAmount}", penaltyId, new Double(131313))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        mvc.perform(get("/penalty/get")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].amount",is(new Double(131313))));
        userId = getUserId();
    }

    private Integer getUserId() throws Exception {
        MvcResult result = mvc.perform(get("/user/all")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String r = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,Object>> users = mapper.readValue(r, List.class);
        Integer userId = (Integer) users.get(0).get("userId");
        return userId;
    }

    private Integer getPenaltyId() throws Exception {
        MvcResult result = mvc.perform(get("/penalty/get")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String r = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,Object>> penalties = mapper.readValue(r, List.class);
        Integer penaltyId = (Integer) penalties.get(0).get("penaltyId");
        return penaltyId;
    }
}
