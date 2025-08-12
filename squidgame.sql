-- File: squidgame.sql

CREATE DATABASE IF NOT EXISTS squidgame;
USE squidgame;

CREATE TABLE players (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    status VARCHAR(20) DEFAULT 'Active',
    current_game_level INT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
