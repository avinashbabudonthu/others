# PDF File generation using pdfcrowd library

## Create project using maven
```
mvn archetype:generate -DgroupId=com.pdf.crowd -DartifactId=pdf-crowd -Dversion=1.0 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

## Add gradle
```
gradle init --type pom
```

## Versions
* Maven - **3.5.2**
* Gradle - **5.0**

## Dependencies
* junit:junit:4.12
* com.pdfcrowd:pdfcrowd:4.10.0
* org.slf4j:slf4j-api:1.7.5
* org.slf4j:slf4j-log4j12:1.7.5
* org.projectlombok:lombok:1.18.6

## Examples
* Convert web page to pdf - **com.web.page.to.pdf.ConvertWebPageToPdf**