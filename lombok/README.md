# Lombok Library

## Requirement
* Using **lombok** library

## Create project using maven
```
mvn archetype:generate -DgroupId=lombok -DartifactId=lombok  -Dversion=1.0 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

## Add gradle
```
gradle init --type pom
```

## Examples
* [Log Annotation](src/test/java/com/lombok/practice/LogAnnotation.java)
* [Lombok](src/test/java/com/lombok/practice/LombokPractice.java)
* [SLF4J](src/test/java/com/lombok/practice/Slf4JAnnotation.java)
* [Value Annotation](src/test/java/com/lombok/practice/ValueAnnotation.java)