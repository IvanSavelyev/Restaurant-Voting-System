INSERT INTO USERS (EMAIL, NAME, PASSWORD)
VALUES ('user1@gmail.com', 'User1', 'password1'),
       ('user2@gmail.com', 'User1', 'password2'),
       ('admin@javaops.ru', 'Admin', 'admin');



INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO DISHES (NAME, PRICE)
VALUES ('FIRST', 1000),
       ('SECOND', 2000),
       ('THIRD', 3000);