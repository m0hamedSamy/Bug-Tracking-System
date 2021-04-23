create database BugSystem 

use BugSystem 



create table bug (
bugName nvarchar(200) primary key,
bugType varchar(50),
bugStatues varchar(10) not null ,
bugPriority int ,
bugLevel int ,
projectName varchar(100),
bugDate varchar(130),
screenShot varchar(100) default null,
developer nvarchar(100) default null,
tester nvarchar(100) 
)

create table users (
username varchar(50),
email varchar(100) primary key,
userpassword varchar(50),
code int not null,
)