Spring training
===============

This training covers the basics of using Spring with Wicket, Hibernate and JAX-WS.

Topics:
-------

Some of the central concepts demonstrated:
* How to inject dependencies with Spring
  * Injection versus lookup vs singleton
* Different ways to configure Spring application context
  * Getting the application context
  * @Inject and @Autowire
  * <component-scan>
* Integrating Wicket and Spring
* Integrating Hibernate and Spring
  * Setting up JPA persistence unit
  * Configuring transactions
* Spring and JDBC
  * Using JDBC Template
* Setting up JAX-WS

Approach:
---------

This is a hands-on approach that uses JUnit tests as a driver to solve exercises.
It uses an example application to demonstrate all concepts covered


The application:
----------------

The application is a web based contact manager. Try it out:

1. Start main class com.exilesoft.training.WebServer
2. (Currently gives an error) Go to http://localhost:10080 to see the application front page
3. Click "Add company" to go to the company form
4. Select a "Company Type" from the drop down list
4. (Not working yet) Input company details and save
5. (Not working yet) Click "List companies" to show the new company
6. (Not working yet) Click on a company to select it
7. (Not working yet) Add a person to the company
8. Select "people" from the main meny
9. (Not working yet) Search for the newly created person


Your task:
----------

0. Preconditions
   * Java
   * Maven
   * Git
   * Some Java IDE (Eclipse recommended)
1. Download the code from Github
   * git clone https://github.com/jhannes/spring-training.git
2. Create the project files (example with Eclipse)
   * mvn eclipse:eclipse -DdownloadSources
   * Import the project into Eclipse
3. Run the tests under com.exilesoft.demo and see them fail
4. Fix the tests in com.exilesoft.demo to learn Dependency Injection basics.
   Recommended order:
   * SimpleXmlConfigTest
   * SimpleJavaConfigTest
   * XmlConfigTest (each inner class in order of appearance)
5. Run com.exilesoft.exercise.WebServer as a main class.
   Go to http://localhost:10080 and explore a bit
6. Run  com.exilesoft.exercise.AddressBookServer as a test. It should fail
7. Simple: Find out how to inject the ApplicationInfo into the ApplicationMenuPage
   (Hint: Wicket doesn't understand @Autowired, but...)
8. Simple: Ensure that repositories are injected so that there's the same
   data storage
9. Complex: Implement searching for companies (hint: This requires a new
   method on the CompanyRepository)
10. Complex: Implement association of companies and people
11. Implement separate querying of people
12. The final section is to implement real persistence.
    Spring helps creating Repositories, whether you prefer JPA, Hibernate
    or JDBC. Persistence is already implemented for CompanyType.
13. Simple: Copy AbstractCompanyTypeRepositoryTest and InmemoryRepositoryTest
    and create an implementation for Companies
14. Complex: Choose another implementation of CompanyTypeRepository as a
    template for CompanyRepository
15. Optional, complex: Extract common code from InmemoryRepositories and
    Jpa/Hibernate/JdbcRepositories
16. Complex: Create tests and implement repositories for Person as well
17. Simple: Make Spring use the new repositories for Person and Company.
    THIS WILL FAIL, SINCE YOU PROBABLY DIDN'T IMPLEMENT FIND BY COMPANY NAME
    OR THE PERSON <-> COMPANY RELATIONSHIP
18. Advanced: Create a test in AbstractCompanyTest and the implementation of
    find by company name
19. Advanced: Create a test in AbstractPersonTest and the implementation of
    the relationship between Person and Company
20. Your application should now work!







