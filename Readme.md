Demo example that involves following technologies.

1. Spring
2. Hibernate
3. HSQLDB
4. Maven

To execute run following.
$ mvn clean

$ mvn tomcat7:run -Duser=sa -Dpassword=""

You can use browser to get (not post) results.
http://localhost:8080/assignment/users
(user = guest ; password = guest )

It should return 3 entries, unless you have modified SQL scripts, or used POST to add / delete users.

For complete testing, use some REST Client like -> "Advanced REST Client", or similar.

------------------------------------------------------------

