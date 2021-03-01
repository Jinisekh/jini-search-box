package com.jini.zendesk.app;

import com.jini.zendesk.search.index.DataIndexStore;
import com.jini.zendesk.search.service.DataSearchStore;
import com.jini.zendesk.util.Utils;

import java.util.Scanner;

public class Application {

    static DataIndexStore indexStore = new DataIndexStore();
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            indexStore.createOrgIndex(Utils.getFileFromResource("organizations.json"));
            indexStore.createUserIndex(Utils.getFileFromResource("users.json"));
            indexStore.createTicketIndex(Utils.getFileFromResource("tickets.json"));
            DataSearchStore searchStore = new DataSearchStore();
            System.out.println("Welcome to Zendesk Search.\n");
            System.out.println("Please select any of the following options.\n");
            System.out.println("Press 1 to search Zendesk.\n");
            System.out.println("Press 2 to view a list of searchable fields.\n");
            System.out.println("Type 'quit' to exit.\n");
            System.out.println("\n");

            String input = "";
            while (!input.equalsIgnoreCase("quit")) {
                input = in.next();
                switch (input) {
                    case "1":
                        searchStore.invokeSearchService(indexStore);
                        break;
                    case "2":
                        searchStore.displaySearchableFields();
                        break;
                    case "quit":
                        System.out.println("Thank you for using Zendesk search. Exiting now...");
                        in.close();
                        break;
                    default:
                        System.out.println("Kindly enter a valid option. Press 1 for Zendesk search, 2 for displaying searchable fields or 'quit' to cancel the operation\n");
                        continue;
                }
                System.out.println("Press 1 to search, 2 to get all searchable fields or 'quit' to cancel the operation");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops Exception has been encountered. Please restart the system");
        }
    }

}
