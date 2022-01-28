CREATE TABLE IF NOT EXISTS `user` (
    `id` INT UNSIGNED AUTO_INCREMENT COMMENT '主键ID',
    `user_name` VARCHAR(20) NOT NULL COMMENT '姓名',
    `password` VARCHAR(30) NOT NULL COMMENT '密码',
    `gender` TINYINT UNSIGNED DEFAULT NULL COMMENT '性别',
    `birthdate` DATE DEFAULT NULL COMMENT '出生日期',
    `mobile` VARCHAR(20) DEFAULT NULL COMMENT  '手机号码',
    `email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;