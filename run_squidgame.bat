@echo off
title Squid Game Simulator
echo Compiling Java files...

javac -d . ^
db\DBConnection.java ^
entity\Player.java ^
entity\Game.java ^
dao\PlayerDAO.java ^
dao\GameDAO.java ^
logic\GameManager.java ^
service\PrizePool.java ^
service\MusicPlayer.java ^
gui\AdminLoginUI.java ^
gui\MainMenu.java ^
gui\PlayerFormUI.java ^
gui\PlayerTableUI.java ^
gui\GameSimulationUI.java ^
Main.java

if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
    exit /b
)

echo.
echo Starting Squid Game Simulator...
java -cp ".;lib\mysql-connector-j-8.0.33.jar" Main

pause
