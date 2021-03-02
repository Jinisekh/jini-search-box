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
    public void testDisplaySearchableFields() {
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
        searchStore.displaySearchableFields();
        assertEquals(testString,getOutput().trim());
    }

    @Test
    public void testDisplayUserResult() throws URISyntaxException, IOException {
        String testString ="-------------------------------------------------------\n" +
                "Displaying Records found::::::::::\n" +
                "-------------------------------------------------------\n" +
                "ID\t\t\t71\n" +
                "URL\t\t\thttp://initech.zendesk.com/api/v2/users/71.json\n" +
                "External ID\t\tc972bb41-94aa-4f20-bc93-e63dbfe8d9ca\n" +
                "Name\t\t\tPrince Hinton\n" +
                "Alias\t\t\tMiss Dana\n" +
                "Created At\t\t2016-04-18T11:05:43 -10:00\n" +
                "Email\t\t\tdanahinton@flotonic.com\n" +
                "Active\t\t\ttrue\n" +
                "Verified\t\tfalse\n" +
                "Shared\t\t\tfalse\n" +
                "Locale\t\t\tzh-CN\n" +
                "Timezone\t\tSamoa\n" +
                "Last Login\t\t2013-05-01T01:18:48 -10:00\n" +
                "Phone\t\t\t9064-433-892\n" +
                "Signature\t\tDon't Worry Be Happy!\n" +
                "Organization\t\t121\n" +
                "Suspended\t\tfalse\n" +
                "Role\t\t\tagent\n" +
                "Tags\t\t\t[Davenport, Cherokee, Summertown, Clinton]\n" +
                "Tickets Submitted\t[A Drama in Wallis and Futuna Islands, A Catastrophe in Micronesia, A Drama in Australia]\n" +
                "Tickets Assigned\t[A Catastrophe in Sierra Leone]\n" +
                "-------------------------------------------------------\n" +
                "-------------------------------------------------------\n" +
                "-------------------------------------------------------\n" +
                "Found 1 records";
        DataIndexStore indexStore = new DataIndexStore();
        indexStore.createTicketIndex(Utils.getFileFromResource("tickets_test.json"));
        indexStore.createUserIndex(Utils.getFileFromResource("users_test.json"));
        String userInputField = "_id";
        String userInputValue = "71";
        DataSearchStore searchStore = new DataSearchStore();
        searchStore.displayUserResult(userInputField,userInputValue,indexStore);
        assertEquals(testString,getOutput().trim());
    }

    @Test
    public void testDisplayTicketResult() throws URISyntaxException, IOException {
        String testString ="-------------------------------------------------------\n" +
                "Displaying Records details::::::::::\n" +
                "-------------------------------------------------------\n" +
                "ID\t\t\t436bf9b0-1147-4c0a-8439-6f79833bff5b\n" +
                "URL\t\t\thttp://initech.zendesk.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json\n" +
                "External ID\t\t9210cdc9-4bee-485f-a078-35396cd74063\n" +
                "Priority\t\thigh\n" +
                "Created At\t\t2016-04-28T11:19:34 -10:00\n" +
                "Description\t\tNostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum.\n" +
                "Due at\t\t\t2016-07-31T02:37:50 -10:00\n" +
                "Status\t\t\tpending\n" +
                "Subject\t\t\tA Catastrophe in Korea (North)\n" +
                "Type\t\t\tincident\n" +
                "Via\t\t\tweb\n" +
                "Assignee\t\t24\n" +
                "Has Incident\t\tfalse\n" +
                "Organization ID\t\t116\n" +
                "Submitter ID\t\t38\n" +
                "Tags\t\t\t[Ohio, Pennsylvania, American Samoa, Northern Mariana Islands]\n" +
                "-------------------------------------------------------\n" +
                "-------------------------------------------------------\n" +
                "-------------------------------------------------------\n" +
                "Found 1 records";
        DataIndexStore indexStore = new DataIndexStore();
        indexStore.createTicketIndex(Utils.getFileFromResource("tickets_test.json"));
        String userInputField = "_id";
        String userInputValue = "436bf9b0-1147-4c0a-8439-6f79833bff5b";
        DataSearchStore searchStore = new DataSearchStore();
        searchStore.displayTicketResult(userInputField,userInputValue,indexStore);
        assertEquals(testString,getOutput().trim());
    }

    @Test
    public void testDisplayOrgResult() throws URISyntaxException, IOException {
        String testString ="-------------------------------------------------------\n" +
                "Displaying Records details ::::::::::\n" +
                "-------------------------------------------------------\n" +
                "ID\t\t\t102\n" +
                "URL\t\t\thttp://initech.zendesk.com/api/v2/organizations/102.json\n" +
                "External ID\t\t7cd6b8d4-2999-4ff2-8cfd-44d05b449226\n" +
                "Name\t\t\tNutralab\n" +
                "Details\t\t\tnull\n" +
                "Shared tickets\t\tfalse\n" +
                "Tags\t\t\t[Cherry, Collier, Fuentes, Trevino]\n" +
                "DomainNames\t\t[trollery.com, datagen.com, bluegrain.com, dadabase.com]\n" +
                "Associated Tickets\t[A Problem in Antigua and Barbuda, A Catastrophe in Bermuda, A Drama in Martinique, A Problem in Syria, A Problem in Gambia, A Nuisance in Liberia, A Problem in Japan, A Nuisance in Eritrea]\n" +
                "Users\t\t\t[33, 69, 25]\n" +
                "-------------------------------------------------------\n" +
                "-------------------------------------------------------\n" +
                "-------------------------------------------------------\n" +
                "Found 1 records";
        String userInputField;
        String userInputValue;
        DataIndexStore indexStore = new DataIndexStore();
        indexStore.createOrgIndex(Utils.getFileFromResource("organizations_test.json"));
        indexStore.createUserIndex(Utils.getFileFromResource("users_test.json"));
        indexStore.createTicketIndex(Utils.getFileFromResource("tickets_test.json"));
        userInputField = "_id";
        userInputValue = "102";
        DataSearchStore searchStore = new DataSearchStore();
        searchStore.displayOrgResult(userInputField,userInputValue,indexStore);
        assertEquals(testString,getOutput().trim());
    }
}