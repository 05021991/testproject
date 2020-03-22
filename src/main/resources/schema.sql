DROP TABLE IF EXISTS NEWS;

CREATE TABLE NEWS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(500) NOT NULL,
  link VARCHAR(250) NOT NULL,
  description VARCHAR(2000) DEFAULT NULL,
  pub_date DATE NOT NULL
);