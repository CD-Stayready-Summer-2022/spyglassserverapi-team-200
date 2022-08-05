create database `spyglass`;

use `spyglass`;

CREATE TABLE  IF NOT EXISTS`users`(
	`id` INTEGER NOT NULL auto_increment PRIMARY KEY,
    `first_name` VARCHAR(255),
    `last_name` VARCHAR(255),
    `email` VARCHAR(255),
    `dob` DATE
);

CREATE TABLE  IF NOT EXISTS `goal`(
	`id` Integer Primary Key auto_increment,
    `title` varchar(255),
    `description` varchar(255),
    `goal_start` DATE,
    `target_date` DATE,
    `target_amount` DOUBLE,
    `current_amount` DOUBLE,
    completion_status ENUM('COMPLETE', 'IN_PROGRESS', 'NOT_STARTED')
);

