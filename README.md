# COFFEE APPLICATION 

## Developers setup:

#### Start Database
```
docker run --rm -e POSTGRES_USER=coffee -e POSTGRES_PASSWORD=coffee -e POSTGRES_DB=coffee -p 5431:5432 --name=coffee_db postgres:11
```

#### Build the app
`docker build -t coffeeapp:latest .`

#### Start the stack with docker-compose
`docker-compose up`

#### Build project with docker container
```
docker run --rm -v /etc/group:/etc/group:ro -v /etc/passwd:/etc/passwd:ro -v /home/$USER:/home/$USER -v "$(pwd)":/usr/src/mymaven \
-u $(id -u $USER):$(id -g $USER) \
-w /usr/src/mymaven maven:3.6.1-jdk-8 mvn clean install

```