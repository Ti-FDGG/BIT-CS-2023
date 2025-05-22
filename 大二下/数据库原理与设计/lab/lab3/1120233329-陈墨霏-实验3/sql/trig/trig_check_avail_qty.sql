-- trig_check_avail_qty.sql

DELIMITER $$

CREATE TRIGGER trig_check_avail_qty
BEFORE UPDATE ON lineitem
FOR EACH ROW
BEGIN
  -- 仅在 quantity 发生变更时检查
  DECLARE available_qty INT;

  IF NEW.quantity <> OLD.quantity THEN
    -- 查找可用库存数量
    SELECT ps.avail_qty 
    INTO available_qty
    FROM partsupp ps
    WHERE ps.part_id = NEW.part_id 
      AND ps.supplier_id = NEW.supplier_id;

    -- 判断库存是否足够
    IF available_qty < NEW.quantity THEN
      SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Insufficient available quantity for the specified part.';
    END IF;
  END IF;
END$$

DELIMITER ;