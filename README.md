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
### sample data 
Authenticated useremail and password to use
 1. _email_: **user1@gmail.com**
 2. _password_: **user1**
     
### set IDE environment
- download and install any java ide(intellij, Eclipse, etc..)
- any DataBase Management System (DBMS) like PostgreSQL, MYSQLworkbench,etc...
- after installation of DBMS upload the sqlScripts:
 1. database
 2. tables
- after installation of java ide navigate to the extracted file then open with the ide
## application Structure
```plaintext
com.example.blog.QT_Global_Blog/
├── AuthenticationConfig/
│   ├── AuthConstants.java
│   ├── JwtAuthFilter.java
│   ├── JwtService.java
├── configuration/
│   ├── Application.java
│   ├── SecurityConfig.java
├── controller/
│   ├── AuthenticationController.java
├── DaoRepository/
│   ├── BlogUserRepository.java
│   ├── CommentRepository.java
│   ├── PostRepository.java
│   ├── RoleRepository.java
├── PayLoad/
│   ├── AuthenticationRequest.java
│   ├── AuthenticationResponse.java
│   ├── RegisterRequest.java
├── postcontroller/
│   ├── CommentController.java
│   ├── PostController.java
├── postEntity/
│   ├── Comment.java
│   ├── Post.java
├── ResponseHandler/
│   ├── Response.java
├── service/
│   ├── AuthService.java
├── UserEntities/
│   ├── BlogUser.java
└── ├── BlogUser.java
```

**_1. AuthenticationConfig :_** contains the jwt configuration like token creation, token generation, claims extraction, etc...

**_2. Configuration :_** contains security configuration like authentication and authorization and session management

**_3. controller :_** contains authentication endpoints

**_4. DaoRepository :_** data access Layer where database communication is happenning

**_5. PayLoad :_** this contains client Request page for authentication and registration

**_6. postController :_** contains endpoint for CRUD Operation on Post and its comments

**_7. postEntity :_** contains classes that are mapped to database Post and comment tables

**_8. ResponseHandler :_** contains a custom class that handles HttpResponse showing status code and message

**_9. service :_** contains authentication mechanism for generating a token for the authenticated or Registered user

**_10. userEntities :_** contains classes mapped to the database bloguser and role tables


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
```plaintext
├── resources/
│   ├── static/
│   ├── templates/
└── ├── application.properties
```
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
| get all posts | http://localhost:8080/api/posts|
| create a post | http://localhost:8080/api/posts/create-post|
| update a post | http://localhost:8080/api/posts/{id}|
| delete a post | http://localhost:8080/api/posts/{id}|
| comment on post | http://localhost:8080/api/comments/{postId}|
| get all comment on a single post | http://localhost:8080/api/comments/{postId}|
| delete comment | http://localhost:8080/api/comments/{postId}|




