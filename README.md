# SQuiz App

* ### Online quiz application for Tutors and Students

## How to run in Local Environment

* Run docker compose file `docker-compose up`
* Connect to dockerized db and create a db `CREATE DATABASE squiz;`
* Add following configs to application properties
    * `spring.datasource.url=jdbc:postgresql://localhost:5432/postgres`
    * `spring.datasource.username=postgres`
    * `spring.datasource.password=postgres`
    * `spring.jpa.hibernate.ddl-auto=create-drop`
