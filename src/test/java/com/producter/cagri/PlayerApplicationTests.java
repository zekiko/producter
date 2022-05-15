package com.producter.cagri;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PlayerApplication.class)
public class PlayerApplicationTests {

    private static final String REQUEST_FOR_PLAYERS = "request/players.query";
    private static final String REQUEST_FOR_NEW_PLAYER = "request/newPlayer.mutation";
    private static final String REQUEST_FOR_DELETE_PLAYER = "request/deletePlayer.mutation";
    private static final String REQUEST_FOR_NEW_PLAYER_FALSE = "request/newPlayerFalse.mutation";
    private static final String REQUEST_FOR_DELETE_PLAYER_FALSE = "request/deletePlayerFalse.mutation";

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void givenPlayerList_whenValidData_returnList() throws IOException, JSONException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(REQUEST_FOR_PLAYERS);
        assertTrue(graphQLResponse.isOk());

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = mapper.readValue(graphQLResponse.getRawResponse().getBody(), Map.class);

        Map<String,Object> map2 = (Map<String, Object>) map.get("data");
        List<Object> list = (ArrayList<Object>) map2.get("findAllPlayers");
        Map<String,Object> map3 = (Map<String, Object>) list.get(0);

        assertEquals("cagri", map3.get("name"));
    }

    @Test
    public void givenPlayerList_whenValidData_thenNewPlayer() throws IOException, JSONException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(REQUEST_FOR_NEW_PLAYER);
        assertTrue(graphQLResponse.isOk());

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = mapper.readValue(graphQLResponse.getRawResponse().getBody(), Map.class);

        Map<String,Object> map2 = (Map<String, Object>) map.get("data");
        Map<String, Object> map3 = (Map<String, Object>) map2.get("newPlayer");
        String name = (String) map3.get("name");

        assertEquals("cagri2", name);

    }

    @Test
    public void givenPlayerList_whenValidData_thenDeletePlayer() throws IOException, JSONException {
        graphQLTestTemplate.postForResource(REQUEST_FOR_NEW_PLAYER);
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(REQUEST_FOR_DELETE_PLAYER);
        assertTrue(graphQLResponse.isOk());
    }

    @Test
    public void givenPlayerList_whenInvalidAddMutation_thenReturnError() throws IOException, JSONException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(REQUEST_FOR_NEW_PLAYER_FALSE);

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = mapper.readValue(graphQLResponse.getRawResponse().getBody(), Map.class);
        List<String> errorList = (ArrayList<String>) map.get("errors");
        assertNotNull(errorList);
    }

    @Test
    public void givenPlayerList_whenInvalidDeleteMutation_thenReturnError() throws IOException, JSONException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource(REQUEST_FOR_DELETE_PLAYER_FALSE);

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = mapper.readValue(graphQLResponse.getRawResponse().getBody(), Map.class);
        List<String> errorList = (ArrayList<String>) map.get("errors");
        assertNotNull(errorList);
    }

}