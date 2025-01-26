
@echo off
REM Kontynuuj wykonywanie poleceń nawet jeśli niektóre z nich zakończą się błędem
SETLOCAL ENABLEEXTENSIONS ENABLEDELAYEDEXPANSION
set "ERROR_FLAG=0"

echo Zatrzymywanie usług Docker Compose i usuwanie woluminów...
docker-compose down -v
if ERRORLEVEL 1 (
    echo Błąd: Zatrzymywanie Docker Compose i usuwanie woluminów nie powiodło się.
    set "ERROR_FLAG=1"
)

echo Usuwanie obrazu PostgreSQL...
docker rmi student-life-manager-postgres-image:1.0
if ERRORLEVEL 1 (
    echo Błąd: Usuwanie obrazu PostgreSQL nie powiodło się.
    set "ERROR_FLAG=1"
)

echo Usuwanie obrazu aplikacji...
docker rmi student-life-manager-app-image:1.0
if ERRORLEVEL 1 (
    echo Błąd: Usuwanie obrazu aplikacji nie powiodło się.
    set "ERROR_FLAG=1"
)

echo Operacje zakończone.

if "%ERROR_FLAG%"=="1" (
    echo.
    echo Wystąpiły błędy podczas wykonywania skryptu.
    echo Proszę sprawdzić komunikaty powyżej.
    exit /b 1
) else (
    echo Wszystkie operacje zostały wykonane pomyślnie.
    exit /b 0
)
