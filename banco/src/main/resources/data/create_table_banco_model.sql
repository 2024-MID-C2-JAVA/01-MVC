CREATE TABLE IF NOT EXISTS bank (
  id INT NOT NULL AUTO_INCREMENT COMMENT 'This field represents the unique, auto-incrementing object identifier.',
  code VARCHAR(128) NOT NULL COMMENT 'This field represents the random autogenerated unique object identifier in the UUID format.',
  name VARCHAR(512) NOT NULL COMMENT 'This field represents the name of the object.',
  address VARCHAR(512) NOT NULL COMMENT 'This field represents the address of the object.',
  phone VARCHAR(128) NOT NULL COMMENT 'This field represents the number phone of the object.',
  balance DECIMAL(60,30) NOT NULL DEFAULT 0 COMMENT 'This field represents the balance of the object.',
  is_active TINYINT(1) UNSIGNED NULL DEFAULT 1 COMMENT 'This field is to activate or deactivate the object (0 is inactive and 1 is active)',
  creator_user_id  VARCHAR(128) NOT NULL COMMENT 'This field represents the user who create the object.',
  created_at TIMESTAMP NOT NULL COMMENT 'This field represents the date the object was create.',
  updater_user_id VARCHAR(128) NULL COMMENT 'This field represents the user who update the object.',
  updated_at TIMESTAMP NULL COMMENT 'This field represents the date the object was last updated.',
  PRIMARY KEY(id),
  CONSTRAINT name UNIQUE (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS client (
  id INT NOT NULL AUTO_INCREMENT COMMENT 'This field represents the unique, auto-incrementing object identifier.',
  code VARCHAR(128) NOT NULL COMMENT 'This field represents the random autogenerated unique object identifier in the UUID format.',
  name VARCHAR(512) NOT NULL COMMENT 'This field represents the name of the object.',
  usuario VARCHAR(512) NOT NULL COMMENT 'This field represents the name of the object.',
  password VARCHAR(512) NOT NULL COMMENT 'This field represents the name of the object.',
  address VARCHAR(512) NOT NULL COMMENT 'This field represents the address of the object.',
  phone VARCHAR(128) NOT NULL COMMENT 'This field represents the number phone of the object.',
  bank_id INT NOT NULL,
  is_active TINYINT(1) UNSIGNED NULL DEFAULT 1 COMMENT 'This field is to activate or deactivate the object (0 is inactive and 1 is active)',
  creator_user_id  VARCHAR(128) NOT NULL COMMENT 'This field represents the user who create the object.',
  created_at TIMESTAMP NOT NULL COMMENT 'This field represents the date the object was create.',
  updater_user_id VARCHAR(128) NULL COMMENT 'This field represents the user who update the object.',
  updated_at TIMESTAMP NULL COMMENT 'This field represents the date the object was last updated.',
  PRIMARY KEY(id),
  CONSTRAINT name UNIQUE (name),
  FOREIGN KEY(bank_id)
        REFERENCES bank(id)
          ON DELETE NO ACTION
          ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS account (
  id INT NOT NULL AUTO_INCREMENT COMMENT 'This field represents the unique, auto-incrementing object identifier.',
  code VARCHAR(128) NOT NULL COMMENT 'This field represents the random autogenerated unique object identifier in the UUID format.',
  number VARCHAR(128) NOT NULL COMMENT 'This field represents the random autogenerated unique object identifier in the UUID format.',
  balance DECIMAL(60,30) NOT NULL DEFAULT 0 COMMENT 'This field represents the balance of the object.',
  pin VARCHAR(128) NOT NULL COMMENT 'This field represents the pin of the object.',
  client_id INT NOT NULL,
  is_active TINYINT(1) UNSIGNED NULL DEFAULT 1 COMMENT 'This field is to activate or deactivate the object (0 is inactive and 1 is active)',
  creator_user_id  VARCHAR(128) NOT NULL COMMENT 'This field represents the user who create the object.',
  created_at TIMESTAMP NOT NULL COMMENT 'This field represents the date the object was create.',
  updater_user_id VARCHAR(128) NULL COMMENT 'This field represents the user who update the object.',
  updated_at TIMESTAMP NULL COMMENT 'This field represents the date the object was last updated.',
  PRIMARY KEY(id),
  CONSTRAINT number UNIQUE (number),
  FOREIGN KEY(client_id)
      REFERENCES client(id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS type_transaction (
  id INT NOT NULL AUTO_INCREMENT COMMENT 'This field represents the unique, auto-incrementing object identifier.',
  name VARCHAR(128) NOT NULL COMMENT 'This field represents the name.',
  description VARCHAR(512) NOT NULL COMMENT 'This field represents the name of the object.',
  PRIMARY KEY(id),
  CONSTRAINT name UNIQUE (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS bank_transaction (
  id INT NOT NULL AUTO_INCREMENT COMMENT 'This field represents the unique, auto-incrementing object identifier.',
  origin_account_id INT NOT NULL,
  destination_account_id INT NOT NULL,
  amount DECIMAL(60,30) NOT NULL COMMENT 'This field represents the balance of the object.',
  type_transaction_id INT NOT NULL,
  created_at TIMESTAMP NOT NULL COMMENT 'This field represents the date the object was create.',
  PRIMARY KEY(id),
  FOREIGN KEY(origin_account_id)
      REFERENCES client(id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
  FOREIGN KEY(destination_account_id)
        REFERENCES client(id)
          ON DELETE NO ACTION
          ON UPDATE NO ACTION,
  FOREIGN KEY(type_transaction_id)
          REFERENCES type_transaction(id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;