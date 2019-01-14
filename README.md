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
- RegistrationServlet receives data from the registration form. (url: /registration);
- RoomServlet returns room information to client about orders and allows to delete them. (url: /room);
- OrderServlet receives data from the order form. (url: /room/order);
- BillServlet returns the bill to the user for payment. (url: /room/bill);
- PaymentServlet accepts a payment confirmation request. (url: /room/bill/payment);
- LogoutServlet deletes user session data upon logout. (url: /logout);
Filter UserAuthFilter accepts data to verify user authentication. (url: /);

The service package contains interfaces and classes that implement them. 
They are used for intermediate processing of data from servlets and repository.
There are UserService(UserServiceImpl), OrderService(OrderServiceImpl), CityService(CityServiceImpl),
DistanceService(DistanceServiceImpl), CompanyService(CompanyServiceImpl), CalculateServise(CalculateServiseImpl).

The configconnection package contains interface and classe that implement it - DBConnection (MySQLConnection)
It has methods for creating and breaking the connection to the database.

The validator package contains the class Validator that has static methods for checking the data entered into the forms.

The exception package contains my own exception for exception handling.
- DataAlreadyExistsException. The exception is thrown when entered user data is already exist in the database.
- IncorrectInputException. The exception is thrown when the user enters incorrect data.
- NoSuchDataException. The exception is thrown when the data requested is not in the database.

The util package contains utility interfaces and classes.
- PasswordEncryption(PasswordEncryptionImpl) has methods for password encoding.
- DataFormatter(MySQLDateFormatter) has methods for correctly formatting dates in the database.
- Classes CalculatorStrategy(PriceCalculatorByCargoType), PriceCalculatorFactory, DocumentPriceCalculator, 
FreightPriceCalculator, ParselPriceCalculator have methods for calculating the cost of transportation.
The GoF templates Factory Method and Strategy were used when implement algorithms in the methods of these classes. 

The directory by path webapp/WEB-INF/view/.. contains jsp files. JSTL library has been applied in JSP files.
These files are consistent with the view in the Model-View-Controller template.

The webapp directory contains other directories with style files and images.

The application supports working with Cyrillic, the code is partially documented.
Applications are covered by unit tests. JUnit and Mockito libraries were used.
The sessions and filters were used, when develope business logic. 
The logging in the system were processed using Log4j.

Build the application using Maven. All dependencies are in the pom.xml.

Installation Instructions.
At first you need to install the JDK. Then start the root directory (CargoDelivery) from the cmd.
Run the command: mvn compile war:war. You will receive CargoDelivery-1.0.war and directory CargoDelivery-1.0 
by path CargoDelivery/target.

Instruction for launching the application.
Start server Apache Tomcat. Put CargoDelivery-1.0.war to directory ..\apache-tomcat\webapps
or take all directories from CargoDelivery directory and put to ..\apache-tomcat\webapps\ROOT.
