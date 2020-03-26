## REST API guidelines and best practices
* Find all employees
	* Method - `GET`
	* URI - `/employees`
	
* Find employee by id
	* Method - `GET`
	* URI - `/employees/{id}`
	
* Save employee
	* Method - `POST`
	* URI - '/employees`
	* Body - `Employee Json`

* Full update employee
	* Method - `PUT`
	* URI - `/employees`
	* Body - `Employee Json`
	
* Partial update employee
	* Method - `PATCH`
	* URI - `/employees`
	* Body - `Employee Json`
	
* Delete employee by id
	* Method - `DELETE`
	* URI - `/employees/{id}`*
	
* Always return proper status code
* Implement global exception handling
* Convert entities to model objects and return as API response
* Apply pagination to find/search APIs
