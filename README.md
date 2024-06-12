## MAF
this app is made to challang the user to calculate simple all to way to hard calculations and share your result with others trough a leaderboard


to use database for users is required and can be created like this : 
"
create database UserDB; use UserDB;

create table Users(
id int primary key auto_increment, 
Username varchar(100),
Password varchar(100),
Level int,
EXperience int,
Easy int,
Normal int,
Hard int,
Impossible int,
Levels int
);

" 
and to use a databse change the "src/dbconfig.properties" file to correspond to your databse, username and password
-- made by Petr Taller 2024
