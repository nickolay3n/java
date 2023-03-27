# Here I'll dockerize my projects
##run
docker-compose up --build -d
docker-compose up --build --force-recreate
docker-compose down;docker-compose pull;docker-compose rm -f


#pgadmin находится по адресу
[pgadmin](http://localhost:5050/browser/)http://localhost:5050/browser/

#подключиться к контейнеру можно:
docker container exec -it postgres_sostav /bin/sh
psql -U postgres -d dbsostav
docker build -t nickolay3n/test:postgres-docker-dbsostav .
docker run --name postgres-docker-dbsostav -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=1234 -e POSTGRES_DB=dbsostav -d -v "C:\progs\_docker\postgres\init":/docker-entrypoint-initdb.d postgres:13.3


docker compose up

dockers/postgres/
## dockers/postgres/

[dockers/postgres/](https://github.com/nickolay3n/java/tree/master/dockers/postgres) is a example of postgres docker with pgadmin usage in docker-compose edition

+ [postgresql]
+ [pgadmin]

## Questions

You can ask me here: nickolay3n@gmail.com