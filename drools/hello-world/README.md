# Drools Hello World

## Requirement
* Validate Students
* Students with age greater than 21 are not allowed for Engineering
* Student age less than 15 are not allowed for Engineering

## Maven command
```
mvn archetype:generate -DgroupId=hello.world -DartifactId=hello-world -Dversion=1.0 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

## Steps
* Dependencies - refer [pom.xml](pom.xml)
* [rule1.drl](src/main/resources/rules/rule1.drl)
* [kmodule.xml](src/main/resources/META-INF/kmodule.xml)
* Model class - [Student.java](src/main/java/hello/world/model/Student.java)
* Main class to initiate rule execution - [StudentAgeValidation.java](src/main/java/hello/world/StudnetAgeValidation.java)
	* Create student list on which rules has to execute
	* Create `KieContainer`, `KieSession` objects
	* To `KieSession` give `ksession` name given in [kmodule.xml](src/main/resources/META-INF/kmodule.xml) file
	* Create `StudentAgeValidation` object and set at `global` so that can be access in drl file for multiple rules
		* `StudentAgeValidation` object we are using in [rule1.drl](src/main/resources/rules/rule1.drl) as `global StudentAgeValidation studentAgeValidation;`
		* `StudentAgeValidation` used in [rule1.drl](src/main/resources/rules/rule1.drl) to call `printMessage` method