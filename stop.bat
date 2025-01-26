@echo off
REM Wyłączanie usług Docker Compose i usuwanie woluminów
echo Zatrzymywanie usług Docker Compose i usuwanie woluminów...
docker-compose down -v
IF ERRORLEVEL 1 (
    echo Wystapil blad podczas zatrzymywania Docker Compose.
    EXIT /B 1
)

REM Usuwanie obrazu PostgreSQL
echo Usuwanie obrazu PostgreSQL...
docker rmi student-life-manager-postgres-image:1.0
IF ERRORLEVEL 1 (
    echo Wystapil blad podczas usuwania obrazu PostgreSQL.
    EXIT /B 1
)

REM Usuwanie obrazu aplikacji
echo Usuwanie obrazu aplikacji...
docker rmi student-life-manager-app-image:1.0
IF ERRORLEVEL 1 (
    echo Wystapil blad podczas usuwania obrazu aplikacji.
    EXIT /B 1
)

echo Operacje zakończone pomyślnie.
