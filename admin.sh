#!/bin/bash
set -e

function usage {
    echo "usage: ./admin [OPTIONS]"
    echo "OPTIONS:"
    echo "build, -b"
    echo "run, -r"
    echo "build-run, -br"
    echo "dbinit, -dbi"
    exit 1
}

removeDockerImage() {
    docker rmi -f servlet_app_server
    docker rmi -f servlet_app_postgres_1
}

buildWar() {
    ./gradlew build
#    docker build --tag servlet_app_postgres_1 -f docker/postgres/Dockerfile .
    docker build --tag servlet_app_server -f docker/jboss/Dockerfile .
}

buildDb() {
    echo Dropping database servlet_app
    psql -h localhost -p 5432 -U postgres < db/dropdb.sql
    echo Prepere user and database
    psql -h localhost -p 5432 -U postgres < db/generatedb.sql
    echo Building database model
    psql -h localhost -p 5432 -U sapp servlet_app < db/model.sql
    psql -h localhost -p 5432 -U sapp servlet_app < db/init.sql
    echo Database model built successfully.
}

runDockerContainer() {
#    docker ps -a -q --filter="name=servlet_app_postgres_1"
#    docker ps -a -q --filter="name=servlet_app_server"
#    docker run -P --publish 127.0.0.1:5432:5432 -d servlet_app_postgres_1
#    docker run -p 8080:8080 -p 9990:9990 -p 8787:8787 servlet_app_server
    pushd ./docker/postgres/
    docker-compose up
    poph
}

if [[ $1 == 'build' || $1 == '-b' ]]
    then
        buildDockerImage
elif [[ $1 == 'remove' || $1 == '-rm' ]]
    then
        removeDockerImage
elif [[ $1 == 'rebuild-run' || $1 == '-br' ]]
    then
        removeDockerImage
        buildWar
        runDockerContainer
elif [[ $1 == 'run' || $1 == '-r' ]]
    then
        runDockerContainer
elif [[ $1 == 'dbinit' || $1 == '-dbi' ]]
    then
        buildDb
else
    usage
fi

