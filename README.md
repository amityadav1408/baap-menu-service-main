# baap-menu-service
The Menu Service is a critical component of our Unified API for Food Aggregators. 
It addresses the challenges restaurants face in managing menus across various online ordering platforms. 
By providing a centralized solution for menu updates and synchronization, this service ensures consistency, accuracy, and efficiency in presenting menus to customers.

bapp-menu-service is a springboot service(docker container) ,is connecting to redis(docker container)

This service connects to redis to add and retrive the menu item,.
Redis is used as both database and caching.

## steps to follow to run standalone
###add these properties in  application.properties </br>
```
spring.cloud.discovery.enabled=false
spring.cloud.config.enabled=false
```
Note:not required when discovery and config server are up and running

###comment in pom.xml </br>
```
spring-boot-starter-security dependency
```
Note: currently no security is implemented ,if enable it will redirect to login page

## Step to Execute
To run menu service <br/>

    mvn clean install
    docker-compose build
    docker-compose up

Hit this endpoint to post a menu item </br>
POST http://localhost:8085/api/menu </br>

Request body:
```json
{
  "name":"Cold Coffee",
  "description":"Cold Nescafe Coffee",
  "price":120.00
}
```
To update the menu item </br>
PUT http://localhost:8085/api/menu </br>

Request body:
```json
{
  "id": 1,
  "name":"Cold Coffee",
  "description":"Cold Nescafe Coffee",
  "price":150.00
}
```

To get the menu item(s)
GET http://localhost:8085/api/menu

Sample Response:
```json
[{
  "id": 1,
  "name":"Cold Coffee",
  "description":"Cold Nescafe Coffee",
  "price":120.00
}]
```

## Useful docker-compose commands
- `docker-compose build`
- `docker-compose up -d` (Detached)
- `docker-compose down` (stop)
- `docker-compose restart` (restart the services)
