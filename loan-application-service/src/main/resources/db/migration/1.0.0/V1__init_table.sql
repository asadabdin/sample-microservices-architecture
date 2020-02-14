-- Enums
CREATE TYPE LoanStatus AS ENUM (
  'CREATED',
  'DENIED',
  'APPLIED'
  );

CREATE TABLE IF NOT EXISTS `loan`
(
  `id`          INTEGER    NOT NULL PRIMARY KEY,
  `amount`      double     not null,
  `duration`    INTEGER    not null,
  `status`      LoanStatus not null,
  `customer_id` INTEGER    not null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
