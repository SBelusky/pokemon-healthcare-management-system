CREATE DATABASE  IF NOT EXISTS `user` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `user`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: user
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `t_notifications`
--
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `t_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_notifications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `from_address` varchar(100) NOT NULL,
  `to_address` varchar(100) NOT NULL,
  `subject` varchar(300) NOT NULL,
  `body` text NOT NULL,
  `status` varchar(45) NOT NULL,
  `created_at` timestamp NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_notifications_t_users_idx` (`user_id`),
  CONSTRAINT `fk_t_notifications_t_users` FOREIGN KEY (`user_id`) REFERENCES `t_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_users`
--

DROP TABLE IF EXISTS `t_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(200) NOT NULL,
  `email` varchar(45) NOT NULL,
  `keycloak_id` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `keycloak_id_UNIQUE` (`keycloak_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_users`
--

LOCK TABLES `t_users` WRITE;
/*!40000 ALTER TABLE `t_users` DISABLE KEYS */;
INSERT INTO `t_users` VALUES (1,'aadmin','admin@phms.com','894c715b-f54c-4294-aa7b-9cf253f61802','Adam','Admin','admin','2025-02-13 15:30:45','2025-03-03 13:37:29'),(2,'sbelusky','samuel.belusky@gmail.com','5d0b47df-2cb1-4b45-bd8e-f43da3b198ce','Samuel','Beluský','trainer','2025-02-13 15:04:06','2025-03-03 13:37:29'),(3,'ddoctor','doctor@phms.com','0d3a4aab-1f40-4a72-828e-144b772c170e','Dr','Doctor','doctor','2025-02-13 15:04:06','2025-03-03 15:07:47');
/*!40000 ALTER TABLE `t_users` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `t_notifications` WRITE;
/*!40000 ALTER TABLE `t_notifications` DISABLE KEYS */;
INSERT INTO `t_notifications` VALUES (1,'info@phms.com','samuel.belusky@gmail.com','Ahoj','Úspešná registrácia','SENT','2025-02-13 15:30:45',2),(2,'info@phms.com','samuel.belusky@gmail.com','subject','body','SENT','2025-02-28 12:48:06',2);
/*!40000 ALTER TABLE `t_notifications` ENABLE KEYS */;
UNLOCK TABLES;

SET FOREIGN_KEY_CHECKS=1;

-- Dump completed on 2025-03-04 15:01:29
