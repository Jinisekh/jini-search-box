package com.jini.zendesk.search.service;

import com.jini.zendesk.search.index.DataIndexStore;
import com.jini.zendesk.util.Utils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class DataSearchStoreTest {

    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    public void testDisplaySearchableFields() throws URISyntaxException, IOException {
        final String testString = "-----------------------------------------\n" +
                "Search Users with::::\n" +
                "-----------------------------------------\n" +
                "_id\n" +
                "url\n" +
                "external_id\n" +
                "name\n" +
                "alias\n" +
                "created_at\n" +
                "active\n" +
                "verified\n" +
                "shared\n" +
                "locale\n" +
                "timezone\n" +
                "last_login_at\n" +
                "email\n" +
                "phone\n" +
                "signature\n" +
                "organization_id\n" +
                "tags\n" +
                "suspended\n" +
                "role\n" +
                "-----------------------------------------\n" +
                "Search tickets with :::\n" +
                "-----------------------------------------\n" +
                "_id\n" +
                "url\n" +
                "external_id\n" +
                "created_at\n" +
                "type\n" +
                "subject\n" +
                "description\n" +
                "priority\n" +
                "status\n" +
                "submitter_id\n" +
                "assignee_id\n" +
                "organization_id\n" +
                "tags\n" +
                "has_incidents\n" +
                "due_at\n" +
                "via\n" +
                "-----------------------------------------\n" +
                "Search Organizations with :::\n" +
                "-----------------------------------------\n" +
                "_id\n" +
                "url\n" +
                "external_id\n" +
                "name\n" +
                "domain_names\n" +
                "created_at\n" +
                "details\n" +
                "shared_tickets\n" +
                "tags";
        DataSearchStore searchStore = new DataSearchStore();
        DataIndexStore indexStore = new DataIndexStore();
        indexStore.createOrgIndex(Utils.getFileFromResource("organizations_test.json"));
        indexStore.createTicketIndex(Utils.getFileFromResource("tickets_test.json"));
        indexStore.createUserIndex(Utils.getFileFromResource("users_test.json"));
        searchStore.displaySearchableFields();
        assertEquals(testString,getOutput().trim());
    }
}