package com.jini.zendesk.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testUserModel() {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        JsonNode node = objectMapper.valueToTree(user);
        for (UserEnum userEnum : UserEnum.values()) {
            assertNotNull(node.get(userEnum.toString()));
        }
    }

}