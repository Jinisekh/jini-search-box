# Coding-challenge-Jini Sekh

## AIM:
## A simple command line application to search data based on the data provided (organizations.json, users.json and tickets.json) and return results in human readble format.

### PROJECT DETAILS:
This is a Java project built using gradle. To the run the application you must have gradle installed in your system. 

### What you’ll need
+ JDK 8 or later
+ Install Gradle

### Clone project from github

 - Open terminal in any suitable folder
 - run below command 
```
 git clone https://github.com/Jinisekh/jini-search-box.git
```

### Install Gradle

  + [Installation guide for gradle](https://gradle.org/install/).

### Build and Run the Project

 - Open terminal on the project folder
```
cd <your-folder-path>/jini-search-box
```
 - run below command 
```
 gradle clean build
```
```
 gradle run
```

### A sample output will look like this::::

```
Welcome to Search.

Please select any of the following options.

Press 1 to search Zendesk.

Press 2 to view a list of searchable fields.

Type 'quit' to exit.


1
<=========----> 75% EXECUTING [3s]
Select 1) Users or 2) Tickets or 3) Organizations
1
<=========----> 75% EXECUTING [5s]
Enter the search term:: _id
<==<=========----> 75% EXECUTING [8s]
Enter the search value:: 71
<=========----> 75% EXECUTING [9s]
-------------------------------------------------------
Displaying Records found::::::::::
-------------------------------------------------------
ID                      71
URL                     http://initech.zendesk.com/api/v2/users/71.json
External ID             c972bb41-94aa-4f20-bc93-e63dbfe8d9ca
Name                    Prince Hinton
Alias                   Miss Dana
Created At              2016-04-18T11:05:43 -10:00
Email                   danahinton@flotonic.com
Active                  true
Verified                false
Shared                  false
Locale                  zh-CN
Timezone                Samoa
Last Login              2013-05-01T01:18:48 -10:00
Phone                   9064-433-892
Signature               Don't Worry Be Happy!
Organization            121
Suspended               false
Role                    agent
Tags                    [Davenport, Cherokee, Summertown, Clinton]
Tickets Submitted       [A Drama in Wallis and Futuna Islands, A Catastrophe in Micronesia, A Drama in Australia]
Tickets Assigned        [A Catastrophe in Sierra Leone]
-------------------------------------------------------
-------------------------------------------------------
-------------------------------------------------------
Found 1 records

```


