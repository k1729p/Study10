@echo off
set JAVA_HOME=C:\PROGRA~1\JAVA\JDK-21
set JAR=..\microservices-registration\target\Study10_microservices-registration-1.0.0-SNAPSHOT.jar
if exist %JAR% call %JAVA_HOME%\bin\java -jar %JAR%
pause