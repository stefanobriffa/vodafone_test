# Stefano Briffa Vodafone Test

This is a test created using Java 8, Spring Boot v2.1 and Spring Data JPA. The requirements were to create REST Web-Service responsible of maintaining 
a database of mobile numbers, that are assigned to clients, along with some related information. The customer database was not in scope and used an
an H2 in-memory. 

## Endpoints

```
HTTP GET /api/mobileSubscribers/GetAll

Requires no parameters.  Will return all the mobile numbers in the database or an empty array in cases were no 
numbers exist
```

```
HTTP POST /api/mobileSubscribers/Search

Requires an JSON object containing possible search parameters.  The endpoint will return 

* the first available mobile number (if successful)
* a BadRequest if no parameters are passed
* a FileNotFound Status if no numbers are returned from the database


Sample JSON required 

{
  "customer_id_owner": 0,
  "customer_id_user": 0,
  "id": 0,
  "msisdn": "string"
}
```

```
HTTP POST /api/mobileSubscribers/Add

Requires an JSON object containning all data required to store a new mobile number.  The endpoint will return 

* the mobile number inserted (is successful)
* a BadRequest if no parameters are passed
* a Conflict Status if the number to be added already exists in the database

Sample JSON required 

{
  "customer_id_owner": 0,
  "customer_id_user": 0,
  "id": 0,
  "msisdn": "string",
  "service_start_date": 0,
  "service_type": "MOBILE_PREPAID"
}

```

```
HTTP DELETE /api/mobileSubscribers/Delete

Requires an JSON object containning all data required find and delete the requested mobile number.  The endpoint 
will return 

* the mobile number inserted (is successful)
* a BadRequest if no parameters are passed
* a Conflict Status if the number to be added is not found in the database  
* a Conflict Status if the number exists in the database but the customer and/or owner IDs do not match

Sample JSON required 

{
  "customer_id_owner": 0,
  "customer_id_user": 0,
  "id": 0,
  "msisdn": "string",
  "service_start_date": 0,
  "service_type": "MOBILE_PREPAID"
}
```

```
HTTP PUT  /api/mobileSubscribers/Update

Requires an JSON object containning all data required find and update the requested mobile number.  The endpoint 
will return 

* the mobile number inserted (is successful)
* a BadRequest if no parameters are passed
* a Conflict Status if the number to be added is not found in the database 
* a Conflict Status if the number exists in the database but the customer and/or owner IDs do not match

Sample JSON required 

{
  "customer_id_owner": 0,
  "customer_id_user": 0,
  "id": 0,
  "msisdn": "string",
  "service_start_date": 0,
  "service_type": "MOBILE_PREPAID"
}
```

## Security

Enable the dependency to secure the REST API

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

## Built With

* [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot v2.1](https://spring.io/blog/2018/10/30/spring-boot-2-1-0)
* [Eclipse] (https://www.eclipse.org/) - IDE
* Spring Data JPA to store and query the data in/from an H2 in-memory database

