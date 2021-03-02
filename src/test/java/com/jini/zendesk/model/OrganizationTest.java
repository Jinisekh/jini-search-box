package com.jini.zendesk.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrganizationTest {

    @Test
    public void testOrganizationModel() {
        ObjectMapper objectMapper = new ObjectMapper();
        Organization organization = new Organization();
        JsonNode node = objectMapper.valueToTree(organization);
        for (OrganizationEnum organizationEnum : OrganizationEnum.values()) {
            assertNotNull(node.get(organizationEnum.toString()));
        }
    }

}