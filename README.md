# AppCompilation  
Adrian Kwok  

## About  
This application is meant to be a compilation of projects. This project was made with Scene Builder for GUI implementations.  

## How to Login:  
A default account is provided if user does not want to connect to an sql database.
username: guest  
password: password  

To make accounts, a mySQL database connection needs to be made by providing access in com.util.ConnectionConfiguration  
The commented line needs to be filled with the local mySQL database table:  
//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/table?useSSL=false", "username", "password");  
