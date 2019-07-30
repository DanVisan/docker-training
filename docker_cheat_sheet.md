#### Common docker commands
  - List images
    ```
    docker images
    ```

  - List running containers
    ```
    docker ps
    ```

  - Run new container
    ```
    docker run image_name
    ```
  
  - Stop container
    ```
    docker stop container_name
    ```
  
  - Remove container
    ```
    docker rm container name
    ```

  - Delete image
    ```
    docker rmi image_name
    ```
    
  - Stop all running containers
    ```
    docker stop $(docker ps -q)
    ```
    
  - Remove all stopped containers
    ```
    docker rm $(docker ps -aq)
    ```
  
  - Execute command in running container
    ```
    docker exec container_name command
    ```
  
  - Start bash terminal(if available) in running container
    ```
    docker exec -it container_name bash
    ```
  - View logs of a running container
    ```
    docker logs container_name
    ```
    
#### Common docker-compose commands
  - Start all services, attached terminal
    ```
    docker-compose up
    ```
  
  - Start all services, detached terminal
    ```
    docker-compose up -d
    ```
  
  - Destroy services
    ```
    docker-compose down
    ```