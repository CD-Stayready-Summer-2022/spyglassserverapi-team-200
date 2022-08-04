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
    `description` varchar(100),
    `goal_start` DATE,
    `target_date` DATE,
    `target_amount` DOUBLE,
    `current_amount` DOUBLE,
    `completion_Status` ENUM('COMPLETE', 'IN_PROGRESS', 'NOT_STARTED'),
    `owner` varchar(50)
);