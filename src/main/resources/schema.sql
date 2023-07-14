DROP TABLE IF EXISTS t_customer;
CREATE TABLE t_customer (
  id INT PRIMARY KEY AUTO_INCREMENT,
  login_name VARCHAR(150) NOT NULL,
  nick_name VARCHAR(150) NOT NULL,
  passwd VARCHAR(150) NOT NULL,
  salt VARCHAR(20) NOT NULL,
  points INT,
  create_time TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS t_transaction;
CREATE TABLE t_transaction (
  id INT PRIMARY KEY AUTO_INCREMENT,
  customer_id INT,
  amount INT,
  points INT,
  transaction_time DATETIME,
  create_time TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS t_rewards;
CREATE TABLE t_rewards (
  id INT PRIMARY KEY AUTO_INCREMENT,
  customer_id INT,
  reward_year INT,
  reward_month INT,
  points INT,
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
