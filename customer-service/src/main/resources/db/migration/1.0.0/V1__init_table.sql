CREATE TABLE IF NOT EXISTS `customer`
(
  `id`         INTEGER NOT NULL PRIMARY KEY,
  `first_name` VARCHAR not null,
  `last_name`  varchar not null,
  `email`      varchar not null,
  `phone`      varchar not null,
  `user_id`    varchar not null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
