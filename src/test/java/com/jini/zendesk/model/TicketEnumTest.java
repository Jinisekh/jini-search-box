package com.jini.zendesk.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;

public class TicketEnumTest {

    @Test
    public void testTicketEnum() {
        ObjectMapper objectMapper = new ObjectMapper();
        Ticket ticket = new Ticket();
        JsonNode node = objectMapper.valueToTree(ticket);
        node.fields().forEachRemaining(e -> {
            String fieldName = e.getKey();
            assertNotNull(TicketEnum.valueOf(fieldName));
        });
    }

}