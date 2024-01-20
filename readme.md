1. Execution 

To execute tests on local environment type in command lime command - mvn clean test -P local.

To execute tests on qa environment update api.base.url property in pom.xml for qa profile with 
correct value and type in command lime command - mvn clean test -P qa.

To execute smoke test on local environment type in command line command -  
mvn test -Dcucumber.filter.tags=@Smoke -P local.


2. Reporting

To open Cucumber report open target/cucumber-report/cucumber.html file.


 