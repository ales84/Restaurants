[![Codacy Badge](https://api.codacy.com/project/badge/Grade/0217fb529a784d24b02d5a70e7873fa5)](https://www.codacy.com/app/ales84/Voting-System?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ales84/Voting-System&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/ales84/Voting-System.svg?branch=master)](https://travis-ci.org/ales84/Voting-System)
[![Dependency Status](https://dependencyci.com/github/ales84/Voting-System/badge)](https://dependencyci.com/github/ales84/Voting-System)

# Restaurant voting system

## Task:

REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed
 * Each restaurant provides new menu each day.

## API

*Service uses basic authorization.*

#### USERS

**Following resources available only for administrators:**

* **GET /api/v1/admin/users - return users list**  
`curl -s http://localhost:8080/voting/api/v1/admin/users --user admin:admin`
      
* **GET /api/v1/admin/users/{id} - return user**  
`curl -s http://localhost:8080/voting/api/v1/admin/users/1 --user admin:admin`
      
* **POST /api/v1/admin/users - create user**  
`curl -s -X POST -d '{"name": "user3","password": "pass3","roles": ["ROLE_USER"]}' -H 'Content-Type: application/json' http://localhost:8080/voting/api/v1/admin/users --user admin:admin`

* **PUT /api/v1/admin/users/{id} - update user**  
`curl -s -X PUT -d '{"name": "userupdated","password": "passupdated","roles": ["ROLE_USER"]}' -H 'Content-Type: application/json' http://localhost:8080/voting/api/v1/admin/users/1 --user admin:admin`

* **DELETE /api/v1/admin/users/{id} - delete user**  
`curl -s -X DELETE http://localhost:8080/voting/api/v1/admin/users/1 --user admin:admin`

**Following resources available for all authorized users:**

* **GET /api/v1/profile - return user profile**  
`curl -s http://localhost:8080/voting/api/v1/profile --user user1:pass1`

* **PUT /api/v1/profile - update user profile**  
`curl -s -X PUT -d '{"name": "userupdated","password": "passupdated"}' -H 'Content-Type: application/json' http://localhost:8080/voting/api/v1/profile --user user1:pass1`

* **DELETE /api/v1/profile - delete user profile**  
`curl -s -X DELETE http://localhost:8080/voting/api/v1/profile --user user1:pass1`

#### RESTAURANTS

**Following resources available only for administrators:**

* **GET /api/v1/admin/restaurants - return restaurants list**  
`curl -s http://localhost:8080/voting/api/v1/admin/restaurants --user admin:admin`

* **GET /api/v1/admin/restaurants/{id} - return restaurant**  
`curl -s http://localhost:8080/voting/api/v1/admin/restaurants/4 --user admin:admin`

* **POST /api/v1/admin/restaurants - create restaurant**  
`curl -s -X POST -d '{"name": "Empire"}' -H 'Content-Type: application/json' http://localhost:8080/voting/api/v1/admin/restaurants --user admin:admin`

* **PUT /api/v1/admin/restaurants/{id} - update restaurant**  
`curl -s -X PUT -d '{"name": "UpdatedName"}' -H 'Content-Type: application/json' http://localhost:8080/voting/api/v1/admin/restaurants/4 --user admin:admin`

* **DELETE /api/v1/admin/restaurants/{id} - delete restaurant**  
`curl -s -X DELETE http://localhost:8080/voting/api/v1/admin/restaurants/4 --user admin:admin`

**Following resources available for all authorized users:**

* **GET /api/v1/restaurants - return restaurants list**  
`curl -s http://localhost:8080/voting/api/v1/restaurants --user user1:pass1`

#### MENUS

**Following resources available only for administrators:**

* **GET /api/v1/admin/restaurants/{id}/menus?date={date} - return menus list for a specified date**  
`curl -s http://localhost:8080/voting/api/v1/admin/restaurants/4/menus?date=2017-06-08 --user admin:admin`

* **GET /api/v1/admin/restaurants/{id}/menus/{id} - return menu**  
`curl -s http://localhost:8080/voting/api/v1/admin/restaurants/4/menus/10 --user admin:admin`

* **POST /api/v1/admin/restaurants/{id}/menus - create menu**  
`curl -s -X POST -d '{"date": "2017-07-01","dishes": [{"name": "Pizza","price": 100},{"name": "Bread","price": 10}]}' -H 'Content-Type: application/json' http://localhost:8080/voting/api/v1/admin/restaurants/4/menus --user admin:admin`

* **PUT /api/v1/admin/restaurants/{id}/menus/{id} - update menu**  
`curl -s -X PUT -d '{"date": "2017-06-08","dishes": [{"name": "Pizza","price": 200}]}' -H 'Content-Type: application/json' http://localhost:8080/voting/api/v1/admin/restaurants/4/menus/10 --user admin:admin`

* **DELETE /api/v1/admin/restaurants/{id}/menus/{id} - delete menu**  
`curl -s -X DELETE http://localhost:8080/voting/api/v1/admin/restaurants/4/menus/10 --user admin:admin`

**Following resources available for all authorized users:**

* **GET /api/v1/menus?date={date} - return menus list for a specified date**  
`curl -s http://localhost:8080/voting/api/v1/menus?date=2017-06-08 --user user1:pass1`

#### VOTES

**Following resources available only for administrators:**

* **GET /api/v1/admin/votes?date={date} - return votes list for a specified date**  
`curl -s http://localhost:8080/voting/api/v1/admin/votes?date=2017-06-08 --user admin:admin`

**Following resources available for all authorized users:**

* **POST /api/v1/restaurants/{id}/votes - create vote for today's date**  
`curl -s -X POST http://localhost:8080/voting/api/v1/restaurants/4/votes --user user1:pass1`
