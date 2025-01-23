set -e

echo "Wylaczam uslugi Docker Compose."

docker-compose down -v

echo "Uslugi wylaczone pomyslnie."

echo "Usuwam obrazy."

docker rmi student-life-manager-postgres-image:1.0
docker rmi student-life-manager-app-image:1.0
