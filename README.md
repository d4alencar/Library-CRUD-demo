# Usage
First of all make sure [Java 21](https://www.oracle.com/br/java/technologies/downloads/), [Maven](https://github.com/apache/maven), [Docker](https://github.com/docker/compose) is installed on your computer.

`Clone` the repository, change to its directory, and then start the PostgreSQL container.
```bash
#clone the repo
git clone github.com/d4alencar/crud_desktop

#change to its directory
cd crud_desktop

#start container
docker compose up -d
```

Run `mvn clean` before compiling to avoid issues from previous builds
```bash
#clean project's maven output directory
mvn clean

#compile and execute 
mvn compile exec:java
```

##Screenshots
<img src="imgs/example1.png" alt="example1" />
<img src="imgs/example2.png" alt="add dialog" />

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
