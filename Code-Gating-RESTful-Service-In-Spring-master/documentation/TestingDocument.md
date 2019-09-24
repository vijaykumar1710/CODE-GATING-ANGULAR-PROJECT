
* Testing of API for positive outcomes has been done manually using swagger api
* Each service has functions such as generateCommand() which does not need to be tested since the testcase for running different tools verifies the correct as well as incorrect behaviour of that function.
* Negative test cases for have been done using Junit and main purpose of those is to verify that exceptions are raised when either user has supplied with invalid input or there is an internal server error which may occur due to either config files not present or reports to be parsed not being generated.
