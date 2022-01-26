INSERT INTO USERS (EMAIL, NAME, PASSWORD)
VALUES ('user@javaops.ru', 'User', '{noop}password'),
       ('admin@javaops.ru', 'Admin', '{noop}Admin'),
       ('user1@javaops.ru', 'User1', '{noop}password1'),
       ('user2@javaops.ru', 'User2', '{noop}password2'),
       ('user3@javaops.ru', 'User3', '{noop}password3'),
       ('user4@javaops.ru', 'User4', '{noop}password4'),
       ('user5@javaops.ru', 'User5', '{noop}password5');

--
INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4),
       ('USER', 5),
       ('USER', 6),
       ('USER', 7);

INSERT INTO RESTAURANTS (NAME)
VALUES ('ASHOT DINNER'),
       ('IVAN LUNCHES'),
       ('USER DISHES');
-- -- --

INSERT INTO MENUS (MENU_DATE, RESTAURANT_ID)
VALUES (CURRENT_DATE, 1),
       (CURRENT_DATE, 2),
       (CURRENT_DATE, 3);

INSERT INTO DISHES (NAME, PRICE, MENU_ID)
VALUES ('COBBER', 1000, 1),
       ('COBBER', 900, 2),
       ('COBBER', 800, 3),
       ('Pork pie', 321, 1),
       ('Pork pie', 313, 2),
       ('Pork pie', 322, 3),
       ('Stargazy pie', 313, 1),
       ('Stargazy pie', 111, 2),
       ('Stargazy pie', 443, 3),
       ('Sunday roast', 554, 1),
       ('Sunday roast', 657, 2),
       ('Sunday roast', 132, 3),
       ('Summer pudding', 111, 1),
       ('Summer pudding', 222, 2),
       ('Summer pudding', 333, 3),
       ('Bangers and mash', 444, 1),
       ('Bangers and mash', 555, 2),
       ('Bangers and mash', 666, 3);


--
INSERT INTO VOTES (VOTE_DATE, RESTAURANT_ID, USER_ID)
VALUES (CURRENT_DATE, 1, 1),
       (CURRENT_DATE, 1, 2),
       (CURRENT_DATE, 2, 3),
       (CURRENT_DATE, 2, 4),
       (CURRENT_DATE, 1, 5),
       (CURRENT_DATE, 1, 6),
       (CURRENT_DATE + 1, 1, 1),
       (CURRENT_DATE + 1, 1, 2),
       (CURRENT_DATE + 1, 2, 3),
       (CURRENT_DATE + 1, 2, 4),
       (CURRENT_DATE + 1, 1, 5),
       (CURRENT_DATE + 1, 1, 6);
