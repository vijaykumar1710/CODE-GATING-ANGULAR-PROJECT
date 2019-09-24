java -jar static-code-analyzers\simian\bin\simian-2.5.10.jar **\*.java
call mvn clean
call mvn compile
call mvn test-compile
call mvn install
echo %errorlevel%
pause