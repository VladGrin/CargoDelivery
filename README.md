CARGO DELIVERY

This is a web application "Cargo delivery system" provides services for receiving and delivering cargo.
The application has an authorization and authentication system.
Unauthorized users have access to public site information, to calculator of services and directions of delivery.
The user should fill in the registration form to get authorization.
Each authorized user will be able to create baggage orders and view his own orders in his private room. 
The order contains information about the type of baggage, weight, date of delivery and etc. 
After receiving the order, the system generates an invoice and the user has opportunity to pay it in his room.

Description.
A web application consists of the such main packages as model, servlets, service, repository, configconnection, util, 
validator, exception. 

The system contains such entity types as User, Order, City and Company. They lie in the package model.
 - User has an id, a name, a username, a city, a  phone, an email address, a password and a role.
 - Order has an id, an userId, a create date, a city sender, a city receiver, a cargo type, a weight, a send date, 
   a receive date, a recipient information, a price and payment confirmation.
 - City has an id and name.
 - User has an id, a name, an address, a EDRPOU code, a tax code, bank information such as name, mfo, account.
 
Information about the subject area is stored in a database. JDBC API is used to access it.  MySQL is taken as a DBMS.
The database contains such basic tables as 'users', 'orders', 'cities', 'company' corresponding to entities from the
model package and an intermediate table 'distance'.

Interfaces and classes that implement them to access the database are in the repository package.
- UserRepository(UserRepositoryImpl), OrderRepository(OrderRepositoryImpl), CityRepository(CityRepositoryImpl),
CompanyRepository(CompanyRepositoryImpl), DistanseRepository(DistanceRepositoryImpl).

For processing the request from the client and the response are the classes in the package servlets. 
They conform to the controllers in the Model-View-Controller template.
- CalculatorServlet handles requests related to the calculation of the cost of transportation. (url: /calculator);
- DirectionServlet handles requests related to get information about the directions of transportation. (url: /direction);
- RegistrationServlet receive data from the registration form. (url: /registration);
- RoomServlet returns room information to client about orders and allows to delete them. (url: /room);
- OrderServlet receive data from the order form. (url: /room/order);





This is a simple server for tracking todo items.
The system contains two entity types User and Todo.
 - User has an id, a username, an email address and a list of todo items.
 - Todo has an id, a subject, a due date and if the item is done or not.

Start server
The server requires Java SE 8 to run.

Linux/OSX:
 Change access permissions to the start script
   sudo chmod 755 bin/TodoItemServer
 Run the startscript
   bin/TodoItemServer

Windows:
 Run the startscript
   bin\TodoItemServer.bat

The server startup on port 9000 and you can access it at http://localhost:9000


The server provides to following REST API:
---------------
-- Get users --
---------------
Returns a list of users and their todo items.

Request: GET /users
Request body: n/a
Response body:
[
	{
        "id": {id},
		"username": {username},
		"email": {email},
		"todos":
		[
			{
				"subject": {subject},
				"dueDate": {due date, format: yyyy-MM-dd HH:mm:ss},
				"done": {true/false}
			},
			...
		]
	},
	...
]


