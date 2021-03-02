package com.jini.zendesk.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;

public class TicketTest {

    @Test
    public void testTicketModel() {
        ObjectMapper objectMapper = new ObjectMapper();
        Ticket ticket = new Ticket();
        JsonNode node = objectMapper.valueToTree(ticket);
        for (TicketEnum ticketEnum : TicketEnum.values()) {
            assertNotNull(node.get(ticketEnum.toString()));
        }
    }

}