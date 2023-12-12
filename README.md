# ICE Take Hoe Task (THT)
This repo contains the code for the THT Unfinished.


## Setup
### Prerequisites

This repo assumes that [Docker](https://www.docker.com/get-started), [maven](https://maven.apache.org/), [JDK17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) and [Node.js](https://nodejs.org/en/) are already installed and configured.

### Install for unix

- clone the repo and cd into it
- install the backend repo with all its dependencies
```
cd be
docker compose up -d
./mvnw clean install
```
- this will reveal a user ID between the logs, copy it
- install the frontned repo with all its dependencies
```
cd fe
npm install
```