drop table if exists fn_task;
drop table if exists fn_user;

create table fn_task (
	id bigint auto_increment,
	title varchar(128) not null,
	description varchar(255),
	user_id bigint not null,
    primary key (id)
) engine=InnoDB;

create table fn_user (
	id bigint auto_increment,
	login_name varchar(64) not null unique,
	name varchar(64) not null,
	password varchar(255) not null,
	salt varchar(64) not null,
	roles varchar(255) not null,
	register_date timestamp not null default 0,
	primary key (id)
) engine=InnoDB;

DROP TABLE IF EXISTS fn_art_classify;
CREATE TABLE `fn_art_classify`( 
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `fid` BIGINT,
  `updateTime` TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8;

DROP TABLE IF EXISTS fn_article;
CREATE TABLE `fn_article`(  
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `cid` BIGINT,
  `keyword` VARCHAR(255),
  `descri` VARCHAR(255),
  `centent` TEXT,
  `pic` VARCHAR(255),
  `author` BIGINT,
  `addTime` TIMESTAMP,
  `updateTime` TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8;

