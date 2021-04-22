# Speedway Service

This service was created to expose the Race car, driver and race related endpoints. 
To refer the below link to get the acceptance criteria.
[Acceptance Criteria](https://learn-2.galvanize.com/cohorts/2560/blocks/1167/content_files/units/05-speedway/01-unit-overview.md)

## How to deploy on docker

### Build the Speedway docker image
To build the Speedway docker image use the following command on terminal
```
docker build -t speedway:dev .
```
Use the following command to create the speedway container in local.
```
docker run -d -p 9000:8080 -e PORT=8080 --name speedway_local --rm speedway:dev
```
Use the following command to create and start the postgres container in local.
```
docker pull postgres
docker run --name my-postgres -e POSTGRES_PASSWORD=open -p 5432:5432 -d postgres
```
Use the following command if postgres container already exist and run the speedway service with given spring profile to communicate the local postgres database.
```
docker start my-postgres
docker run --name speedway-localpg -e PORT=8080 -e SPRING_PROFILES_ACTIVE=localpg -p 9001:8080 -d speedway:dev
```
Use the following command to create and run the speedway service on the docker network.
```
docker network create --driver bridge speedway-network
docker run --name speedway-pg --network speedway-network -e POSTGRES_PASSWORD=open -e POSTGRES_DB=speedway-db -d postgres
docker run --name speedway-networkpg --network speedway-network -e PORT=8080 -e SPRING_PROFILES_ACTIVE=dockerpg -p 9002:8080 -d speedway:dev
```

## How to deploy on cloud
* Create git action with proper yaml task and assign the default branch as `master`.
* The git action yaml should have the actions/checkout@v2 task and Techmmunity/docker-heroku-deploy@v1.0.0 task
* Use the ubuntu-latest as running environment.

You'll then need to add the environment variable to enable the heroku Spring profile.
> SPRING_PROFILES_ACTIVE=heroku

## API document
[Speedway API document](https://speedway-epsilon.herokuapp.com/docs/index.html)
