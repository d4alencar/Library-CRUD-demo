# CRUD desktop app
A simple CRUD desktop application to manage library's books

<img src="imgs/example1.png" alt="app">

## Usage
This project depend on [Java 21](https://www.oracle.com/br/java/technologies/downloads/), [Maven](https://github.com/apache/maven), 
[Docker](https://github.com/docker/compose) to run, certify to have it on your machine.

```
git clone github.com/d4alencar/crud_desktop
cd crud_desktop
docker compose up
mvn clean
mvn compile exec:java
```

### next steps
- [ ] treat SQL Injections
- [x] add search tool
- [x] handle unexpected behaviour
- [ ] improve GUI

### tech stack

| Layer | Technology |
|-------|-------------|
| Language | Java (JDK 21) |
| GUI | Swing |
| Database | PostgreSQL |
| IDE | LunarVim |
| Build Tool | Maven |
