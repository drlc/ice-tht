# ICE Take Hoe Task (THT)
This repo contains the code for the THT.


## Setup
### Prerequisites

This repo assumes that [Docker](https://www.docker.com/get-started), [maven](https://maven.apache.org/) and [JDK17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) are already installed and configured.

### Install

- clone the repo and cd into it
- install the repo with all its dependencies: `./mvnw clean install`


### Usage for unix

Navigate to your project folder and run
- `make build`: clean install the project
- `make test`: run the tests
- `docker compose up -d`: setup required external system to run the project