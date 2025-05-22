-- trig_update_orders_total_amount.sql

DELIMITER $$

CREATE TRIGGER trig_update_orders_total_amount
AFTER INSERT ON lineitem
FOR EACH ROW
BEGIN
  DECLARE v_price DECIMAL(10,2);
  DECLARE v_exists INT;

  -- 判断 part 是否存在
  SELECT COUNT(*) INTO v_exists
  FROM part
  WHERE part_id = NEW.part_id;

  -- 如果 part 不存在，则抛出异常
  IF v_exists = 0 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Invalid part_id: part does not exist';
  END IF;

  -- 获取零售价
  SELECT retail_price INTO v_price
  FROM part
  WHERE part_id = NEW.part_id;

  -- 判断 orders 是否存在
  SELECT COUNT(*) INTO v_exists
  FROM orders
  WHERE order_id = NEW.order_id;

  IF v_exists = 0 THEN
    -- 如果订单不存在，则插入新订单记录（初始 total_amount = 0）
    INSERT INTO orders(order_id, total_amount)
    VALUES (NEW.order_id, 0);
  END IF;

  -- 更新 total_amount
  UPDATE orders
  SET total_amount = total_amount + (v_price * NEW.quantity * NEW.discount)
  WHERE order_id = NEW.order_id;
END$$

DELIMITER ;