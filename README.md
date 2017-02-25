
=================

Running via Maven in GWT Dev Mode
---------------------------------
1. Start the web application in Tomcat 7 via Maven

    mvn clean install
	
    mvn tomcat7:run-war-only

	
Deployed URL: http://127.0.0.1:8082/parent/


2. Start GWT Dev Mode via Maven

    mvn gwt:run -pl web
