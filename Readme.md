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
|------------------|-----------|:----------:|
| user@javaops.ru  | password  | USER       |
| admin@javaops.ru | Admin     | USER/ADMIN |
| user1@javaops.ru | password1 | USER       |
| user2@javaops.ru | password2 | USER       |
| user3@javaops.ru | password3 | USER       |
| user4@javaops.ru | password4 | USER       |

All methods for entitys are present in tables with URL and access type.

 **DISH:**  
| REQUEST TYPE | URL                                    | ACCESS | DESCRIPTION                                 |
|:------------:|----------------------------------------|:------:|---------------------------------------------|
| GET          | api/admin/rest/dishes/{dishId}         | ADMIN  | Return dish with current id                 |
| GET          | api/admin/rest/dishes&menuId=          | ADMIN  | Return list of dishes with current menuId   |
| DELETE       | api/admin/rest/dishes/{dishId}?menuId= | ADMIN  | Remove dish with dishId in menu with menuId |
| PUT          | api/admin/rest/dishes&menuId=          | ADMIN  | Update dish for menuId with request body    |
| POST         | api/admin/rest/dishes&menuId=          | ADMIN  | Create dish for menuId with request body    |

Note: request body example for update/create dish:\
Update:  {"id": 1,"name": "UP_COBBER","price": 666.5}\
Create: {"name": "NEW_COBBER","price": 555.6 }

 **MENU:**  
| REQUEST TYPE | URL                                | ACCESS     | DESCRIPTION                                    |
|:------------:|------------------------------------|:----------:|------------------------------------------------|
| GET          | api/admin/rest/menus?restaurantId=<br>api/rest/menus?restaurantId= | ADMIN<br>USER | Return menu list with dish for restaurantId    |
| GET          | api/admin/rest/menus/{menuId}<br>api/rest/menus/{menuId}      | ADMIN<br>USER | Return menu with dishes for menuId             |
| DELETE       | api/admin/rest/menus/{menuId}      | ADMIN      | Remove menu with menuId                        |
| PUT          | api/admin/rest/menus&restaurantId= | ADMIN      | Update menu for restaurantId with request body |
| POST         | api/admin/rest/menus&restaurantId= | ADMIN      | Create menu for restaurantId with request body |

Note: request body example for update/create menu:\
Update:  {"id": 1,"name": "UP_MENU","date": "YYYY-MM-DD"}\
Create: {"name": "NEW MENU","date": "YYYY-MM-DD"}

**RESTAURANTS:**
| REQUEST TYPE | URL                                | ACCESS     | DESCRIPTION                                    |
|:------------:|------------------------------------|:----------:|------------------------------------------------|
| GET          | api/admin/rest/restaurants<br>api/rest/restaurants           | ADMIN<br>USER      | Return all avaliable restaurants|
| GET          | api/admin/rest/restaurants/{restaurantId}<br>api/rest/restaurants/{restaurantId}       | ADMIN<br>USER      | Return restaurant with for restaurantId|
| DELETE       | api/admin/rest/restaurants/{restaurantId}      | ADMIN      | Remove restaurant with restaurantId|
| PUT          | api/admin/rest/restaurants&restaurantId= | ADMIN      | Update restaurant by restaurantId with request body |
| POST         | api/admin/rest/restaurants&restaurantId= | ADMIN      | Create restaurant by restaurantId with request body |

Note: request body example for update/create restaurant:\
Update:  {"id": 1,"name": "UP RESTAURANT"}\
Create: {"name": "NEW RESTAURANT"}

