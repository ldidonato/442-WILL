To compile:
javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar EdgeFieldTest.java EdgeTableTest.java MainTester.java

MUST COMPILE BEFORE RUNNING

To run default:
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar:org.junit.runner.JUnitCore MainTester

To ask for help:
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar:org.junit.runner.JUnitCore MainTester -h

To add object string (can only take in one object at a time, f for field, t for table):
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar:org.junit.runner.JUnitCore MainTester -n f,1,field1

To add file name (can handle mutiple objects), example file data.csv:
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar:org.junit.runner.JUnitCore MainTester -f