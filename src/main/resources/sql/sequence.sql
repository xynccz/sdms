--在MySQL中创建实现自增的序列（Sequence）
--第一步：创建--Sequence 管理表
DROP TABLE IF EXISTS sequence; 
CREATE TABLE sequence ( 
     name VARCHAR(50) NOT NULL, 
     current_value INT NOT NULL, 
     increment INT NOT NULL DEFAULT 1, 
     PRIMARY KEY (name) 
) ENGINE=InnoDB; 
 
--第二步：创建--取当前值的函数
DROP FUNCTION IF EXISTS currval; 
DELIMITER $ 
CREATE FUNCTION currval (seq_name VARCHAR(50)) 
     RETURNS INTEGER
     LANGUAGE SQL 
     DETERMINISTIC 
     CONTAINS SQL 
     SQL SECURITY DEFINER 
     COMMENT ''
BEGIN
     DECLARE value INTEGER; 
     SET value = 0; 
     SELECT current_value INTO value 
          FROM sequence
          WHERE name = seq_name; 
     RETURN value; 
END
$ 
DELIMITER ; 
 
--第三步：创建--取下一个值的函数
DROP FUNCTION IF EXISTS nextval; 
DELIMITER $ 
CREATE FUNCTION nextval (seq_name VARCHAR(50)) 
     RETURNS INTEGER
     LANGUAGE SQL 
     DETERMINISTIC 
     CONTAINS SQL 
     SQL SECURITY DEFINER 
     COMMENT ''
BEGIN
     UPDATE sequence
          SET current_value = current_value + increment 
          WHERE name = seq_name; 
     RETURN currval(seq_name); 
END
$ 
DELIMITER ; 
 
--第四步：创建--更新当前值的函数
DROP FUNCTION IF EXISTS setval; 
DELIMITER $ 
CREATE FUNCTION setval (seq_name VARCHAR(50), value INTEGER) 
     RETURNS INTEGER
     LANGUAGE SQL 
     DETERMINISTIC 
     CONTAINS SQL 
     SQL SECURITY DEFINER 
     COMMENT ''
BEGIN
     UPDATE sequence
          SET current_value = value 
          WHERE name = seq_name; 
     RETURN currval(seq_name); 
END
$ 
DELIMITER ; 
 
--第五步：测试函数功能,当上述四步完成后，可以用以下数据设置需要创建的sequence名称以及设置初始值和获取当前值和下一个值。
INSERT INTO sequence VALUES ('PoSequence', 0, 1);----添加一个sequence名称和初始值，以及自增幅度
SELECT SETVAL('PoSequence', 10);---设置指定sequence的初始值
SELECT CURRVAL('PoSequence');--查询指定sequence的当前值
SELECT NEXTVAL('PoSequence');--查询指定sequence的下一个值