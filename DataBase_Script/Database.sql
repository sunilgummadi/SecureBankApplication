CREATE TABLE `TransactionLog` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `debitedAmount` double DEFAULT NULL,
  `uid` bigint NOT NULL,
  `updateDateTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `User` (
  `uid` bigint NOT NULL AUTO_INCREMENT,
  `accountNo` bigint NOT NULL,
  `balance` double NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phoneno` bigint NOT NULL,
  `pincode` int NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;