cd docker-spring-boot
mvn clean package
rem java -jar target/spring-boot-web.jar
rem access http://localhost:8080

docker build -t nickolay3n/test:my-hello-docker1 .
docker run -d -p 8080:8080 --rm -t nickolay3n/test:my-hello-docker1

rem $ sudo docker build -t spring-boot:1.0 .
rem // run it
rem $ sudo docker run -d -p 8080:8080 -t spring-boot:1.0
rem access http://localhost:8080