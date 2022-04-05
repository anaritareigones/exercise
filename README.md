#JumiaServicesExercise

### Overview
For this exercise, the goal was to create a backend application in Java, using the provided
database (SQLite 3) to list and categorize country phone numbers. Phone
numbers are categorized by country, state (valid or not valid), country code
and number.
It was necessary to build endpoint(s) for:
* Rendering a list of all phone numbers available in the DB.
* Possibility to filter by country and state.
* Pagination.

This repository contains the implementation for Jumia's exercise.

### Build
Currently, the container created with the Dockerfile, does not correctly retrieve data due to the falied mount of the DB on the container.
Locally the tests are passing and the application is functional.

### Testing
To test the program, feel free to use the endpoints below and other variations of the parameters. It is possible to omit the country, state, 
and pagination. With no parameters, the default response is to show all Customers.
* #### _getCustomerByOptional_ endpoint:
  * http://localhost:8080/getCustomerByOptional/ - Retrieves all Customers, since no property was specified.
  * http://localhost:8080/getCustomerByOptional/?country=Uganda&state=valid - Retrieves all the Customers with valid numbers from Uganda.
  * http://localhost:8080/getCustomerByOptional/?country=Uganda - Retrieves Customers from the country of choice, Uganda in this specific case.
  * http://localhost:8080/getCustomerByOptional/?state=valid - Only customers with valid numbers are retrieved.
  * http://localhost:8080/getCustomerByOptional/?state=invalid - Only customers with invalid numbers are retrieved.
* #### _getCustomerPage_ endpoint:
  * http://localhost:8080/getCustomerPage/?limit=5&page=2 - Retrieves 5 Customers per page, from the third page in this case.
  * http://localhost:8080/getCustomerPage - The defaultValue for page is 0, so the first one, and the defaultValue for the limit of results per page is 5. By not specifying any page or limit, this method retrieves the first page and the first five Customers.
  
  #### Unit Tests
  Unit Tests were also developed and are available in the test folder.

