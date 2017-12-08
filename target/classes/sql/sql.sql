---Êý¾Ý±í
1¡¢tbl_emp
CREATE TABLE `NewTable` (
`emp_id`  int(11) NOT NULL AUTO_INCREMENT ,
`emp_name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL ,
`gender`  char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`email`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`d_id`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`emp_id`),
FOREIGN KEY (`d_id`) REFERENCES `tnl_dept` (`dept_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `fk_emp_dept` (`d_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
AUTO_INCREMENT=1009
ROW_FORMAT=COMPACT
;

2¡¢tnl_dept
CREATE TABLE `NewTable` (
`dept_id`  int(11) NOT NULL AUTO_INCREMENT ,
`dept_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`dept_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3
ROW_FORMAT=COMPACT
;

