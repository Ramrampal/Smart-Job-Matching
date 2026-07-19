
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `users` WRITE;

INSERT INTO `users` VALUES (2,'Beta@gmail.com',' sohit Beta','$2a$10$//hmP.ig0WDI8A6VoiQFVu9GeAj.6vgBn6HAGgE60eIn3DWd.hdrO','STUDENT'),(3,'murgi@gmail.com',' Nikita ','$2a$10$odl3ZGSHoRPnbMVICc0NOeWD6pwLp9CQ9Sp4/o9rRl/k.IaaoUbVu','STUDENT');
UNLOCK TABLES;
