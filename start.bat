@echo off
REM Exit immediately if a command fails
SETLOCAL ENABLEEXTENSIONS ENABLEDELAYEDEXPANSION
set "ERROR_FLAG=0"

echo Building PostgreSQL Docker image...
docker build -t student-life-manager-postgres-image:1.0 .\database
if ERRORLEVEL 1 set "ERROR_FLAG=1"

echo Building Application Docker image...
docker build -t student-life-manager-app-image:1.0 .\student-life-manager-app
if ERRORLEVEL 1 set "ERROR_FLAG=1"

echo Starting Docker Compose services...
docker-compose up -d
if ERRORLEVEL 1 set "ERROR_FLAG=1"

echo Listing running Docker containers...
docker ps
if ERRORLEVEL 1 set "ERROR_FLAG=1"

if %ERROR_FLAG%==1 (
    echo An error occurred during execution.
    exit /b 1
) else (
    echo Script executed successfully.
)
