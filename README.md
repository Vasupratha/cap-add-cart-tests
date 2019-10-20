The automation tests follows the page object model using Cucumber-Java-Maven technical stack

The tests can be executed from the directory where the project is downloaded to and using the command

mvn test -Dtest=MainRunner -Dconfig.properties.path=src/test/resources/config-myStore.properties

The last scenario of the test would fail as there is a bug
