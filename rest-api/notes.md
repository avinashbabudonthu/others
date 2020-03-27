## REST API guidelines and best practices
* Find all employees
	* Method - `GET`
	* URI - `/employees`
	* Return body with status `200 OK` if data found
	* Return status `204 No Content` if no data found
	
* Find employee by id
	* Method - `GET`
	* URI - `/employees/{id}`
	
* Save employee
	* Method - `POST`
	* URI - '/employees`
	* Body - `Employee Json`
	* Return URI to find newly created object like - `/employees/{id}`
	* Return status code `201 - Created`
		* Spring Boot - `org.springframework.http.ResponseEntity.created(uri).body(student);`

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
* Add validations 
	* Return proper error code and error messages
