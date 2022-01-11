<!-- [![Codacy Badge](https://app.codacy.com/project/badge/Grade/e7607f460f164f9ead83a8e2fb2772b3)](https://www.codacy.com/gh/IvanSavelyev/Restaurant-Voting-System/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=IvanSavelyev/Restaurant-Voting-System&amp;utm_campaign=Badge_Grade) -->

[![Build Status](https://app.travis-ci.com/IvanSavelyev/Restaurant-Voting-System.svg?branch=master)](https://app.travis-ci.com/IvanSavelyev/Restaurant-Voting-System)

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


| Username         | Password  |    Role    |
|------------------|:---------:|:----------:|
| user@javaops.ru  | password  |    USER    |
| admin@javaops.ru |   Admin   | USER/ADMIN |
| user1@javaops.ru | password1 |    USER    |
| user2@javaops.ru | password2 |    USER    |
| user3@javaops.ru | password3 |    USER    |
| user4@javaops.ru | password4 |    USER    |
| user5@javaops.ru | password5 |    USER    |

The full description of the application is available at the link (when the application is running)

Start the project (mvn spring-boot:run) and go to
[SWAGGER](localhost:8080/swagger-ui.html)

