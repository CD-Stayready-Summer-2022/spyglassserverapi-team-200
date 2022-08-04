create database `spyglass`;

use `spyglass-api`;

CREATE TABLE  IF NOT EXISTS`users`(
	`id` INTEGER NOT NULL auto_increment PRIMARY KEY,
    `first_name` VARCHAR(255),
    `last_name` VARCHAR(255),
    `email` VARCHAR(255),
    `dob` DATE
);

create table `goals`(
	`id` Integer Primary Key auto_increment,
    `title` varchar(50),
    `last_name` varchar(50),
    `email` varchar(50),
    `age` Integer,
    `status` ENUM('COMPLETE', 'IN_PROGRESS', 'NOT_STARTED')
);