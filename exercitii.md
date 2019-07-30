#### Environment variables
  - env var example
    
    ```
    docker run --rm env-var-example env
    docker run --rm -e HELLO="Hello world" env-var-example
    ```

#### Start PostgreSQL database using **docker container**
  - docker run

    ```
    docker run --rm -e POSTGRES_USER=coffee -e POSTGRES_PASSWORD=coffee -e POSTGRES_DB=coffee -p 5431:5432 postgres:11
    ```

#### Build Spring Boot Application
  - GET `/coffee/{id}`
  - Body: 
    ```
    {
      "id": "1",
      "name": "espresso"
    }
    ``` 

#### Dockerize spring application
  - Dockerfile

    ```
    FROM openjdk:8
    
    COPY target/coffee.jar coffee.jar
    
    ENTRYPOINT ["java", "-jar", "coffee.jar"]
    ```
  - Docker build
  
    `docker build -t coffeeapp:1 .`
    
  - List docker images
    
    `docker images`
    
#### Run app and database with docker-compose  
  - Write `docker-compose.yml` file
  
    ```
    version: '3'
    services:
      coffeeapp:
        image: coffeeapp:4
      postgres:
        image: postgres:11
        environment:
          POSTGRES_USER: coffee
          POSTGRES_PASSWORD: coffee
          POSTGRES_DB: coffee
    ```
    
  - Environment variales. Note: Containers are in the same network
    ```
     version: '3'
     services:
       coffeeapp:
         image: coffeeapp:4
         environment:
           SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/coffee
           SPRING_DATASOURCE_USERNAME: coffee
           SPRING_DATASOURCE_PASSWORD: coffee
       postgres:
         image: postgres:11
         environment:
           POSTGRES_USER: coffee
           POSTGRES_PASSWORD: coffee
           POSTGRES_DB: coffee
     ```
  - Database should be started first
    ```
    version: '3'
    services:
      coffeeapp:
        image: coffeeapp:4
        environment:
          SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/coffee
          SPRING_DATASOURCE_USERNAME: coffee
          SPRING_DATASOURCE_PASSWORD: coffee
        depends_on:
          - postgres
      postgres:
        image: postgres:11
        environment:
          POSTGRES_USER: coffee
          POSTGRES_PASSWORD: coffee
          POSTGRES_DB: coffee
    ```
  - App should expose port outside  
    ```
    version: '3'
    services:
      coffeeapp:
        image: coffeeapp:4
        environment:
          SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/coffee
          SPRING_DATASOURCE_USERNAME: coffee
          SPRING_DATASOURCE_PASSWORD: coffee
        depends_on:
          - postgres
        ports:
          - 8080:8080
      postgres:
        image: postgres:11
        environment:
          POSTGRES_USER: coffee
          POSTGRES_PASSWORD: coffee
          POSTGRES_DB: coffee
    ```
#### Push image to a docker registry
  - Login to a docker registry

    `docker login`  

  - Push image to registry
  
    `docker push danvisan01/coffeeapp:1`
    
#### Pull image from a docker registry and run it locally
  - Push image to registry
  
    `docker pull danvisan01/coffeeapp:1`
    
  - Change `docker-compose.yml` to use the pulled image
  
  - Run docker-compose
  
    `docker-compose up`    
#### Run docker-compose on a remote server (optional)
  - connect to the server
  
    ```
    ssh root@123.123.123.123
    ```
  - run docker-compose  
  
    ```
    docker-compose up -d
    ```
#### Use a self managed cloud database (optional)


#### Docker volumes
  - Host directory volume mount
    ```
    version: '3'
    services:
      coffeeapp:
        image: coffeeapp:4
        environment:
          SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/coffee
          SPRING_DATASOURCE_USERNAME: coffee
          SPRING_DATASOURCE_PASSWORD: coffee
        depends_on:
          - postgres
        ports:
          - 8080:8080
        volumes:
          - ./coffee_docs:/root/coffee_docs
      postgres:
        image: postgres:11
        environment:
          POSTGRES_USER: coffee
          POSTGRES_PASSWORD: coffee
          POSTGRES_DB: coffee
    ```
    
  - Docker managed volume
    ```
    version: '3'
    services:
      coffeeapp:
        image: coffeeapp:4
        environment:
          SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/coffee
          SPRING_DATASOURCE_USERNAME: coffee
          SPRING_DATASOURCE_PASSWORD: coffee
        depends_on:
          - postgres
        ports:
          - 8080:8080
        volumes:
          - coffeeapp_coffee_docs_vol:/root/coffee_docs
      postgres:
        image: postgres:11
        environment:
          POSTGRES_USER: coffee
          POSTGRES_PASSWORD: coffee
          POSTGRES_DB: coffee
    volumes:
      coffeeapp_coffee_docs_vol:
    ```
  
  - Share volumes among containers
    ```
    version: '3'
    services:
      nginx:
        image: nginx
        volumes:
          - coffeeapp_coffee_docs_vol:/usr/share/nginx/html/coffee_docs
        ports:
          - 8081:80
      coffeeapp:
        image: coffeeapp:4
        environment:
          SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/coffee
          SPRING_DATASOURCE_USERNAME: coffee
          SPRING_DATASOURCE_PASSWORD: coffee
        depends_on:
          - postgres
        ports:
          - 8080:8080
        volumes:
          - coffeeapp_coffee_docs_vol:/root/coffee_docs
      postgres:
        image: postgres:11
        environment:
          POSTGRES_USER: coffee
          POSTGRES_PASSWORD: coffee
          POSTGRES_DB: coffee
    volumes:
      coffeeapp_coffee_docs_vol:
    ```
  
#### Build maven application with docker container
  - Build project (files owned by root user)
  
    ```
    docker run --rm \
    -v "$(pwd)":/usr/src/mymaven \
    -w /usr/src/mymaven \
    maven:3.6.1-jdk-8 mvn clean package
    ```
  
  - Make sure files are owned by current user
  
    ```
    docker run --rm \
    -v /etc/group:/etc/group:ro \
    -v /etc/passwd:/etc/passwd:ro \
    -v /home/$USER/.m2:/home/$USER/.m2 \
    -v "$(pwd)":/usr/src/mymaven \
    -u $(id -u $USER):$(id -g $USER) \
    -w /usr/src/mymaven \
    maven:3.6.1-jdk-8 mvn clean package
    ```
  - Do the same thing with docker-compose
  `TODO`