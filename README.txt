1.  This example uses JAX-RS with Jersey, Jackson for handling Rest Resource calls and responses.
2.  On the front end I'm using Bootstrap, and JQuery for data display.
3.  In the src/resource is a config.properties file that holds the location of the student file property.
4.  Please update that so that proper call is loaded for the json file.
5.  There is also a src/resources/log4j.properties file for setting the location of your log file.  
    Please update that to your proper log file location.
6.  I used a TreeMap for direct referencing of Students by an auto generated key.  The TreeMap is loaded  
    into a Singleton Pattern StudentSingleton so the json data file is cached.  A conversion to List is 
    completed from TreeMap with Jackson conversion for pass back to AJAX call.


