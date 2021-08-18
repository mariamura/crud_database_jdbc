create database crud_db;

create table team (
    teamId 	            int(10) not null AUTO_INCREMENT,
    teamName    	    varchar(100),
    status              varchar(100),

    primary key (teamId)
    );


create table developer (
    developerId         int(10) not null AUTO_INCREMENT,
    fisrtName    	    int(1),
    lastName   	 		varchar(100),
    teamId				int(10) not null,
    primary key (developerId)
    );


create table skill (
    skillId             int(10) not null AUTO_INCREMENT,
    skillName    	    varchar(100),
    primary key (skillId)
);
