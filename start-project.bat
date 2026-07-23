@echo off
title Smart Job Matching

echo =====================================
echo Killing old Backend and Frontend...
echo =====================================

for /f "tokens=5" %%a in ('netstat -ano ^| find ":8081"') do taskkill /F /PID %%a >nul 2>&1
for /f "tokens=5" %%a in ('netstat -ano ^| find ":5173"') do taskkill /F /PID %%a >nul 2>&1

timeout /t 2 >nul

cd /d D:\Smart-Job-Matching

echo Starting Backend...
start cmd /k "cd backend && mvn spring-boot:run"

timeout /t 8 >nul

echo Starting Frontend...
start cmd /k "cd frontend && npm run dev"

timeout /t 3 >nul

start http://localhost:5173

echo Project Started Successfully!
exit