CREATE DATABASE  IF NOT EXISTS `appointment` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `appointment`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: appointment
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
-- Table structure for table `t_appointments`
--
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `t_appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_appointments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `appointment_datetime` datetime NOT NULL,
  `status` varchar(45) NOT NULL,
  `pokemon_id` int NOT NULL,
  `doctor_id` int NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_pokemon_id` (`pokemon_id`) /*!80000 INVISIBLE */,
  KEY `idx_doctor_id` (`doctor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_appointments`
--

LOCK TABLES `t_appointments` WRITE;
/*!40000 ALTER TABLE `t_appointments` DISABLE KEYS */;
INSERT INTO `t_appointments` VALUES (1,'2025-02-13 15:30:45','IN_PROGRESS',2,2,'2025-02-13 15:30:45',NULL),(2,'2025-02-28 15:59:34','CANCELLED',2,1,'2025-02-28 17:01:15','2025-02-28 17:04:51'),(3,'2025-03-02 15:53:30','SCHEDULED',2,2,'2025-03-02 16:53:50',NULL),(4,'2025-03-02 15:50:30','SCHEDULED',2,2,'2025-03-02 16:55:30',NULL),(5,'2025-03-02 15:54:30','SCHEDULED',2,2,'2025-03-02 17:04:04',NULL);
/*!40000 ALTER TABLE `t_appointments` ENABLE KEYS */;
UNLOCK TABLES;

SET FOREIGN_KEY_CHECKS=1;
-- Dump completed on 2025-03-04 15:18:45
