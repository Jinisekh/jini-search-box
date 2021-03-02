package com.jini.zendesk.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserEnumTest {

    @Test
    public void testUserEnum() {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        JsonNode node = objectMapper.valueToTree(user);
        node.fields().forEachRemaining(e -> {
            String fieldName = e.getKey();
            assertNotNull(UserEnum.valueOf(fieldName));
        });
    }

}