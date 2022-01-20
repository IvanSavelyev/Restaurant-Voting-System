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

-- INSERT INTO RESTAURANTS (NAME, MENU_ID)
-- VALUES ('ASHOT DINNER', 1),
--        ('IVAN LUNCHES', 2),
--        ('USER DISHES' , 3);

INSERT INTO RESTAURANTS ( NAME)
VALUES ( 'ASHOT DINNER'),
       ( 'IVAN LUNCHES'),
       ( 'USER DISHES' );
-- -- --

INSERT INTO MENUS ( MENU_DATE, RESTAURANT_ID)
VALUES ( CURRENT_DATE, 1),
       ( CURRENT_DATE, 2),
       ( CURRENT_DATE, 3);

INSERT INTO DISHES (NAME, PRICE, MENU_ID)
VALUES ('COBBER', 1000.50,           1),
       ('COBBER', 900.51,            2),
       ('COBBER', 800.52,            3),
       ('Pork pie', 321.52,          1),
       ('Pork pie', 313.53,          2),
       ('Pork pie', 322.55,          3),
       ('Stargazy pie', 313.54,      1),
       ('Stargazy pie', 111.56,      2),
       ('Stargazy pie', 443.57,      3),
       ('Sunday roast', 554.58,      1 ),
       ('Sunday roast', 657.59,      2 ),
       ('Sunday roast', 132.51,      3 ),
       ('Summer pudding', 111.52,    1),
       ('Summer pudding', 222.52,    2),
       ('Summer pudding', 333.52,    3),
       ('Bangers and mash', 444.53,  1),
       ('Bangers and mash', 555.54,  2),
       ('Bangers and mash', 666.55,  3),
       ('Toad in the Hole', 888.56, 1),
       ('Toad in the Hole', 999.57, 2),
       ('Toad in the Hole', 532.5,  3),
       ('Steak and Kidney Pie', 232.58, 1),
       ('Steak and Kidney Pie', 132.52, 2),
       ('Steak and Kidney Pie', 787.51, 3),
       ('Scotch Egg', 314.52, 1),
       ('Scotch Egg', 533.51, 2),
       ('Scotch Egg', 536.53, 3),
       ('Lancashire Hot Pot', 467.55,1),
       ('Lancashire Hot Pot', 469.57,2),
       ('Lancashire Hot Pot', 776.5, 3),
       ('wine', 657.51,1),
       ('wine', 556.52,2),
       ('wine', 554.53,3),
       ('lemonade', 334.52, 1),
       ('lemonade', 332.5,  2),
       ('lemonade', 223.52, 3),
       ('tomato juice', 225.52, 1),
       ('tomato juice', 788.5,  2),
       ('tomato juice', 967.53, 3),
       ('coffee', 564.53, 1),
       ('coffee', 869.54, 2),
       ('coffee', 647.56, 3),
       ('cocoa', 223.57, 1),
       ('cocoa', 111.5,  2),
       ('cocoa', 233.54, 3),
       ('beer', 246.51, 1),
       ('beer', 564.51, 2),
       ('beer', 876.52, 3),
       ('Onion soup', 087.53,1),
       ('Onion soup', 344.54,2),
       ('Onion soup', 422.5, 3),
       ('Tomato soup', 255.5, 1),
       ('Tomato soup', 664.5, 2),
       ('Tomato soup', 443.5, 3),
       ('Chicken broth', 331.52, 1),
       ('Chicken broth', 334.52, 2),
       ('Chicken broth', 335.54, 3),
       ('Fish soup', 336.57, 1),
       ('Fish soup', 377.55, 2),
       ('Fish soup', 337.52, 3),
       ('Miso soup', 955.52, 1),
       ('Miso soup', 554.56, 2),
       ('Miso soup', 800.52, 3),
       ('Vegetable soup', 804.5, 1),
       ('Vegetable soup', 422.5, 2),
       ('Vegetable soup', 369.5, 3),
       ('eclair', 245.52, 1),
       ('eclair', 342.54, 2),
       ('eclair', 246.56, 3),
       ('gingerbread', 422.57, 1),
       ('gingerbread', 432.57, 2),
       ('gingerbread', 325.52, 3),
       ('filling', 321.51,1),
       ('filling', 311.5, 2),
       ('filling', 315.5, 3),
       ('flan', 144.8,  1),
       ('flan', 422.5,  2),
       ('flan', 155.52, 3);
--
INSERT INTO VOTES (VOTE_DATE, RESTAURANT_ID, USER_ID)
VALUES (CURRENT_DATE, 1, 1),
       (CURRENT_DATE, 1, 2),
       (CURRENT_DATE, 2, 3),
       (CURRENT_DATE, 2, 4),
       (CURRENT_DATE, 1, 5),
       (CURRENT_DATE, 1, 6);

-- INSERT INTO MENUS_DISHES (MENU_ID, DISHES_ID)
-- VALUES  (1, 1   ),
--         (2, 2   ),
--         (3, 3   ),
--         (1, 4   ),
--         (2, 5   ),
--         (3, 6   ),
--         (1, 7   ),
--         (2, 8   ),
--         (3, 9   ),
--         (1, 10  ),
--         (2, 11  ),
--         (3, 12  ),
--         (1, 13  ),
--         (2, 14  ),
--         (3, 15  ),
--         (1, 16  ),
--         (2, 17  ),
--         (3, 18  ),
--         (1, 19  ),
--         (2, 20  ),
--         (3, 21  ),
--         (1, 22  ),
--         (2, 23  ),
--         (3, 24  ),
--         (1, 25  ),
--         (2, 26  ),
--         (3, 27  ),
--         (1, 28  ),
--         (2, 29  ),
--         (3, 30  ),
--         (1, 31  ),
--         (2, 32  ),
--         (3, 33  ),
--         (1, 34  ),
--         (2, 35  ),
--         (3, 36  ),
--         (1, 37  ),
--         (2, 38  ),
--         (3, 39  ),
--         (1, 40  ),
--         (2, 41  ),
--         (3, 42  ),
--         (1, 43  ),
--         (2, 44  ),
--         (3, 45  ),
--         (1, 46  ),
--         (2, 47  ),
--         (3, 48  ),
--         (1, 49  ),
--         (2, 50  ),
--         (3, 51  ),
--         (1, 52  ),
--         (2, 53  ),
--         (3, 54  ),
--         (1, 55  ),
--         (2, 56  ),
--         (3, 57  ),
--         (1, 58  ),
--         (2, 59  ),
--         (3, 60  ),
--         (1, 61  ),
--         (2, 62  ),
--         (3, 63  ),
--         (1, 64  ),
--         (2, 65  ),
--         (3, 66  ),
--         (1, 67  ),
--         (2, 68  ),
--         (3, 69  ),
--         (1, 70  ),
--         (2, 71  ),
--         (3, 72  ),
--         (1, 73  ),
--         (2, 74  ),
--         (3, 75  ),
--         (1, 76  ),
--         (2, 77  ),
--         (3, 78  );
--
--
--
--
--
