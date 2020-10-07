frontend project link:

            https://github.com/Konrad-Zak/end-project-frontend/tree/c467c39672e18ead2ec29411df5371838b9181f8  

Start-up:

            The application requires an internet connection

DB config:

    Create Db in MySQL(config in application.properties, as in the kodilla course ):
    
    spring.datasource.url=jdbc:mysql://localhost:3306/end_project?serverTimezone=Europe/Warsaw&useSSL=False
    spring.datasource.username=end_user
    spring.datasource.password=end_user
    
    spring.jpa.database=mysql
    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.hibernate.naming.strategy=org.hibernate.cfg.ImprovedNamingStrategy
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

use server port:
       
        server.port=8081

email: 

       spring.mail.host=smtp.gmail.com
       spring.mail.port=587
       spring.mail.username=test.kodilla.2020@gmail.com
       spring.mail.password=kodilla2020
       spring.mail.properties.mail.smtp.auth=true
       spring.mail.properties.mail.smtp.starttls.enable=true
  
external api: 

    curio.api.endpoint=http://numbersapi.com/
    edamam.api.endpoint=https://api.edamam.com/api/food-database/v2/parser
    edamam.api.appId=3e93a065
    edamam.api.appKey=882438b570964258465ac7715c9c41cd
       
first part run: 

    1) First run end-project-backend 
    2) Second run end-project-frontend
    3) go to http://localhost:8080/ or http://localhost:8080/login
    4) create new user or login in app by use user account
    5) use app

Accounts:
        
        Admin account(for use dedicated endpoints):
        Username: ADMIN 
        PASSWORD: ADMIN
        
        User account:
        Username: user
        PASSWORD: user
        

App Description:

    Application consisting of:
    - creating a new user account
    - curiosities of the day
    - calculation of the daily caloric requirement
    - checking the calories of a given product
    - send messages to administrator
    - writing external api errors to the database

    
