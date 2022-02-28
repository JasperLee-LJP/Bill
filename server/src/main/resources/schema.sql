CREATE TABLE IF NOT EXISTS `user` (
    `id` INT UNSIGNED AUTO_INCREMENT COMMENT '主键ID',
    `username` VARCHAR(20) NOT NULL COMMENT '姓名',
    `password` VARCHAR(90) NOT NULL COMMENT '密码',
    `gender` TINYINT UNSIGNED DEFAULT NULL COMMENT '性别',
    `birthdate` DATETIME DEFAULT NULL COMMENT '出生日期',
    `mobile` VARCHAR(20) DEFAULT NULL COMMENT  '手机号码',
    `email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `last_login_time` DATETIME DEFAULT NULL COMMENT '最后登录时间',
    `status` TINYINT UNSIGNED DEFAULT NULL COMMENT '账号状态',
    PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `fund_source`(
    `id` INT UNSIGNED AUTO_INCREMENT COMMENT '主键ID',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `source` VARCHAR(30) NOT NULL COMMENT  '来源名称',
    `color` VARCHAR(30) DEFAULT NULL COMMENT '颜色',
    PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `fund`(
    `id` INT UNSIGNED AUTO_INCREMENT COMMENT '主键ID',
    `source_id` INT UNSIGNED NOT NULL COMMENT  '来源ID',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `transaction_time` DATETIME DEFAULT NULL COMMENT '交易时间',
    `amount` INT NOT NULL COMMENT '金额',
    `principal` INT NOT NULL COMMENT '本金',
    `interest` INT NOT NULL COMMENT '利息',
    PRIMARY KEY(`id`),
    KEY(`source_id`),
    CONSTRAINT `fund_ibfk_1` FOREIGN KEY(`source_id`) REFERENCES `fund_source`(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;