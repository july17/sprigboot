CREATE SCHEMA IF NOT EXISTS StudentSpring DEFAULT CHARACTER SET utf8 ;
USE StudentSpring;


CREATE TABLE IF NOT EXISTS Discipline
(
  discipline_id BIGINT NOT NULL AUTO_INCREMENT,
  discipline_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (discipline_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS StudentGroups
(
  group_id  BIGINT NOT NULL AUTO_INCREMENT,
  group_name VARCHAR(25) NOT NULL,
  PRIMARY KEY (group_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET utf8 ;

CREATE TABLE  Student
(
  student_id BIGINT NOT NULL AUTO_INCREMENT,
  Surname VARCHAR(25) NOT NULL,
  Name VARCHAR(25) NOT NULL,
  City VARCHAR(25) NULL,
  Email VARCHAR(45) NULL,
  group_id BIGINT NOT NULL,
  PRIMARY KEY (student_id),
  CONSTRAINT fk_student_studentgroups
    foreign key (group_id)
    references studentspring.studentgroups (group_id)
    on delete no action
    on update no action
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARACTER SET utf8 ;



CREATE TABLE  student_discipline
(
  discipline_id BIGINT not NULL,
  student_id BIGINT not NULL,
  PRIMARY KEY (discipline_id, student_id),
  CONSTRAINT studentdiscipline_idfk_1
  FOREIGN KEY (discipline_id)
    REFERENCES  studentspring.discipline (discipline_id),
  CONSTRAINT  studentdiscipline_idfk_2
  FOREIGN KEY (student_id)
    REFERENCES  studentspring.student (student_id)
) ENGINE = InnoDB
DEFAULT CHARACTER SET utf8 ;


CREATE TABLE IF NOT EXISTS logger (
  logger_id BIGINT NOT NULL AUTO_INCREMENT,
  student VARCHAR(50) NOT NULL,
  discipline VARCHAR(90) NOT NULL,
  action VARCHAR(10) NOT NULL,
  time_stamp DATETIME NOT NULL,
  user VARCHAR(50) NULL,
  PRIMARY KEY (logger_id)
) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET utf8 ;