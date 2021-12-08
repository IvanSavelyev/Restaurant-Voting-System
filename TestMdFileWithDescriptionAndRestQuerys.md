### Send POST request with json body

POST http://localhost:8080/api/rest/admin/dish/1
Content-Type: application/json

{
"name": "created",
"price": 1999 }

###

GET http://localhost:8080/api/rest/admin/dish/1

<> 2021-12-08T215013.200.json

# VOTES QUERY'S

###

POST http://localhost:8080/api/rest/profile/votes/vote?restaurantId=3

<> 2021-12-08T222848.201.json

###

PATCH http://localhost:8080/api/rest/profile/votes/vote?restaurantId=2

# RESTAURANT ADMIN QUERY'S

###

# FIRST FIND RESTAURANTS

GET http://localhost:8080/api/rest/admin/restaurants

<> 2021-12-08T224934.200.json

# SECOND FIND RESTAURANTS MENUS

###

GET http://localhost:8080/api/rest/admin/menus?restaurantId=1

<> 2021-12-08T224950.200.json

# THEN FIND MENUS DISHES

###

GET http://localhost:8080/api/rest/admin/dishes?menuId=1

<> 2021-12-08T225626.200.json
