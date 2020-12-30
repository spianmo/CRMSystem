

-- -----------------------------------------------------
-- Schema opencrm
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `opencrm` DEFAULT CHARACTER SET utf8;
USE `opencrm`;

-- -----------------------------------------------------
-- Table `opencrm`.`t_employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencrm`.`t_employee`
(
    `employee_id`   INT         NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(45) NULL,
    `produce_type`  VARCHAR(45) NULL,
    `department_id` INT         NULL,
    `salary`        DECIMAL     NULL,
    PRIMARY KEY (`employee_id`),
    UNIQUE INDEX `employee_id_UNIQUE` (`employee_id` ASC) 
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `opencrm`.`t_task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencrm`.`t_task`
(
    `task_id`      INT  NOT NULL AUTO_INCREMENT,
    `task_time`    DATE NULL,
    `customer_num` INT  NULL,
    `employee_id`  INT  NOT NULL,
    PRIMARY KEY (`task_id`),
    UNIQUE INDEX `task_id_UNIQUE` (`task_id` ASC) ,
    INDEX `fk_t_task_t_employee1_idx` (`employee_id` ASC) ,
    CONSTRAINT `fk_t_task_t_employee1`
        FOREIGN KEY (`employee_id`)
            REFERENCES `opencrm`.`t_employee` (`employee_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `opencrm`.`t_customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencrm`.`t_customer`
(
    `customer_id` INT          NOT NULL,
    `name`        VARCHAR(45)  NULL,
    `address`     VARCHAR(100) NULL,
    `credit`      INT          NULL,
    `phone`       VARCHAR(20)  NULL,
    `employee_id` INT          NOT NULL,
    PRIMARY KEY (`customer_id`),
    INDEX `fk_t_customer_t_employee1_idx` (`employee_id` ASC) ,
    CONSTRAINT `fk_t_customer_t_employee1`
        FOREIGN KEY (`employee_id`)
            REFERENCES `opencrm`.`t_employee` (`employee_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `opencrm`.`t_produce`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencrm`.`t_produce`
(
    `produce_id`   INT         NOT NULL,
    `name`         VARCHAR(45) NULL,
    `price`        DECIMAL     NULL,
    `produce_date` DATE        NULL,
    `produce_type` VARCHAR(20) NULL,
    PRIMARY KEY (`produce_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `opencrm`.`t_trade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencrm`.`t_trade`
(
    `customer_id` INT     NOT NULL,
    `produce_id`  INT     NOT NULL,
    `trade_id`    INT     NOT NULL AUTO_INCREMENT,
    `employee_id` INT     NULL,
    `amount`      DECIMAL NULL,
    `produce_num` INT     NULL,
    PRIMARY KEY (`trade_id`),
    INDEX `fk_t_customer_has_t_produce_t_produce1_idx` (`produce_id` ASC) ,
    INDEX `fk_t_customer_has_t_produce_t_customer1_idx` (`customer_id` ASC) ,
    CONSTRAINT `fk_t_customer_has_t_produce_t_customer1`
        FOREIGN KEY (`customer_id`)
            REFERENCES `opencrm`.`t_customer` (`customer_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_t_customer_has_t_produce_t_produce1`
        FOREIGN KEY (`produce_id`)
            REFERENCES `opencrm`.`t_produce` (`produce_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `opencrm`.`t_feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opencrm`.`t_feedback`
(
    `produce_id`  INT          NOT NULL,
    `customer_id` INT          NOT NULL,
    `feedback_id` INT          NOT NULL AUTO_INCREMENT,
    `content`     VARCHAR(500) NULL,
    `create_time` DATE         NULL,
    `deal_status` INT          NULL,
    PRIMARY KEY (`feedback_id`),
    INDEX `fk_t_produce_has_t_customer_t_customer1_idx` (`customer_id` ASC) ,
    INDEX `fk_t_produce_has_t_customer_t_produce1_idx` (`produce_id` ASC) ,
    CONSTRAINT `fk_t_produce_has_t_customer_t_produce1`
        FOREIGN KEY (`produce_id`)
            REFERENCES `opencrm`.`t_produce` (`produce_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_t_produce_has_t_customer_t_customer1`
        FOREIGN KEY (`customer_id`)
            REFERENCES `opencrm`.`t_customer` (`customer_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

create table t_user
(
    id int auto_increment,
    username varchar(50) not null,
    password varchar(50) not null,
    email varchar(50) not null,
    account_level tinyint not null,
    constraint t_user_pk
        primary key (id)
)
    ENGINE = InnoDB;

INSERT INTO opencrm.t_customer (customer_id, name, address, credit, phone, employee_id) VALUES (1, '唐钱进', '江苏省南京市栖霞区羊山北路一号', 90, '13861948872', 1);
INSERT INTO opencrm.t_customer (customer_id, name, address, credit, phone, employee_id) VALUES (2, '余欣婷', '江苏省南京市栖霞区羊山北路一号', 90, '13861948872', 1);
INSERT INTO opencrm.t_customer (customer_id, name, address, credit, phone, employee_id) VALUES (3, '唐小余', '江苏省南通市如东县洋口镇', 80, '13861948872', 1);
INSERT INTO opencrm.t_customer (customer_id, name, address, credit, phone, employee_id) VALUES (4, '余小唐', '江西省抚州市临川区', 80, '13861948872', 1);