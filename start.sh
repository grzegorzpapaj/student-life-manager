set -e

function echo_info {
    echo -e "\033[1;34m[INFO]\033[0m $1"
}

function echo_error {
    echo -e "\033[1;31m[ERROR]\033[0m $1"
}

POSTGRES_IMAGE_TAG="student-life-manager-postgres-image:1.0"
APP_IMAGE_TAG="student-life-manager-app-image:1.0"

echo_info "Zaczynam proces budowania i wdrazania."

echo_info "Buduje obraz Docker Postgres."
docker build -t $POSTGRES_IMAGE_TAG ./database
echo_info "Obraz Postgres zbudowany pomyslnie."

echo_info "Buduje obraz Docker Aplikacji."
docker build -t $APP_IMAGE_TAG ./student-life-manager-app
echo_info "Obraz Aplikacji zbudowany pomyslnie."

echo_info "Wdrazam uslugi przez docker-compose."
docker-compose up -d --build
echo_info "Uslugi dzialaja poprawnie."

echo_info "Obecnie dzialajace kontenery Docker:"
docker ps




