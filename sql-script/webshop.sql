CREATE DATABASE  IF NOT EXISTS `webshop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `webshop`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: webshop
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Lace'),(2,'Super Fine'),(3,'Fine Weight'),(4,'Light Weight'),(5,'Medium Weight'),(6,'Bulky Weight'),(7,'Super Bulky'),(8,'Jumbo');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderline`
--

DROP TABLE IF EXISTS `orderline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderline` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `product_quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id_idx` (`order_id`),
  KEY `product_id_idx` (`product_id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderline`
--

LOCK TABLES `orderline` WRITE;
/*!40000 ALTER TABLE `orderline` DISABLE KEYS */;
INSERT INTO `orderline` VALUES (1,3,6,1),(2,3,1,1),(3,4,6,2),(4,6,6,1),(5,6,1,1),(6,6,3,1),(7,7,6,1);
/*!40000 ALTER TABLE `orderline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date` datetime NOT NULL,
  `total_amount` decimal(7,2) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'Received',
  PRIMARY KEY (`id`),
  KEY `customer_email_idx` (`customer_email`),
  KEY `order_status_idx` (`status`),
  CONSTRAINT `customer_email` FOREIGN KEY (`customer_email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'bella@test.com','2024-03-27 09:34:51',29.50,'Shipped'),(2,'bella@test.com','2024-03-27 09:35:03',29.50,'Shipped'),(3,'bella@test.com','2024-04-05 11:45:12',192.10,'Received'),(4,'bella@test.com','2024-04-05 12:16:50',313.80,'Received'),(5,'bella@test.com','2024-04-05 12:21:23',156.20,'Shipped'),(6,'bella@test.com','2024-04-05 12:26:17',203.77,'Received'),(7,'bella@test.com','2024-04-08 13:40:26',156.90,'Received');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` decimal(7,2) NOT NULL,
  `category_id` int NOT NULL,
  `description` mediumtext NOT NULL,
  `brand` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id_idx` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Catona',35.20,2,'2,5-3,5 mm. 50 g. gauche 26 st on 32 rows','Scheepjes'),(2,'Catania',29.50,2,'2,5-3,5. 50 g gauche 27 st on 30 rows','Schachenmayr'),(3,'Colour Crafter',23.67,4,'100 g, 4 mm hook size, gauch: 20 st x 24 rows','Scheepjes'),(4,'River Washed',34.99,3,'50 g, 3-3,5mm hook size, gaiuche: 24 st x 32 rows','Scheepjes'),(5,'Namaste',39.50,6,'100 g, 8 mm, gauche: 12 stx 13 rows','Scheepjes'),(6,'Whirl',156.90,3,'215g, 3 mm hook size, gauche: 25 st x 44 rows','Scheepjes'),(7,'Metropolis',51.49,2,'50g, 2,5-3 mm hook size, gauche: 29 st x 45 rows','Scheepjes'),(8,'Softfun',31.70,4,'50 g, 3,5-4mm hook size, gauche. 21 st x 30 rows','Scheepjes'),(11,'Maxi Sugar Rush',38.99,1,'50 g, 1,25-1,5 mm hook size, 25 st x33 rows','Scheepjes'),(12,'Chunky Monkey',45.55,5,'100 g, 5 mm hook size,  gauche: 13 st x 18 rows','Scheepjes'),(13,'Blanket Yarn',234.50,8,'300 g, 20mm hook size, gauche: 3 st x 6 rows','Hobbii'),(14,'Cosy',46.00,4,'50g, 4mm hook size, gauche: 24 st','Go Handmade'),(15,'Highland Wool',90.00,4,'50g, 3,5-4mm hook size, gauche: 22 st x 34 rows','Hobbii'),(16,'Portobello',59.70,5,'50g, 4mm hook size, gauche: 19stx 27 rows','Hobbii'),(17,'Tweed Delight',52.50,5,'50g, 5mm hook size, gauche: 17 st x 23 rows','Hobbii'),(18,'Lollipop',185.00,5,'200g, 5-6 mm hooksize, gauche: 12 st x 15 rows','Hobbii'),(19,'Molly Fine',99.00,6,'100g, 6-7 mm hook size, gauche: 14 st x18 rows','Mayflower'),(20,'alpaca Birs',98.90,6,'50 g, 66-7 mm hook size, gauche: 15 st','Viking of Norway'),(21,'Happy chunky Double',49.80,7,'50g, 6-10 mm hook size','Go Handmade'),(22,'Bungee Mini',148.90,7,'200g, 12 mm hook size, gauche: 7 st x 10 rows','Hobbii'),(23,'Friends Ribbon',150.60,7,'250g, 10 mm hook size, gauche: 7 st x9 rows','Hobbii'),(24,'Honey Bunny',226.80,8,'300 g, 10 mm hook size, gauche: 6 st x 10 rows','Hobbii'),(25,'Flora',45.60,2,'50 g, 3 mm hook size, gauche: 24 st x 32 rows','Drops'),(26,'Bead Deluxe',47.60,1,'50g, 1 mm hook size','Go Handmade'),(27,'Diablo',56.80,1,'50g, 4 mm hook size, gauche: 21 st x 28 rows','Hobbii'),(28,'Nord',56.90,2,'50 g, 3mm hook size, gauche: 24 st x 32 rows','Drops'),(29,'Belle',63.30,4,'50 g, 4 mm hook size, gauche: 21 st x 28 rows','Drops'),(30,'Lima',43.50,5,'50 g, 4 mm hook size, gauche: 21 st x 28 rows','Drops'),(31,'Air',56.70,5,'50 g, 5 mm hook size, gauche: 17 st x 22 rows','Drops'),(32,'Alpaca',34.50,3,'50 g, 3 mm hook size, gauche: 24 st x 32 rows','Drops');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_CUSTOMER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `firstname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `lastname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_ci NOT NULL,
  `password` varchar(68) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_as_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin@softyarn.com','Admin','Admin','$2a$10$7VSeIDMl/pZ1dJ9of7Vpdu.KsYT9ExIy.hTuHa37Wb.O4Hw0cVQc2'),('anna@test.com','Anna','Smith','$2a$10$4Wo58WYFw3zWGWXysegq7eVq6VKye4cGbIAyitRQzrkF7sWr8w9DG'),('bella@test.com','Bella','Ramsay','$2a$10$A6NLg4oxEsshIGXfjftfr.NzVJyMD0ObYl3u9grKF6L/QFCWm/ABG'),('bob@test.com','Bob','Smith','$2a$10$KKi9yHDCE61Sx/kIrdaft.pY47feEcd6G0ckrQ7gvfol9Mpw7sbOu'),('charlie@test.com','Charlie','Brown','$2a$10$A08Fnv4PAn2ghZZPt.d1hO8.mppchqUdMGX9a77.oOWGkZESgINhy'),('nadia@test.com','Nadia','Hadid','$2a$10$gcHC0bNLcrC6RueFnpcqguvKEk99.B2kIGpgzfp1ECopvlSdRCFJS');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_email`,`role_id`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_email` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('admin@softyarn.com',1),('anna@test.com',2),('bella@test.com',2),('bob@test.com',2),('charlie@test.com',2),('nadia@test.com',2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-12 10:52:05
