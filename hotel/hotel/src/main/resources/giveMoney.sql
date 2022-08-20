drop procedure if exists giveMoney;
DELIMITER $$
CREATE
    DEFINER = `root`@`localhost` PROCEDURE giveMoney(IN id int, In end_date DATETIME)
BEGIN

END $$
DELIMITER ;

call giveMoney(9, NOW())
