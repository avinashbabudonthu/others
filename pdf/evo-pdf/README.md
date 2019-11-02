# Spring Boot 2 Bean Validation

## Create project using maven
```
mvn archetype:generate -DgroupId=com.evo.pdf -DartifactId=evo-pdf -Dversion=1.0 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

## Add gradle
```
gradle init --type pom
```

## Dependencies
* junit:junit:4.12

## Examples
* URL to PDF converter - **com.url.to.pdf.URLToPdf**
* HTML to PDF converter - **com.html.to.pdf.HTMLToPdf**

## Execute as an executable 
* Install evo pdf jar to local maven repository
```
mvn install:install-file -Dfile=[path]/evohtmltopdf-v7.5.jar -DgroupId=evohtmltopdf -DartifactId=evohtmltopdf -Dversion=7.5 -Dpackaging=jar
```
* Create package using maven
```
mvn clean compile package
```
* Execute jar
```
java -jar target\evo-pdf-jar-with-dependencies.jar "[path]/[input-html-file-name].html" "[path]/[output-pdf-file-name].pdf"
```