Restaurant Voting Api
Techical requirements.
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

    2 types of users: admin and regular users
    Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
    Menu changes each day (admins do the updates)
    Users can vote on which restaurant they want to have lunch at
    Only one vote counted per user
    If user votes again the same day:
        If it is before 11:00 we assume that he changed his mind.
        If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it (better - link to Swagger).

P.S.: Make sure everything works with latest version that is on github :)
P.P.S.: Assume that your API will be used by a frontend developer to build frontend on top of that.

So, all available users and credentials are present in current table.

| Username         | Password  | Role       |
|------------------|-----------|------------|
| user@javaops.ru  | password  | USER       |
| admin@javaops.ru | Admin     | USER/ADMIN |
| user1@javaops.ru | password1 | USER       |
| user2@javaops.ru | password2 | USER       |
| user3@javaops.ru | password3 | USER       |
| user4@javaops.ru | password4 | USER       |

For Administrator defines methods presents in next URL:\
For Dish:

| REQUEST PARAMETER         | URL  | ACCESS       | DESCRIPTION|
|------------------|-----------|------------|------------|
| GET | api/admin/rest/dishes/{dishId}  | ADMIN       | Return dish with current id
| GET | api/admin/rest/dishes&menuId=     | ADMIN | Return list of dishes with current menuId
| DELETE | api/admin/rest/dishes/{dishId}?menuId= | ADMIN       | Remove dish with {dishId} in menu with menuId
| PUT | api/admin/rest/dishes&menuId= | ADMIN       | Update dish for menuId with request body
| POST | api/admin/rest/dishes&menuId= | ADMIN       |Create dish for menuId with request body
