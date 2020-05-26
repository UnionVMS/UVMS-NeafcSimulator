"# UVMS-NeafcSimulator" <br><br><br><br>
start in dev mode :  mvnw compile quarkus:dev  (remote debug on 5005)  <br><br>
                     mvnw compile quarkus:dev -Dsuspend  (wait for debugger attach) <br><br>
 build             :  mvnw clean package   -> output in target as usual <br><br>
 execute           :  java -jar UVMS-NeafcSimulator-1.0-SNAPSHOT-runner <br><br>
 stop              :  ctrl-c <br><br>

 mvnw quarkus:generate-config -Dfile=TESTapplication.properties <br><br>

 list available extentions mvnw quarkus:list-extensions <br><br>
 add an extention if needed : mvnw quarkus:add-extension -Dextensions="hibernate-validator" <br>
