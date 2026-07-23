@echo off
title Smart Job Matching Developer Toolkit
color 0A

:menu
cls
echo ============================================
echo      SMART JOB MATCHING DEVELOPER MENU
echo ============================================
echo.
echo 1. Start Project
echo 2. Stop Project
echo 3. Build Backend
echo 4. Open VS Code
echo 5. Open MySQL
echo 6. Git Status
echo 7. Git Add + Commit + Push
echo 8. Exit
echo.

set /p choice=Enter Choice:

if "%choice%"=="1" goto start
if "%choice%"=="2" goto stop
if "%choice%"=="3" goto build
if "%choice%"=="4" goto vscode
if "%choice%"=="5" goto mysql
if "%choice%"=="6" goto git
if "%choice%"=="7" goto push
if "%choice%"=="8" exit

goto menu

:start
call start-project.bat
pause
goto menu

:stop
for /f "tokens=5" %%a in ('netstat -ano ^| find ":8081"') do taskkill /F /PID %%a >nul 2>&1
for /f "tokens=5" %%a in ('netstat -ano ^| find ":5173"') do taskkill /F /PID %%a >nul 2>&1
echo.
echo Project stopped successfully.
pause
goto menu

:build
cd backend
mvn clean install
cd ..
pause
goto menu

:vscode
code .
goto menu

:mysql
start cmd /k "mysql -u root -p"
goto menu

:git
git status
pause
goto menu

:push
echo.
set /p msg=Enter Commit Message:

git add .
git commit -m "%msg%"
git push

pause
goto menu