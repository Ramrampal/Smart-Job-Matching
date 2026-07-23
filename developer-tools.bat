@echo off
setlocal EnableDelayedExpansion

title Smart Job Matching Toolkit
color 0A

REM Always run from the project directory
cd /d "%~dp0"

:menu
cls
echo ============================================
echo      SMART JOB MATCHING TOOLKIT
echo ============================================
echo.
echo 1. Start Project
echo 2. Build Backend
echo 3. Health Check
echo 4. Git Push
echo 5. Open Project Folder
echo 6. Open MySQL
echo 7. Exit
echo.
set /p choice=Enter Choice:

if "%choice%"=="1" goto start
if "%choice%"=="2" goto build
if "%choice%"=="3" goto health
if "%choice%"=="4" goto push
if "%choice%"=="5" goto folder
if "%choice%"=="6" goto mysql
if "%choice%"=="7" exit

goto menu


:start
cls
echo ============================================
echo          STARTING PROJECT
echo ============================================
echo.

call start-project.bat

echo.
pause
goto menu


:build
cls
echo ============================================
echo          BUILDING BACKEND
echo ============================================
echo.

pushd backend

set "MVN_PATH="

for /f "delims=" %%i in ('where mvn.cmd 2^>nul') do (
    set "MVN_PATH=%%i"
    goto buildFound
)

:buildFound

if not defined MVN_PATH (
    echo ERROR: Maven not found.
    echo.
    pause
    popd
    goto menu
)

echo Using Maven:
echo !MVN_PATH!
echo.

call "!MVN_PATH!" clean install

if errorlevel 1 (
    echo.
    echo BUILD FAILED.
) else (
    echo.
    echo BUILD SUCCESSFUL.
)

popd

echo.
pause
goto menu


:health
cls
echo ============================================
echo         PROJECT HEALTH CHECK
echo ============================================
echo.

REM -------- Java --------
where java >nul 2>&1
if errorlevel 1 (
    echo Java           : NOT FOUND
) else (
    echo Java           : OK
)

REM -------- Maven --------
where mvn >nul 2>&1
if errorlevel 1 (
    echo Maven          : NOT FOUND
) else (
    echo Maven          : OK

    for /f "tokens=3" %%v in ('mvn -version ^| findstr /B /C:"Apache Maven"') do (
        echo Maven Ver.     : %%v
    )

    for /f "delims=" %%i in ('where mvn.cmd') do (
        echo Maven Path     : %%i
    )
)

REM -------- Node --------
where node >nul 2>&1
if errorlevel 1 (
    echo Node.js        : NOT FOUND
) else (
    echo Node.js        : OK
)

REM -------- npm --------
where npm >nul 2>&1
if errorlevel 1 (
    echo npm            : NOT FOUND
) else (
    echo npm            : OK
)

REM -------- Git --------
where git >nul 2>&1
if errorlevel 1 (
    echo Git            : NOT FOUND
) else (
    echo Git            : OK
)

echo.

REM -------- Backend --------
netstat -ano | findstr ":8081" >nul
if errorlevel 1 (
    echo Backend        : STOPPED
) else (
    echo Backend        : RUNNING
)

REM -------- Frontend --------
netstat -ano | findstr ":5173" >nul
if errorlevel 1 (
    echo Frontend       : STOPPED
) else (
    echo Frontend       : RUNNING
)

echo.
echo ============================================
echo         HEALTH CHECK COMPLETE
echo ============================================
pause
goto menu


:push
cls
echo ============================================
echo               GIT PUSH
echo ============================================
echo.

set /p msg=Commit Message:

if "%msg%"=="" (
    echo.
    echo Commit message cannot be empty.
    pause
    goto menu
)

git add .

git diff --cached --quiet
if %errorlevel%==0 (
    echo.
    echo No changes to commit.
    pause
    goto menu
)

git commit -m "%msg%"
if errorlevel 1 (
    echo.
    echo Commit failed.
    pause
    goto menu
)

git push

if errorlevel 1 (
    echo.
    echo Push failed.
) else (
    echo.
    echo Git Push Successful.
)

pause
goto menu


:folder
explorer .
goto menu


:mysql
start cmd /k "mysql -u root -p"
goto menu