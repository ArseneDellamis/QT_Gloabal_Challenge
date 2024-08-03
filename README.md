# BLOG Application

blog application is an application creating content, post them and user can add comment
update,edit,delete only when the user is Authenticated and Authorized

## Technologies
program backend uses:
    1. Java Spring Boot
    2. postgreSQL
    3. postman

## Application Set-up and Configurations

### configuration files
- visit the git repository to get the project file url:https://github.com/ArseneDellamis/QT_Gloabal_Challenge
- extract the file
- the file contains:
  1. QT_Global_Blog
  2. sql_script
### set IDE environment
- download and install any java ide(intellij, Eclipse, etc..)
- any DataBase Management System (DBMS) like PostgreSQL, MYSQLworkbench,etc...
- after installation of DBMS upload the sqlScripts:
 1. database
 2. tables
- after installation of java ide navigate to the extracted file then open with the ide

### Dependencies
project dependencies available for the projects

| dependency | usage |
|------------|------------|
| spring data jpa | relational database access|
| spring web | creation of RestAPis|
| postgres driver| postgres interraction|
| lombok | getter,setter and constructor auto generation|
| spring validation | user validation|
| spring security | user Authentication & authorization|
|Junit 5 | junit test|
|json web token | token creation and generation|


## Database configuration
- navigate to the DMBS(postgresql, mysql workbench, etc...) then upload the sql scripts
1. database first
2. tables

|Database | table |
|---------|-------|
|qt_global_blog | blog_user|
| | role|
| | post|
| | comment|

- you can also configure you database in intellij navigate to resources/application.properties
 if you want to use your own database

### application.properties configuration for database

spring.datasource.url=jdbc:postgresql://localhost:5432/database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

## RestApi and Endpoint
the application provides a list of endpoints for performing different task and are divided in 2 parts
1. Permitted RestApi
2. authenticated RestApi

   
### Permitted RestApi
these are Apis that are accessible by user
|description | RestApi|
|------------|--------|
|user registration | http://localhost:8080/api/auth/register|
|user authentication | http://localhost:8080/api/auth/authenticate|

### authenticated RestApi
these are only for authorized users to perform action like create, update, read, delete
data of our blog application

|description | RestApi|
|------------|--------|
|get all posts | http://localhost:8080/api/posts|
|create a post | http://localhost:8080/api/posts/create-post|

