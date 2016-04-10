# jpa-bv-demo
Demonstrates the usage of bean validation by persistence providers like EclipseLink and Hibernate

Please activate at most one of the two maven profiles jpa_eclipselink and jpa_hibernate. The JUnit test class PersonTest
includes various methods which insert or update DB entries with bean validtion constraints.
The log output tells you, which EntityManager method is executed and which lifecycle/validation method is called in turn (indented by 2 spaces). 
