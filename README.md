
# News Article Service

This service is for a publishing company that created an app for reading news articles.

To serve data to the app this backend service will provide RESTful APIs for the following use cases:

1. allow an editor to create/update/delete an article
2. display one article
3. list all articles for a given author
4. list all articles for a given period 
5. find all articles for a specific keyword

The response article usually consists of the following information:

- header
- short description
- text
- publish date
- author(s) - currently preloaded with 2 authors (refer data.sql)
- keyword(s)


## Tools and Frameworks used

- Kotlin (service), Java 8 (acceptance tests), Shell script (CLI)
- Spring boot - rest api
- Gradle on Kotlin DSL - build management
- Hibernate jpa - persistence
- H2 Database - Database
- Mockito - Unit testing
- Serenity - Acceptance testing
- Swagger - documentation


## Compile, Build, Run and Test

You can unzip and import the project into intellij and use IDE features to clean, build, run, unit and acceptance tests etc or
Use chicken cli as a one stop shop and can be executed from the project root `~/news-article/`

### Chicken CLI

    $ ./chicken

    available commands:
       compile                       Clean, compile but do not run tests
       unit                          Run unit tests
       run                           Compile and run the app
       stop                          Stop and kill the app
       bdd                           Run Serenity BDDs
       pre_commit                    Run this as a pre commit hook in CI if required

#### Compile and Build

     ./chicken compile

#### Run the server

The spring boot application has been assigned the default 8080 port.

    ./chicken run
    
NOTE: This runs as a gradle process in background and you may have to manually exit or use chicken stop below.
    
#### Stop the server

This will stop any gradle process executed using chicken run

    ./chicken stop
    
#### Run unit tests

    ./chicken unit
    
#### Run acceptance tests

This uses serenity to run the user acceptance scenario tests and requires a fresh application start loaded with mock data into H2 DB to be used by the tests.

    ./chicken stop
    ./chicken bdd
    ./chicken stop
    
The acceptance tests uses the mock data from database to ensure testing behaviour and live transactions on Application and Offer domain objects.

The acceptance test results can be graphically viewed from users perspective at ~/news-article/news-article-service-acceptance/target/site/serenity/index.html
NOTE: This will be generated after you run the `./chicken bdd` from command line.


## Swagger

Assuming the application is started on port 8080, you may access the swagger ui to test the services,

http://localhost:8080/swagger-ui.html


## H2-Console

Access the h2-console to visualize/query mock data else find the data.sql file for init scripts

http://localhost:8080/h2-console/


## Potential Upgrades/Addons/Enhancements

- Have multiple authors attached to an article - which is currently one author per article
- Add javax validation for controller inputs instead of relying on default entity field validation
- Document db could be a better fit for this kind of a use case and can be used
- Containerize the service if required - will be required in case of attaching a document db container for testing as mentioned above
- Handle performance testing using any testing tools like Gatling, Jmeter
- Integrate a CI tool such as circle ci scripts in place for code pull, build and test
- Add swagger descriptions to the services