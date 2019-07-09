use craftbeer;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(128) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `public_id` VARCHAR(256) NOT NULL,
  `role` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO user(user_name,password,role)
VALUES ('thongnb','$2a$10$9U7LBeinjB/cuYDmF9ydkuT2sA4Wl7VjabNBleuv11iRDZZSLXSGG', 'ROLE_ADMIN');

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `beer`;
CREATE TABLE `beer` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(512) NOT NULL,
  `manufacturer` VARCHAR(128),
  `country` VARCHAR(128),
  `description` VARCHAR(1028),
  `price` DOUBLE DEFAULT 0,
  `status` VARCHAR(128),
  `category_id` INT(11) UNSIGNED,
  PRIMARY KEY (`id`),
  
  KEY `category_id` (`category_id`),
  CONSTRAINT `beer_ibfk_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

use craftbeer;
DROP TABLE IF EXISTS `user_beer`;
CREATE TABLE `user_beer` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) UNSIGNED,
  `beer_id` INT(11) UNSIGNED,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `ub_ibfk_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  KEY `beer_id` (`beer_id`),
  CONSTRAINT `ub_ibfk_beer` FOREIGN KEY (`beer_id`) REFERENCES `beer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;