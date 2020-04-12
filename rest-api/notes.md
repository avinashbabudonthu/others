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
* Name API from consumre perspective. Is it self explanatory
* Have documentation that consumers easily understand
	* Swagger
* No secure info in URI like
	* username
	* ssn
* Always use plurals for URI like
	* /students
	* /users
	* /employees

## Richardson Maturity Model
### Level 0
* Include verbs in API like
	* http://localhost:9000/saveStudent
	* http://localhost:9000/getStudent/100
	* http://localhost:9000/getAllStudents
	
### Level 1 - Improper use of HTTP Methods
* Exposing resources with proper URI like
	* http://localhost:9000/students
	* http://localhost:9000/students/100

### Level 2
* Level 1 + Propert use of HTTP Methods
* Exposing resources with proper URI like
	* POST - http://localhost:9000/students
	* GET - http://localhost:9000/students/100
	* DELETE - http://localhost:9000/students

### Level 3
* Level 2 + HATEOAS