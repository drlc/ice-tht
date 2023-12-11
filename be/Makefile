.DEFAULT_GOAL := help
SHELL = bash

BASE_FOLDER = tht
	
.PHONY: build
build: ## clean and install
	./mvnw clean install

.PHONY: test
test: ## run all tests
	./mvnw test

.PHONY: mysql
mysql: ## run mysql
	docker compose up -d