package com.jini.zendesk.search.index;

import com.jini.zendesk.model.Organization;
import com.jini.zendesk.model.User;
import com.jini.zendesk.util.Utils;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DataIndexStoreTest {
    @Test
    public void testOrdIndexLoad() throws IOException, URISyntaxException {
        DataIndexStore indexStore = new DataIndexStore();
        indexStore.createOrgIndex(Utils.getFileFromResource("organizations_test.json"));
        assertEquals(25, indexStore.getOrgEntityMap().size());
        Organization organization = indexStore.getOrgEntityMap().get(102);
        assertEquals("Nutralab", organization.getName());
        Map<String, Map<String, Set<Organization>>> orgSearchIndex = indexStore.getOrgSearchIndex();
        assertEquals(1, orgSearchIndex.get("domain_names").get("datagen.com").size());
        assertEquals(1, orgSearchIndex.get("tags").get("olsen").size());
        assertEquals(2, orgSearchIndex.get("tags").get("cherry").size());
        assertEquals(6, orgSearchIndex.get("details").get("non profit").size());
        assertEquals(2, orgSearchIndex.get("details").get("").size());
        assertEquals(10, orgSearchIndex.get("shared_tickets").get("true").size());
        assertEquals(15, orgSearchIndex.get("shared_tickets").get("false").size());
        assertEquals(1, orgSearchIndex.get("_id").get("102").size());
        assertNull(orgSearchIndex.get("_id").get("10000"));
    }

    @Test
    public void testUserIndexLoad() throws IOException, URISyntaxException {
        DataIndexStore indexStore = new DataIndexStore();
        indexStore.createUserIndex(Utils.getFileFromResource("users_test.json"));
        assertEquals(75,indexStore.getUserEntityMap().size());
        User user = indexStore.getUserEntityMap().get(1);
        assertEquals("coffeyrasmussen@flotonic.com",user.getEmail());
        assertEquals("2016-04-15T05:19:46 -10:00",user.getCreated_at());
        assertEquals("http://initech.zendesk.com/api/v2/users/1.json",user.getUrl());
        assertEquals("74341f74-9c79-49d5-9611-87ef9b6eb75f",user.getExternal_id());
        assertEquals("Francisca Rasmussen",user.getName());
        assertEquals("Miss Coffey",user.getAlias());
        assertEquals("2016-04-15T05:19:46 -10:00",user.getCreated_at());
        assertEquals(true,user.getActive());
        assertEquals(true,user.getVerified());
        assertEquals(false,user.getShared());
        assertEquals("en-AU",user.getLocale());
        assertEquals("Sri Lanka",user.getTimezone());
        assertEquals("2013-08-04T01:03:27 -10:00",user.getLast_login_at());
        Map<String,Map<String,Set<User>>> userSearchIndex = indexStore.getUserSearchIndex();
        assertEquals(1,userSearchIndex.get("name").get("Francisca Rasmussen".toLowerCase()).size());
    }

    @Test
    public void testTicketIndexLoad() throws IOException, URISyntaxException {
        DataIndexStore indexStore = new DataIndexStore();
        indexStore.createTicketIndex(Utils.getFileFromResource("tickets_test.json"));
        assertEquals(200,indexStore.getTicketEntityMap().size());

    }
}