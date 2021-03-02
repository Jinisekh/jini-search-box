package com.jini.zendesk.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrganizationEnumTest {
    @Test
    public void testOrganizationEnum() {
        ObjectMapper objectMapper = new ObjectMapper();
        Organization organization = new Organization();
        JsonNode node = objectMapper.valueToTree(organization);
        node.fields().forEachRemaining(e -> {
            String fieldName = e.getKey();
            assertNotNull(OrganizationEnum.valueOf(fieldName));
        });
    }
}