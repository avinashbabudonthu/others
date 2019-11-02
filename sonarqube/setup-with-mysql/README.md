# Setup sonarqube and sonarscanner with MySQL DB
## Start sonarqube
* Sonarqube configured to use mysql
* Download Sonarqube community edition from [here](https://www.sonarqube.org/downloads/)
* Download Sonar scanner from [here](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner/)
* Create a database as name "sonarqube" in mysql
* Extract Sonarqube then open file **sonar.properties**. Update following properties to point to mysql db
```sonar-properties
sonar.jdbc.username=sonar
sonar.jdbc.password=sonar
sonar.jdbc.url=jdbc:mysql://localhost:3306/sonarqube?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&useConfigs=maxPerformance&useSSL=false
sonar.web.port=9000
```
* Go to bin and run **StartSonar.bat** for windows. Other OS go to respective folders in bin and execute **./sonar.sh**
* Open url **http://localhost:9000**. Sonarqube UI should be opened

## Start sonar scanner and point to sonarqube
* Extract Sonar scanner
* Go inside sonar scanner **conf/sonar-scanner.properties**. Add following properties
```sonar-scanner
sonar.host.url=http://localhost:9000
sonar.sourceEncoding=UTF-8
```