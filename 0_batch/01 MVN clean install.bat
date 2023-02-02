@echo off
set JAVA_HOME=C:\PROGRA~1\JAVA\JDK-19
set M2_HOME=c:\tools\apache-maven-3.8.5

:: The registration server should be up and running for test success. Because of that tests are skipped now.
set SKIP_TESTS=-DskipTests -Dmaven.test.skip=true
pushd %cd%
cd ..
call %M2_HOME%\bin\mvn clean install %SKIP_TESTS%
pause
popd