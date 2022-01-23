#  Web Application with Spring Boot | Spring Security | Angular | Fullstack | Demo 

![Screenshot 2022-01-23 190229](https://user-images.githubusercontent.com/86077654/150677514-4b58f010-19dd-4ead-868f-5536304b91e1.png)

![Screenshot 2022-01-23 190910](https://user-images.githubusercontent.com/86077654/150677672-06f00ceb-b6eb-4017-bc1c-275cd64cd20c.png)

## Project description
I made this project based on Spring Boot | Angular | fullstack  web application with Spring Boot on backend and Angular on frontend. Also, I have implement Spring Security with JWT for authentication & authorization for user.

Technology
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Angular
- Hibernate
- Maven
## Run project
To run the project, make sure you have installed
- MySQL database

First, open the project and follow path "\src\main\resources\application.properties" to configuation application.properties to connect database.
![Screenshot 2022-01-23 185448](https://user-images.githubusercontent.com/86077654/150677128-4d0ab4f2-22d4-4304-9ba4-22bc3c4a72dc.png)

Second, open MailService class change setFrom("your-email")
![Screenshot 2022-01-23 191107](https://user-images.githubusercontent.com/86077654/150677727-e060deff-6de0-498a-a468-5546e512da68.png)

Finally, open google account and turn on App access is less secure
![Screenshot 2022-01-23 192124](https://user-images.githubusercontent.com/86077654/150678219-20eddd2e-9379-4c9d-9750-426f3f038a73.png)

Make sure you created database "student" and change username, password with your MySQL account. Next change you google account to send email if user request to reset password.

Second, run command "mvn clean package" to install NodeJS, NPM and package backend, frontend into a single jar.

When package success, open terminal at project and run command "java -jar target/demo-0.0.1-SNAPSHOT.jar".

Then open "http://localhost:8080/" to see application. For user, open UserConfig class to see account to login application

Good luck.




