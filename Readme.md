Demo example that involves following technologies.

1. Spring
2. Hibernate
3. HSQLDB
4. Maven
5. Hazelcast
6. Simple Logging Facade for Java(SL4J)

To execute run following.
$ mvn clean

$ mvn tomcat7:run -Duser=sa -Dpassword=""

You can mvn test as well
$ mvn test -Duser=sa -Dpassword=""

You can use browser to get (not post) results.
http://localhost:8080/assignment/users
(user = guest ; password = guest )

It should return 3 entries, unless you have modified SQL scripts, or used POST to add / delete users.

For complete testing, use some REST Client like -> "Advanced REST Client", or Postman, or similar

------------------------------------------------------------

This is a Maven project to implement a simple Spring based web application with a controller exposing the following 4 APIs
• GET /user
• POST /user
• PUT /user
• DELETE /user
Those APIs will perform CRUD operations using Hibernate as persistence framework on a User entity with at minimum the following fields:
• id (long) / primary key
• username (String) / unique
• password
• status (String) / possible values: Activated/Deactivated
These 4 APIs are secured with Spring Security using BASIC authentication.
There are unit tests for controllers, services and DAO classes.

-----------------------

Package structure
Following is the project structure: 
Package                                       Description
• com.uxpsystems.assignement.config             package for web application configuration classes
• com.uxpsystems.assignement.controller         package for Spring controller classes
• com.uxpsystems.assignement.service            package for service classes
• com.uxpsystems.assignement.dao                package for DAO classes

Spring Annotation and Hibernate Annotation has been used here.

For simplicity, have used in-memory DB - HSQLDB.

As runtime, have used embedded Tomcat.

-------------------------------------------

Logging: -
Spring is using by default Jakarta Commons Logging, but in this project I have used Simple Logging Facade for Java (SLF4J).
I removed the commons-logging by putting it in exclusions in pom.xml, and added dependencies for SLF4J.

--------------------------------------------

Additional things done.

1. Configured hibernate to use Hazelcast as second level cache.
2. Developed a Secure service layer methods

