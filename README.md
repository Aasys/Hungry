Hungry - Food Odering System
=================

LogIn
--------------------------
Application URL: 
                http://resin.cci.drexel.edu:8080/~kwb44/parent


User with most data

email is: joedrexel@drexel.net 

password is: default

another user

email is: jp@trek.com

password is: default


Code Briefing
------------------
This web app used Google Web Toolkit has is backbone framework - v2.7.0
   http://www.gwtproject.org/
1. /server/.. contains server side code
2. /shared/.. contains definition of objects shared between client and server
3. /client/.. contains client side code
   
For client side ui we use GWT Material Design Framework - v1.3.3
    http://gwt-material-demo.herokuapp.com/
    
This  is a maven project

dbconfig.properties - server\src\main\resources\com\aasys\sts
-------------------------
database connection can be configured from this file

schema.sql
---------------------------
contains default drop table, create table and inserts to the database


sql commands
------------------------------
server\src\main\java\com\aasys\sts\server\xxxxxxxServiceImpl.java are where most sql commands are located


For DevRunning via Maven in GWT Dev Mode
---------------------------------
1. Compile

     mvn clean install


2. Run locally via tomcat - accessible via http://127.0.0.1:8082/parent/

    mvn tomcat7:run-war-only


3. Start GWT Dev Mode via Maven

    mvn gwt:run -pl web
    

Deploying 
---------------------------------
Method 1: 
     compile and copy the war file form web/target to your webapps folder

Method 2: tux - resin

     compile and inflate the war file to ~/resin
     set permission
     git the resin-url/parent from your browser



