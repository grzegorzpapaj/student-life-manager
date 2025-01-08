docker build -t student-life-manager-postgres-image:1.0 ./database
docker build -t student-life-manager-app-image:1.0 ./student-life-manager-app

docker-compose up -d
docker ps

