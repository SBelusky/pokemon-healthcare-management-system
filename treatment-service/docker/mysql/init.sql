CREATE DATABASE  IF NOT EXISTS `treatment` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `treatment`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: treatment
-- ------------------------------------------------------
-- Server version	8.0.19

SET FOREIGN_KEY_CHECKS=0;

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
-- Table structure for table `t_medications`
--

DROP TABLE IF EXISTS `t_medications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_medications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` text NOT NULL,
  `dosage` varchar(100) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `currency` varchar(45) NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_medications`
--

LOCK TABLES `t_medications` WRITE;
/*!40000 ALTER TABLE `t_medications` DISABLE KEYS */;
INSERT INTO `t_medications` VALUES (1,'Potion','Heals 20 HP','50ml',100,'EUR','2025-02-12 14:56:30','2025-02-12 14:56:30'),(2,'Super Potion','Heals 50 HP','100ml',250,'EUR','2025-02-12 14:56:30','2025-02-12 14:56:30'),(3,'Hyper Potion','Heals 200 HP','200ml',1200,'EUR','2025-02-12 14:56:30','2025-02-12 14:56:30'),(4,'Antidote','Cures Poison','30ml',50,'EUR','2025-02-12 14:56:30','2025-02-12 14:56:30'),(5,'Burn Heal','Cures Burn','30ml',100,'EUR','2025-02-12 14:56:30','2025-02-12 14:56:30'),(6,'Ice Heal','Cures Freeze','30ml',100,'EUR','2025-02-12 14:56:30','2025-02-12 14:56:30'),(7,'Awakening','Cures Sleep','30ml',100,'EUR','2025-02-12 14:56:30','2025-02-12 14:56:30'),(8,'Paralyze Heal','Cures Paralysis','30ml',100,'EUR','2025-02-12 14:56:30','2025-02-12 14:56:30'),(9,'Full Restore','Fully restores HP & cures status','500ml',3000,'EUR','2025-02-12 14:56:30','2025-02-12 14:56:30'),(10,'Revive','Revives a fainted Pokémon with half HP','1 unit',1500,'EUR','2025-02-12 14:56:30','2025-02-12 14:56:30');
/*!40000 ALTER TABLE `t_medications` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `t_medications_BEFORE_UPDATE` BEFORE UPDATE ON `t_medications` FOR EACH ROW BEGIN
	IF NEW.updated_at IS NULL THEN
        SET NEW.updated_at = CURRENT_TIMESTAMP;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `t_prescriptions`
--

DROP TABLE IF EXISTS `t_prescriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_prescriptions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `doctor_id` int NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `treatment_history_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_prescriptions_t_treatment_history1_idx` (`treatment_history_id`),
  KEY `idx_doctor_id` (`doctor_id`),
  CONSTRAINT `fk_t_prescriptions_t_treatment_history1` FOREIGN KEY (`treatment_history_id`) REFERENCES `t_treatment_history` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_prescriptions`
--

LOCK TABLES `t_prescriptions` WRITE;
/*!40000 ALTER TABLE `t_prescriptions` DISABLE KEYS */;
INSERT INTO `t_prescriptions` VALUES (1,'Painkillers for recovery',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',1),(2,'Antibiotics for infection',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',1),(3,'Insulin for diabetes',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',1),(4,'Antihistamines for allergies',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',1),(5,'Steroids for inflammation',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',1),(6,'Vitamins for deficiency',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',1),(7,'Antidepressants for mental health',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',2),(8,'Blood thinners for circulation',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',2),(9,'Pain relief patches',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',2),(10,'Cough syrup for respiratory issues',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',2),(12,'string',2,'2025-03-01 22:45:47',NULL,1),(13,'string',2,'2025-03-01 22:57:58',NULL,1);
/*!40000 ALTER TABLE `t_prescriptions` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `t_prescriptions_has_medications`
--

DROP TABLE IF EXISTS `t_prescriptions_has_medications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_prescriptions_has_medications` (
  `prescription_id` int NOT NULL,
  `medication_id` int NOT NULL,
  PRIMARY KEY (`medication_id`,`prescription_id`),
  KEY `fk_t_prescriptions_has_t_medications_t_medications1_idx` (`medication_id`),
  KEY `fk_t_prescriptions_has_t_medications_t_prescriptions_idx` (`prescription_id`),
  CONSTRAINT `fk_t_prescriptions_has_t_medications_t_medications1` FOREIGN KEY (`medication_id`) REFERENCES `t_medications` (`id`),
  CONSTRAINT `fk_t_prescriptions_has_t_medications_t_prescriptions` FOREIGN KEY (`prescription_id`) REFERENCES `t_prescriptions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_prescriptions_has_medications`
--

LOCK TABLES `t_prescriptions_has_medications` WRITE;
/*!40000 ALTER TABLE `t_prescriptions_has_medications` DISABLE KEYS */;
INSERT INTO `t_prescriptions_has_medications` VALUES (1,1),(13,1),(1,2),(13,2),(1,3),(1,4),(1,5),(2,6),(2,7),(2,8),(2,9),(2,10);
/*!40000 ALTER TABLE `t_prescriptions_has_medications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_surgeries`
--

DROP TABLE IF EXISTS `t_surgeries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_surgeries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `surgery_date` timestamp NOT NULL,
  `doctor_id` int NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `treatment_history_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_surgeries_t_treatment_history1_idx` (`treatment_history_id`),
  KEY `idx_doctor_id` (`doctor_id`),
  CONSTRAINT `fk_t_surgeries_t_treatment_history1` FOREIGN KEY (`treatment_history_id`) REFERENCES `t_treatment_history` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_surgeries`
--

LOCK TABLES `t_surgeries` WRITE;
/*!40000 ALTER TABLE `t_surgeries` DISABLE KEYS */;
INSERT INTO `t_surgeries` VALUES (1,'Appendix removal','2025-02-12 10:00:00',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',1),(2,'Bone fracture repair','2025-02-13 11:00:00',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',1),(3,'Heart surgery','2025-02-14 09:00:00',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',1),(4,'Liver transplant','2025-02-15 08:00:00',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',1),(5,'Brain surgery','2025-02-16 07:00:00',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',1),(6,'Eye surgery','2025-02-17 06:00:00',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',1),(7,'Kidney transplant','2025-02-18 05:00:00',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',2),(8,'Lung surgery','2025-02-19 04:00:00',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',2),(9,'Spinal cord surgery','2025-02-20 03:00:00',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',2),(10,'Gastrointestinal surgery','2025-02-21 02:00:00',2,'2025-02-12 14:56:30','2025-02-12 14:56:30',2);
/*!40000 ALTER TABLE `t_surgeries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_treatment_history`
--

DROP TABLE IF EXISTS `t_treatment_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_treatment_history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pokemon_id` int NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_pokemon_id` (`pokemon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_treatment_history`
--

LOCK TABLES `t_treatment_history` WRITE;
/*!40000 ALTER TABLE `t_treatment_history` DISABLE KEYS */;
INSERT INTO `t_treatment_history` VALUES (1,2,'2025-02-12 14:56:30',NULL),(2,3,'2025-02-12 14:56:30',NULL);
/*!40000 ALTER TABLE `t_treatment_history` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `t_vaccination_plans`
--

DROP TABLE IF EXISTS `t_vaccination_plans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_vaccination_plans` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `vaccination_date` timestamp NOT NULL,
  `doctor_id` int NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `treatment_history_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_vaccination_plans_t_treatment_history1_idx` (`treatment_history_id`),
  KEY `idx_doctor_id` (`doctor_id`),
  CONSTRAINT `fk_t_vaccination_plans_t_treatment_history1` FOREIGN KEY (`treatment_history_id`) REFERENCES `t_treatment_history` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_vaccination_plans`
--

LOCK TABLES `t_vaccination_plans` WRITE;
/*!40000 ALTER TABLE `t_vaccination_plans` DISABLE KEYS */;
INSERT INTO `t_vaccination_plans` VALUES (1,'Type A','Basic vaccination for fire-type Pokémon','2025-03-01 10:00:00',2,'2025-02-20 14:00:00','2025-02-21 14:00:00',1),(2,'Type B','Water-type Pokémon vaccination','2025-03-05 11:30:00',2,'2025-02-20 14:30:00','2025-02-21 14:30:00',1),(3,'Type C','Electric-type immunity boost','2025-03-10 09:15:00',2,'2025-02-20 15:00:00','2025-02-21 15:00:00',1),(4,'Type D','Grass-type Pokémon seasonal vaccine','2025-03-15 12:00:00',2,'2025-02-20 15:30:00','2025-02-21 15:30:00',1),(5,'Type E','Rock-type Pokémon protective vaccine','2025-03-20 14:45:00',2,'2025-02-20 16:00:00','2025-02-21 16:00:00',1),(6,'Type F','Poison-type Pokémon immunity booster','2025-03-25 10:30:00',2,'2025-02-20 16:30:00','2025-02-21 16:30:00',1),(7,'Type G','Flying-type Pokémon disease prevention','2025-03-30 13:15:00',2,'2025-02-20 17:00:00','2025-02-21 17:00:00',2),(8,'Type H','Dark-type Pokémon health support','2025-04-05 08:45:00',2,'2025-02-20 17:30:00','2025-02-21 17:30:00',2),(9,'Type I','Psychic-type Pokémon mind enhancement','2025-04-10 11:00:00',2,'2025-02-20 18:00:00','2025-02-21 18:00:00',2),(10,'Type J','Ghost-type Pokémon spiritual protection','2025-04-15 09:30:00',2,'2025-02-20 18:30:00','2025-02-21 18:30:00',2);
/*!40000 ALTER TABLE `t_vaccination_plans` ENABLE KEYS */;
UNLOCK TABLES;

SET FOREIGN_KEY_CHECKS=1;

-- Dump completed on 2025-03-04 15:06:24
