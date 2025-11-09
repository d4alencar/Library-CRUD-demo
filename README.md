# Usage
This project depend on [Java 21](https://www.oracle.com/br/java/technologies/downloads/), [Maven](https://github.com/apache/maven), 
[Docker](https://github.com/docker/compose) to run, certify to have it on your machine.

Clone the repository and change to its folder
```
git clone github.com/d4alencar/crud_desktop
cd crud_desktop
```
```
docker compose up
mvn clean
mvn compile exec:java
```
<img src="imgs/example1.png" alt="app">

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
