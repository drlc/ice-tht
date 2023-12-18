# ICE Take Hoe Task (THT)
This repo contains the code for the THT Unfinished.

## Assumptions and Info
- The albums are not attached to a specific artists
- A song can have multiple genres, but not artists and albums
- For the sake of simplicity, EntityNotFound, ReferenceKeyError and other exceptions are not handled in the BE, but can be implemented in the future by global handling those exceptions
- The BE does not enforce security, but can be implemented in the future by using Spring Security
- The BE does not enforce validation on input data, but can be implemented in the future
- The BE does not have a proper logging mechanism, but can be implemented in the future
- Data like artists, albums and genres are inserted directly in DB, but in the future a different way to add them can be implemented
- In order to add a song, the artist, genre and album should already exists
- I have limited expereice in FE tests, so I did not implement any tests for the FE

The frontend is simplyfied and does not have any styling. Furthermore I prefer not to use a state management library like Redux or use Contexts, or other libraries (router, typescript instead of plain js, i18n, ag-grid..), to keep the code as simple as possible.




## Setup
### Prerequisites

This repo assumes that [Docker](https://www.docker.com/get-started), [maven](https://maven.apache.org/), [JDK17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) and [Node.js](https://nodejs.org/en/) are already installed and configured.

### Install for unix

- clone the repo and cd into it
- install the backend repo with all its dependencies `./mvnw clean install`
- change the local properties in order to connect to a MySQL db; in case you want to use the dockerized db, you can execute `docker compose up -d`
- run the backend with `./mvnw spring-boot:run`
- this will reveal a user ID between the logs, copy it
- install the frontned repo with all its dependencies `npm install`
- run the frontend with `npm start`
- paste the user ID in the input field and click on the Login button

