CREATE DATABASE  IF NOT EXISTS `banking` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `banking`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: banking
-- ------------------------------------------------------
-- Server version	5.7.28-log

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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `address_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `place_of_birth` varchar(90) DEFAULT NULL,
  `living_city` enum('MINSK','MOSCOW','BREST','VITEBSK','SPB','GRODNO') DEFAULT 'MINSK',
  `living_address` varchar(90) DEFAULT NULL,
  `registration_city` enum('MINSK','MOSCOW','BREST','VITEBSK','SPB','GRODNO') DEFAULT 'MINSK',
  `registration_address` varchar(90) DEFAULT NULL,
  `users_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`address_id`,`users_user_id`),
  KEY `fk_addresses_users1_idx` (`users_user_id`),
  CONSTRAINT `fk_addresses_users1` FOREIGN KEY (`users_user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (13,'asdasdasd','MOSCOW','asdasd 5.5','MINSK','asdasd 5.5',18),(14,'Belarus','MOSCOW','Аладовых 5','MINSK','Аладовых 5',19);
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_bill`
--

DROP TABLE IF EXISTS `bank_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` decimal(15,2) NOT NULL,
  `bill_number` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_bill`
--

LOCK TABLES `bank_bill` WRITE;
/*!40000 ALTER TABLE `bank_bill` DISABLE KEYS */;
INSERT INTO `bank_bill` VALUES (1,916000.00,3016594966941);
/*!40000 ALTER TABLE `bank_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_agreement`
--

DROP TABLE IF EXISTS `credit_agreement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credit_agreement` (
  `credit_agreement_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agreement_number` bigint(20) NOT NULL,
  `start_date` date DEFAULT NULL,
  `finish_date` date DEFAULT NULL,
  `term_contract` date DEFAULT NULL,
  `credit_amount` decimal(15,2) DEFAULT NULL,
  `credit_type` enum('PERPETUAL','LONG_TERM','SHORT_TERM','REVOCABLE','IRREVOCABLE') DEFAULT 'SHORT_TERM',
  `currency_type` enum('BYN','USD','EUR') DEFAULT NULL,
  `users_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`credit_agreement_id`,`agreement_number`),
  KEY `fk_credit_agreement_users1_idx` (`users_user_id`),
  CONSTRAINT `fk_credit_agreement_users1` FOREIGN KEY (`users_user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_agreement`
--

LOCK TABLES `credit_agreement` WRITE;
/*!40000 ALTER TABLE `credit_agreement` DISABLE KEYS */;
INSERT INTO `credit_agreement` VALUES (35,3014804013484,'2022-04-29','2023-04-29','2022-04-29',100000.00,'PERPETUAL','BYN',18);
/*!40000 ALTER TABLE `credit_agreement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_main_bill`
--

DROP TABLE IF EXISTS `credit_main_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credit_main_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` bigint(20) NOT NULL,
  `bill_amount` decimal(15,2) NOT NULL DEFAULT '0.00',
  `credit_agreement_id` bigint(20) NOT NULL,
  `credit_agreement_number` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`credit_agreement_id`,`credit_agreement_number`),
  KEY `fk_credit_main_bill_credit_agreement1_idx` (`credit_agreement_id`,`credit_agreement_number`),
  CONSTRAINT `fk_credit_main_bill_credit_agreement1` FOREIGN KEY (`credit_agreement_id`, `credit_agreement_number`) REFERENCES `credit_agreement` (`credit_agreement_id`, `agreement_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_main_bill`
--

LOCK TABLES `credit_main_bill` WRITE;
/*!40000 ALTER TABLE `credit_main_bill` DISABLE KEYS */;
INSERT INTO `credit_main_bill` VALUES (14,3014340978777,100000.00,35,3014804013484);
/*!40000 ALTER TABLE `credit_main_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_service_bill`
--

DROP TABLE IF EXISTS `credit_service_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credit_service_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` bigint(20) NOT NULL,
  `bill_amount` decimal(15,2) NOT NULL DEFAULT '0.00',
  `credit_agreement_id` bigint(20) NOT NULL,
  `credit_agreement_number` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`credit_agreement_id`,`credit_agreement_number`),
  KEY `fk_credit_service_bill_credit_agreement1_idx` (`credit_agreement_id`,`credit_agreement_number`),
  CONSTRAINT `fk_credit_service_bill_credit_agreement1` FOREIGN KEY (`credit_agreement_id`, `credit_agreement_number`) REFERENCES `credit_agreement` (`credit_agreement_id`, `agreement_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_service_bill`
--

LOCK TABLES `credit_service_bill` WRITE;
/*!40000 ALTER TABLE `credit_service_bill` DISABLE KEYS */;
INSERT INTO `credit_service_bill` VALUES (14,3014340978773,12000.00,35,3014804013484);
/*!40000 ALTER TABLE `credit_service_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deposit_agreement`
--

DROP TABLE IF EXISTS `deposit_agreement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deposit_agreement` (
  `deposit_agreement_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agreement_number` bigint(20) NOT NULL,
  `start_date` date DEFAULT NULL,
  `finish_date` date DEFAULT NULL,
  `term_contract` date DEFAULT NULL,
  `deposit_amount` decimal(15,2) DEFAULT NULL,
  `deposit_type` enum('PERPETUAL','LONG_TERM','SHORT_TERM','REVOCABLE','IRREVOCABLE') DEFAULT 'SHORT_TERM',
  `currency_type` enum('BYN','USD','EUR') DEFAULT NULL,
  `users_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`deposit_agreement_id`,`agreement_number`),
  KEY `fk_deposit_agreement_users1_idx` (`users_user_id`),
  CONSTRAINT `fk_deposit_agreement_users1` FOREIGN KEY (`users_user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deposit_agreement`
--

LOCK TABLES `deposit_agreement` WRITE;
/*!40000 ALTER TABLE `deposit_agreement` DISABLE KEYS */;
INSERT INTO `deposit_agreement` VALUES (27,3014226384147,'2022-04-25','2023-04-25','2022-04-25',500.00,'PERPETUAL','BYN',18),(28,3014437992466,'2022-04-25','2022-09-22','2022-04-25',1000.00,'PERPETUAL','BYN',19),(29,3014822226739,'2022-04-25','2022-09-22','2022-04-25',500.00,'PERPETUAL','BYN',18),(30,30141507626910,'2022-04-25','2023-04-25','2022-04-25',999.00,'PERPETUAL','BYN',19),(31,3014470907796,'2022-04-25','2022-10-25','2022-04-25',999.00,'PERPETUAL','BYN',18),(32,3014567524431,'2022-04-25','2023-04-25','2022-04-25',33.00,'PERPETUAL','BYN',18);
/*!40000 ALTER TABLE `deposit_agreement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_bill`
--

DROP TABLE IF EXISTS `main_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `main_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` bigint(20) NOT NULL,
  `bill_amount` decimal(15,2) NOT NULL,
  `deposit_agreement_id` bigint(20) NOT NULL,
  `deposit_agreement_number` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`deposit_agreement_id`,`deposit_agreement_number`),
  KEY `fk_main_bill_deposit_agreement1_idx` (`deposit_agreement_id`,`deposit_agreement_number`),
  CONSTRAINT `fk_main_bill_deposit_agreement1` FOREIGN KEY (`deposit_agreement_id`, `deposit_agreement_number`) REFERENCES `deposit_agreement` (`deposit_agreement_id`, `agreement_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_bill`
--

LOCK TABLES `main_bill` WRITE;
/*!40000 ALTER TABLE `main_bill` DISABLE KEYS */;
INSERT INTO `main_bill` VALUES (9,3014833370398,500.00,27,3014226384147),(10,3014383938000,1000.00,28,3014437992466),(11,3014506721398,500.00,29,3014822226739),(12,3014735880009,999.00,30,30141507626910),(13,3014547555402,999.00,31,3014470907796),(14,3014611233497,33.00,32,3014567524431);
/*!40000 ALTER TABLE `main_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passports`
--

DROP TABLE IF EXISTS `passports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passports` (
  `passport_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `passport_serial` varchar(45) DEFAULT NULL,
  `passport_number` int(11) DEFAULT NULL,
  `output_organisation` varchar(45) DEFAULT NULL,
  `output_date` date DEFAULT NULL,
  `identification_number` varchar(45) DEFAULT NULL,
  `users_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`passport_id`,`users_user_id`),
  KEY `fk_passports_users_idx` (`users_user_id`),
  CONSTRAINT `fk_passports_users` FOREIGN KEY (`users_user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passports`
--

LOCK TABLES `passports` WRITE;
/*!40000 ALTER TABLE `passports` DISABLE KEYS */;
INSERT INTO `passports` VALUES (19,'MP',1234567,'RUVD','2022-04-24','1234567A123AA2',18),(20,'MP',1234567,'RUVD','2022-04-24','1234567A123AA2',19);
/*!40000 ALTER TABLE `passports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_bill`
--

DROP TABLE IF EXISTS `service_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` bigint(20) NOT NULL,
  `bill_amount` decimal(15,2) NOT NULL DEFAULT '0.00',
  `deposit_agreement_id` bigint(20) NOT NULL,
  `deposit_agreement_number` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`deposit_agreement_id`,`deposit_agreement_number`),
  KEY `fk_service_bill_deposit_agreement1_idx` (`deposit_agreement_id`,`deposit_agreement_number`),
  CONSTRAINT `fk_service_bill_deposit_agreement1` FOREIGN KEY (`deposit_agreement_id`, `deposit_agreement_number`) REFERENCES `deposit_agreement` (`deposit_agreement_id`, `agreement_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_bill`
--

LOCK TABLES `service_bill` WRITE;
/*!40000 ALTER TABLE `service_bill` DISABLE KEYS */;
INSERT INTO `service_bill` VALUES (6,3014833370394,35.00,27,3014226384147),(7,3014383938008,28.77,28,3014437992466),(8,30145067213910,14.38,29,3014822226739),(9,3014735880000,69.93,30,30141507626910),(10,30145475554010,35.06,31,3014470907796),(11,3014611233494,2.31,32,3014567524431);
/*!40000 ALTER TABLE `service_bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_info`
--

DROP TABLE IF EXISTS `social_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `social_info` (
  `social_info_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `marital_status` enum('MARRIED','NOT_MARRIED','DIVORCED') DEFAULT NULL,
  `citizenship` enum('BELARUS','RUSSIA') DEFAULT NULL,
  `is_invalid` enum('NO','FIRST_GROUP','SECOND_GROUP','THIRD_GROUP') DEFAULT NULL,
  `is_pensiya` tinyint(1) DEFAULT NULL,
  `is_conscript` tinyint(1) DEFAULT NULL,
  `users_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`social_info_id`,`users_user_id`),
  KEY `fk_social_info_users1_idx` (`users_user_id`),
  CONSTRAINT `fk_social_info_users1` FOREIGN KEY (`users_user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_info`
--

LOCK TABLES `social_info` WRITE;
/*!40000 ALTER TABLE `social_info` DISABLE KEYS */;
INSERT INTO `social_info` VALUES (15,'MARRIED','BELARUS','NO',0,0,18),(16,'MARRIED','BELARUS','NO',0,0,19);
/*!40000 ALTER TABLE `social_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `third_name` varchar(45) NOT NULL,
  `date_of_birth` date NOT NULL,
  `sex` enum('MALE','FEMALE') NOT NULL DEFAULT 'MALE',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (18,'Антон','Лукьянов','Александрович','2022-04-24','MALE'),(19,'Ярослав','Петровский','Дмитриевич','2022-04-24','MALE');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-29 20:10:57
