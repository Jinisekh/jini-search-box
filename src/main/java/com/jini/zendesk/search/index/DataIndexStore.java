package com.jini.zendesk.search.index;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jini.zendesk.model.Organization;
import com.jini.zendesk.model.Ticket;
import com.jini.zendesk.model.User;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataIndexStore {

    private Map<Integer, Organization> orgEntityMap = new HashMap<>();
    private Map<Integer, User> userEntityMap = new HashMap<>();
    private Map<String, Ticket> ticketEntityMap = new HashMap<>();

    private Map<Integer, Set<Integer>> orgToUserRelationMap = new HashMap<>();
    private Map<Integer, Set<String>> orgToTicketRelationMap = new HashMap<>();
    private Map<Integer, Set<String>> userToTicketSubmittedRelationMap = new HashMap<>();
    private Map<Integer, Set<String>> userToTicketAssignedRelationMap = new HashMap<>();

    private Map<String, Map<String, Set<Organization>>> orgSearchIndex = new HashMap<>();
    private Map<String, Map<String, Set<User>>> userSearchIndex = new HashMap<>();
    private Map<String, Map<String, Set<Ticket>>> ticketSearchIndex = new HashMap<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    public void createOrgIndex(File jsonFile) throws IOException {
        List<Organization> orgList = objectMapper.readValue(jsonFile, new TypeReference<List<Organization>>() {
        });
        orgList.forEach(org -> {
            orgEntityMap.put(org.get_id(), org);
            addOrganizationToSearchIndex(org);
        });
    }

    public Map<Integer, User> createUserIndex(File jsonFile) throws IOException {
        List<User> userList = objectMapper.readValue(jsonFile, new TypeReference<List<User>>() {
        });
        userList.forEach(user -> {
            userEntityMap.put(user.get_id(), user);
            addOrgToUserRelations(user);
            addUserToSearchIndex(user);
        });
        return userEntityMap;
    }

    private void addUserToSearchIndex(User user) {
        JsonNode node = objectMapper.valueToTree(user);
        node.fields().forEachRemaining(e -> {
            String fieldName = e.getKey();
            if (e.getValue().isArray()) {
                e.getValue().forEach(valueNode -> {
                    addUserFieldValueToSearchIndex(fieldName, valueNode.asText(""), user);
                });
            } else {
                addUserFieldValueToSearchIndex(fieldName, e.getValue().asText(""), user);
            }
        });
    }

    private void addUserFieldValueToSearchIndex(String fieldName, String value, User user) {
        if (!userSearchIndex.containsKey(fieldName)) {
            userSearchIndex.put(fieldName, new HashMap<>());
        }
        Map<String, Set<User>> fieldValueIndex = userSearchIndex.get(fieldName);
        String lowerCaseValue = value.toLowerCase();
        if (!fieldValueIndex.containsKey(lowerCaseValue)) {
            fieldValueIndex.put(lowerCaseValue, new HashSet<>());
        }
        fieldValueIndex.get(lowerCaseValue).add(user);
    }

    public Map<String, Ticket> createTicketIndex(File jsonFile) throws IOException {
        List<Ticket> ticketList = objectMapper.readValue(jsonFile, new TypeReference<List<Ticket>>() {
        });
        ticketList.forEach(ticket -> {
            ticketEntityMap.put(ticket.get_id(), ticket);
            addOrgToTicketRelations(ticket);
            addUserToTicketSubmittedRelations(ticket);
            addUserToTicketAssignedRelations(ticket);
            addTicketToSearchIndex(ticket);
        });
        return ticketEntityMap;
    }

    private void addTicketToSearchIndex(Ticket ticket) {
        JsonNode node = objectMapper.valueToTree(ticket);
        node.fields().forEachRemaining(e -> {
            String fieldName = e.getKey();
            if (e.getValue().isArray()) {
                e.getValue().forEach(valueNode -> {
                    addTicketFieldValueToSearchIndex(fieldName, valueNode.asText(""), ticket);
                });
            } else {
                addTicketFieldValueToSearchIndex(fieldName, e.getValue().asText(""), ticket);
            }
        });
    }

    private void addTicketFieldValueToSearchIndex(String fieldName, String value, Ticket ticket) {
        if (!ticketSearchIndex.containsKey(fieldName)) {
            ticketSearchIndex.put(fieldName, new HashMap<>());
        }
        Map<String, Set<Ticket>> fieldValueIndex = ticketSearchIndex.get(fieldName);
        String lowerCaseValue = value.toLowerCase();
        if (!fieldValueIndex.containsKey(lowerCaseValue)) {
            fieldValueIndex.put(lowerCaseValue, new HashSet<>());
        }
        fieldValueIndex.get(lowerCaseValue).add(ticket);
    }

    private void addUserToTicketSubmittedRelations(Ticket ticket) {
        if (!userToTicketSubmittedRelationMap.containsKey(ticket.getSubmitter_id())) {
            userToTicketSubmittedRelationMap.put(ticket.getSubmitter_id(), new HashSet<>());
        }
        userToTicketSubmittedRelationMap.get(ticket.getSubmitter_id()).add(ticket.getSubject());
    }

    private void addUserToTicketAssignedRelations(Ticket ticket) {
        if (!userToTicketAssignedRelationMap.containsKey(ticket.getAssignee_id())) {
            userToTicketAssignedRelationMap.put(ticket.getAssignee_id(), new HashSet<>());
        }
        userToTicketAssignedRelationMap.get(ticket.getAssignee_id()).add(ticket.getSubject());
    }

    private void addOrgToTicketRelations(Ticket ticket) {
        if (!orgToTicketRelationMap.containsKey(ticket.getOrganization_id())) {
            orgToTicketRelationMap.put(ticket.getOrganization_id(), new HashSet<>());
        }
        orgToTicketRelationMap.get(ticket.getOrganization_id()).add(ticket.getSubject());
    }

    private void addOrgToUserRelations(User user) {
        if (!orgToUserRelationMap.containsKey(user.getOrganization_id())) {
            orgToUserRelationMap.put(user.getOrganization_id(), new HashSet<>());
        }
        orgToUserRelationMap.get(user.getOrganization_id()).add(user.get_id());
    }

    public void addOrganizationToSearchIndex(Organization org) {
        JsonNode node = objectMapper.valueToTree(org);
        node.fields().forEachRemaining(e -> {
            String fieldName = e.getKey();
            if (e.getValue().isArray()) {
                e.getValue().forEach(valueNode -> {
                    addOrgFieldValueToSearchIndex(fieldName, valueNode.asText(""), org);
                });
            } else {
                addOrgFieldValueToSearchIndex(fieldName, e.getValue().asText(""), org);
            }
        });
    }

    private void addOrgFieldValueToSearchIndex(String fieldName, String value, Organization org) {
        if (!orgSearchIndex.containsKey(fieldName)) {
            orgSearchIndex.put(fieldName, new HashMap<>());
        }
        Map<String, Set<Organization>> fieldValueIndex = orgSearchIndex.get(fieldName);
        String lowerCaseValue = value.toLowerCase();
        if (!fieldValueIndex.containsKey(lowerCaseValue)) {
            fieldValueIndex.put(lowerCaseValue, new HashSet<>());
        }
        fieldValueIndex.get(lowerCaseValue).add(org);
    }

    public Map<Integer, Organization> getOrgEntityMap() {
        return orgEntityMap;
    }

    public Map<Integer, User> getUserEntityMap() {
        return userEntityMap;
    }

    public Map<String, Ticket> getTicketEntityMap() {
        return ticketEntityMap;
    }

    public Map<Integer, Set<Integer>> getOrgToUserRelationMap() {
        return orgToUserRelationMap;
    }

    public Map<Integer, Set<String>> getOrgToTicketRelationMap() {
        return orgToTicketRelationMap;
    }

    public Map<Integer, Set<String>> getUserToTicketSubmittedRelationMap() {
        return userToTicketSubmittedRelationMap;
    }

    public Map<String, Map<String, Set<Organization>>> getOrgSearchIndex() {
        return orgSearchIndex;
    }

    public Map<String, Map<String, Set<User>>> getUserSearchIndex() {
        return userSearchIndex;
    }

    public Map<String, Map<String, Set<Ticket>>> getTicketSearchIndex() {
        return ticketSearchIndex;
    }

    public Map<Integer, Set<String>> getUserToTicketAssignedRelationMap() {
        return userToTicketAssignedRelationMap;
    }
}
