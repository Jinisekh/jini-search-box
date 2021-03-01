package com.jini.zendesk.search.service;

import com.jini.zendesk.model.*;
import com.jini.zendesk.search.index.DataIndexStore;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DataSearchStore {

    public void invokeSearchService(DataIndexStore indexStore) {
        System.out.println("Select 1) Users or 2) Tickets or 3) Organizations\n");
        Scanner in = new Scanner(System.in);
        String input = "";
        input = in.next();
        Scanner scan;
        String userInputField;
        String userInputValue;
        switch (input) {
            case "1":
                scan = new Scanner(System.in);
                System.out.println("Enter the search term::");
                userInputField  = scan.nextLine();
                System.out.println("Enter the search value::");
                userInputValue = scan.nextLine();
                displayUserResult(userInputField,userInputValue,indexStore);
                break;
            case "2":
                scan = new Scanner(System.in);
                System.out.println("Enter the search term::");
                userInputField  = scan.nextLine();
                System.out.println("Enter the search value::");
                userInputValue = scan.nextLine();
                displayTicketResult(userInputField,userInputValue,indexStore);
                break;
            case "3":
                scan = new Scanner(System.in);
                System.out.println("Enter the search term::");
                userInputField  = scan.nextLine();
                System.out.println("Enter the search value::");
                userInputValue = scan.nextLine();
                displayOrgResult(userInputField,userInputValue,indexStore);
                break;
            default:
                System.out.println("Kindly enter a valid option. Press 1 to search users, 2 for tickets or 3 for organization.");
                break;
        }
    }

    private void displayOrgResult(String userInputField, String userInputValue, DataIndexStore indexStore) {
        Map<String, Map<String, Set<Organization>>> orgSearchIndex = indexStore.getOrgSearchIndex();
        Map<Integer,Set<String>> orgToTicketRelationMap = indexStore.getOrgToTicketRelationMap();
        Map<Integer,Set<Integer>> orgToUserRelationship = indexStore.getOrgToUserRelationMap();

        if(!orgSearchIndex.containsKey(userInputField) || !orgSearchIndex.get(userInputField).containsKey(userInputValue.toLowerCase())){
            System.out.println("No records were found. Please try again");
            return;
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("Displaying Records details ::::::::::");
        System.out.println("-------------------------------------------------------");
        Set<Organization> ticketSet = orgSearchIndex.get(userInputField).get(userInputValue.toLowerCase());
        ticketSet.forEach(e -> {
            System.out.println("ID\t\t\t"+e.get_id());
            System.out.println("URL\t\t\t"+e.getUrl());
            System.out.println("External ID\t\t"+e.getExternal_id());
            System.out.println("Name\t\t\t"+e.getName());
            System.out.println("Details\t\t\t"+e.getDetails());
            System.out.println("Shared tickets\t\t"+e.getShared_tickets());
            System.out.println("Tags\t\t\t"+e.getTags());
            System.out.println("DomainNames\t\t"+e.getDomain_names());
            if(orgToTicketRelationMap.containsKey(e.get_id())){
                System.out.println("Associated Tickets\t"+orgToTicketRelationMap.get(e.get_id()));
            }
            if(orgToUserRelationship.containsKey(e.get_id())){
                System.out.println("Users\t\t\t"+orgToUserRelationship.get(e.get_id()));
            }
            System.out.println("-------------------------------------------------------");
        });
        System.out.println("-------------------------------------------------------");
        System.out.println("-------------------------------------------------------");
        System.out.println("Found "+orgSearchIndex.get(userInputField).get(userInputValue.toLowerCase()).size()+" records");
    }

    private void displayTicketResult(String userInputField, String userInputValue, DataIndexStore indexStore) {
        Map<String, Map<String, Set<Ticket>>> ticketSearchIndex = indexStore.getTicketSearchIndex();

        if(!ticketSearchIndex.containsKey(userInputField) || !ticketSearchIndex.get(userInputField).containsKey(userInputValue.toLowerCase())){
            System.out.println("No records were found. Please try again");
            return;
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("Displaying Records details::::::::::");
        System.out.println("-------------------------------------------------------");
        Set<Ticket> ticketSet = ticketSearchIndex.get(userInputField).get(userInputValue.toLowerCase());
        ticketSet.forEach(e -> {
            System.out.println("ID\t\t\t"+e.get_id());
            System.out.println("URL\t\t\t"+e.getUrl());
            System.out.println("External ID\t\t"+e.getExternal_id());
            System.out.println("Priority\t\t"+e.getPriority());
            System.out.println("Created At\t\t"+e.getCreated_at());
            System.out.println("Description\t\t"+e.getDescription());
            System.out.println("Due at\t\t\t"+e.getDue_at());
            System.out.println("Status\t\t\t"+e.getStatus());
            System.out.println("Subject\t\t\t"+e.getSubject());
            System.out.println("Type\t\t\t"+e.getType());
            System.out.println("Via\t\t\t"+e.getVia());
            System.out.println("Assignee\t\t"+e.getAssignee_id());
            System.out.println("Has Incident\t\t"+e.getHas_incidents());
            System.out.println("Organization ID\t\t"+e.getOrganization_id());
            System.out.println("Submitter ID\t\t"+e.getSubmitter_id());
            System.out.println("Tags\t\t\t"+e.getTags());
            System.out.println("-------------------------------------------------------");
        });
        System.out.println("-------------------------------------------------------");
        System.out.println("-------------------------------------------------------");
        System.out.println("Found "+ticketSearchIndex.get(userInputField).get(userInputValue.toLowerCase()).size()+" records");
    }

    private void displayUserResult(String userInputField, String userInputValue, DataIndexStore indexStore) {

        Map<String, Map<String, Set<User>>> userSearchIndex = indexStore.getUserSearchIndex();
        Map<Integer, Set<String>> userToTicketSubmittedRelation = indexStore.getUserToTicketSubmittedRelationMap();
        Map<Integer, Set<String>> userToTicketAssignedRelation = indexStore.getUserToTicketAssignedRelationMap();

        if(!userSearchIndex.containsKey(userInputField) || !userSearchIndex.get(userInputField).containsKey(userInputValue.toLowerCase())) {
            System.out.println("No records were found. Please try again");
            return;
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("Displaying Records found::::::::::");
        System.out.println("-------------------------------------------------------");
        Set<User> userSet = userSearchIndex.get(userInputField).get(userInputValue.toLowerCase());
        userSet.forEach(e -> {
            System.out.println("ID\t\t\t"+e.get_id());
            System.out.println("URL\t\t\t"+e.getUrl());
            System.out.println("External ID\t\t"+e.getExternal_id());
            System.out.println("Name\t\t\t"+e.getName());
            System.out.println("Alias\t\t\t"+e.getAlias());
            System.out.println("Created At\t\t"+e.getCreated_at());
            System.out.println("Email\t\t\t"+e.getEmail());
            System.out.println("Active\t\t\t"+e.getActive());
            System.out.println("Verified\t\t"+e.getVerified());
            System.out.println("Shared\t\t\t"+e.getShared());
            System.out.println("Locale\t\t\t"+e.getLocale());
            System.out.println("Timezone\t\t"+e.getTimezone());
            System.out.println("Last Login\t\t"+e.getLast_login_at());
            System.out.println("Phone\t\t\t"+e.getPhone());
            System.out.println("Signature\t\t"+e.getSignature());
            System.out.println("Organization\t\t"+e.getOrganization_id());
            System.out.println("Suspended\t\t"+e.getSuspended());
            System.out.println("Role\t\t\t"+e.getRole());
            System.out.println("Tags\t\t\t"+e.getTags());
            if(userToTicketSubmittedRelation.containsKey(e.get_id())){
                System.out.println("Tickets Submitted\t"+userToTicketSubmittedRelation.get(e.get_id()));
            }
            if(userToTicketAssignedRelation.containsKey(e.get_id())){
                System.out.println("Tickets Assigned\t"+userToTicketAssignedRelation.get(e.get_id()));
            }
            System.out.println("-------------------------------------------------------");
        });
        System.out.println("-------------------------------------------------------");
        System.out.println("-------------------------------------------------------");
        System.out.println("Found "+userSearchIndex.get(userInputField).get(userInputValue.toLowerCase()).size()+" records");
    }

    public void displaySearchableFields() {
        System.out.println("-----------------------------------------");
        System.out.println("Search Users with::::");
        System.out.println("-----------------------------------------");
        for (UserEnum userEnum : UserEnum.values()) {
            System.out.println(userEnum);
        }
        System.out.println("-----------------------------------------");
        System.out.println("Search tickets with :::");
        System.out.println("-----------------------------------------");
        for (TicketEnum ticketEnum : TicketEnum.values()) {
            System.out.println(ticketEnum);
        }
        System.out.println("-----------------------------------------");
        System.out.println("Search Organizations with :::");
        System.out.println("-----------------------------------------");
        for (OrganizationEnum organizationEnum : OrganizationEnum.values()) {
            System.out.println(organizationEnum);
        }
        System.out.println("\n");
    }
}
