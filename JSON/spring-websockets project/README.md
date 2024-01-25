## Spring WebSockets

[DOCKER HUB](https://hub.docker.com/repository/docker/nickolay3n/json_to_file_v1.00/general)
Этот модуль получает json данные из веб сокета и записывает их в файл

### build docker
cd C:\Users\User\Documents\GitHub\java\JSON\spring-websockets\tutorials-master\spring-websockets\target\out\artifacts\spring_websockets_jar
.Dockerfile
FROM openjdk:17
ADD /spring-websockets.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]

cd C:\Users\User\Documents\GitHub\java\JSON\spring-websockets\tutorials-master\spring-websockets\target\out\artifacts\
docker build -t nickolay3n/json_to_file_v1.00 .

### docker run

docker run --restart on-failure:5 --restart=unless-stopped -d -name jsonH nickolay3n/json_to_file_v1.00 \
-e WEB_SOCKET_WITH_PORT=ws://localhost:8080/path \
-e FILE_JSON_PATH=/var/log/json/docker/json_H.json \
-e FILE_JSON_PATH_BUF=/var/log/json/docker/json_Hbuf.json \
-v /var/log/koly/json:/var/log/docker/json/docker

### Relevant articles
- [Intro to WebSockets with Spring](https://www.baeldung.com/websockets-spring)
- [A Quick Example of Spring Websockets’ @SendToUser Annotation](https://www.baeldung.com/spring-websockets-sendtouser)
- [Scheduled WebSocket Push with Spring Boot](https://www.baeldung.com/spring-boot-scheduled-websocket)
- [Test WebSocket APIs With Postman](https://www.baeldung.com/postman-websocket-apis)
- [Debugging WebSockets](https://www.baeldung.com/debug-websockets)
