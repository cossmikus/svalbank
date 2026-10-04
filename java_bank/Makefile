.PHONY: up down down-v run dev restart logs build clean help

help: ## Show available commands
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "  %-10s %s\n", $$1, $$2}'

up: ## Start Postgres, Redis, Kafka in the background
	docker compose up -d

down: ## Stop and remove containers (keeps data volumes)
	docker compose down

down-v: ## Stop containers AND wipe volumes (deletes DB data)
	docker compose down -v

run: ## Run the Spring Boot app
	./mvnw spring-boot:run

dev: up run ## Bring infra up, then run the app

restart: down up ## Restart the infra containers

logs: ## Tail container logs
	docker compose logs -f

build: ## Build the jar (skips tests)
	./mvnw clean package -DskipTests

clean: ## Remove build artifacts
	./mvnw clean
