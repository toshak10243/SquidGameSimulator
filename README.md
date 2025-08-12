Squid Game Simulator – Java Project

Introduction

This is a Java-based Squid Game Simulator that brings the thrill of the popular Squid Game series into an interactive desktop application.
The project allows an admin to manage players, run game simulations, track player progress, update prize pools, and reset the game.
It combines Java Swing for the UI, MySQL for database storage, and additional features like sound effects and background images for an immersive experience.

Features

Admin Login System – Secure login to access the game controls.

Add and Manage Players – Add new players with name and age, view the complete list of players.

Game Simulation – Simulate each Squid Game level with elimination logic.

Dynamic Stats – View players left, eliminated players, and the current prize pool in real time.

Prize Pool Management – Automatic updates to the prize pool after each level.

Reset Option – Restart the game from level 1 with all players active.

Themed UI – Dark, red, and black Squid Game theme with mask man background image.

Sound Effects – Background music plays when the admin logs in.


Technologies Used

Java (Core + Swing for GUI)

MySQL Database

JDBC (MySQL Connector)

Object-Oriented Programming (OOP) concepts

Audio (WAV playback in Java)

Project Structure

db/ – Database connection file

entity/ – Entity classes for Player and Game

dao/ – Data Access Objects to handle database operations

logic/ – GameManager class containing main simulation logic

service/ – PrizePool and MusicPlayer services

gui/ – All Java Swing UI classes

resources/ – Images and sound files used in the project


How to Run

Install Java JDK and MySQL on your system.

Clone or download this repository to your local system.

Import the squidgame.sql file into MySQL to set up the database.

Update the database credentials in DBConnection.java.


Compile the project in CMD:

javac -d . db\DBConnection.java entity\Player.java entity\Game.java dao\PlayerDAO.java dao\GameDAO.java logic\GameManager.java service\PrizePool.java service\MusicPlayer.java 
gui\AdminLoginUI.java gui\MainMenu.java gui\PlayerFormUI.java gui\PlayerTableUI.java gui\GameSimulationUI.java Main.java

Run The Project in CMD:

java -cp ".;lib\mysql-connector-j-8.0.33.jar" Main


Login with:

Username: toshak

Password: 1234

Author
Toshak Sharma
MCA in Artificial Intelligence & Machine Learning – JECRC University
GitHub: toshak10243

THANKYOU !


![ss](https://github.com/user-attachments/assets/5aeb0143-d0cb-4571-87e5-dc65d0590728)

