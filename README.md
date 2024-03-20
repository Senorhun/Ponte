<div align="center">


### **Project Name:** RECORDING OF CONTACT DATA

### **Task Description:**

> "Creating a web form, where is possible to save 
> contact information and you can assign several addresses and phone numbers to them.
> The system allows the user to manage contacts, like save and delete."

### **Description of my project:**

> I envisioned a Springboot 3-layer web application where the
> users are shops, which can have several suppliers, in addition
> the same suppliers may have several locations. The user can record several
> business partners and their locations, addresses and other contact details.
> No more unreadable notes or unorganized phone numbers in the phone.
> With this application, we have organized and quick access to all important data.

### **Main Features:**

> By implementing SpringBoot security, I can hash the password on the tenth at the moment of registration,
> so it is stored hidden in the database for data security. We use email (instead of username) for 
> identification, so each one must be unique, therefore they are validated during the registration.

> CRUD functions are only available to registered users, the admin
> authorized users have additional functions.

> In order to optimize the application, pageable requests can be sent.

> For contacts, it is sufficient to enter the phone number or email,
> these can be modified later, with the appropriate validations.

> Continuous testing of the application was done using postman, a copy of this package 
> can be found next to the README file, but of course with [swagger](http://localhost:8080/swagger-ui/index.html#/),
> the methods of the controller layer can be seen immediately after starting the program.

> There are prefilled sql files in src/main/resources and the admin email and password as well.

> Structure and naming conventions are important so everything can be found easily in the packages

### **Prerequisites:**

> - JDK 17 or later versions
> - PostgreSQL database server
> - The project can be cloned from the [GitHub](https://github.com/Senorhun/Ponte.git) repository

### **Documentation:**
> src/main/java/org/ponte/config
> - Package contains mostly the security classes 
> - Plus the ModelMapper which can help decreasing boilerplate codes

> src/main/java/org/ponte/controller 
> - AppUserController contains CRUD methods for appUsers
> - ContactController contains CRUD methods for contacts and contact information

> src/main/java/org/ponte/domain
> - Package contains all the Entities what are our tables to store data
> - 3 classes and 1 enum UserRole which can be modified for adding more security level

> src/main/java/org/ponte/dto
> - Package contains all the Commands and Infos
> - It is important that everything has a different class like AppUserInfo and AppUserInfoList
> - This way it will be better for future modifications
> - There are validation annotations in Command classes so data can be validated instantly

> src/main/java/org/ponte/exceptionHandling
> - Package contains every exception what are handled with specific messages for better understandability

> src/main/java/org/ponte/repository
> - Package contains every repository what communicates with database
> - There are plenty of space for future ypql queries which can help with specific reads from the database
 
> src/main/java/org/ponte/service
> - Package contains every business logic and some complex validations (e.g.: EmailOrPhone)
 
> src/main/resources
> - For comfortable handling there are sql files preapared to fill database and try out functions
> - Run the application then select and run the sql files appUsers->user_role->contact->location
> - Yaml file is here as well including vital informations about our database
 
> src/target/test-classes/org/ponte/AppUserControllerTest
> - Package contains tests for the smooth and stable running of the application
> - Testing was mainly running through postman
</div>