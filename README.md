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
It uses one example application to demonstrate all concepts

* AddressBookWebTest contains an acceptance test for the whole application
  * Start by implementing ApplicationInfo to get it to pass
  * Use real injection in HomePage.java to start using Spring
  * Use real injection in all other pages instead of dummy dependencies
  * Change injection to use <component-scan>
* JpaCompanyTypeRepositoryTest contains a test for the company type
  * Modify test-persistence.xml to include CompanyType
  * Implement list() to pass the test
* Create JpaCompanyRepositoryTest and JpaCompanyRepository based on InmemoryCompanyRepositoryTest


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

