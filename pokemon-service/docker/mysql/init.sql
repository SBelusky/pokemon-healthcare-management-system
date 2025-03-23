CREATE DATABASE  IF NOT EXISTS `pokemon` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pokemon`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pokemon
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
-- Table structure for table `t_abilities`
--
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `t_abilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_abilities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_abilities`
--

LOCK TABLES `t_abilities` WRITE;
/*!40000 ALTER TABLE `t_abilities` DISABLE KEYS */;
INSERT INTO `t_abilities` VALUES (1,'Overgrow','2025-03-03 14:01:40','2025-03-03 14:01:40'),(2,'Blaze','2025-03-03 14:01:40','2025-03-03 14:01:40'),(3,'Torrent','2025-03-03 14:01:40','2025-03-03 14:01:40'),(4,'Static','2025-03-03 14:01:40','2025-03-03 14:01:40'),(5,'Cute Charm','2025-03-03 14:01:40','2025-03-03 14:01:40'),(6,'Pickup','2025-03-03 14:01:40','2025-03-03 14:01:40'),(7,'Damp','2025-03-03 14:01:40','2025-03-03 14:01:40'),(8,'Guts','2025-03-03 14:01:40','2025-03-03 14:01:40'),(9,'Levitate','2025-03-03 14:01:40','2025-03-03 14:01:40'),(10,'Thick Fat','2025-03-03 14:01:40','2025-03-03 14:01:40');
/*!40000 ALTER TABLE `t_abilities` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `t_abilities_BEFORE_UPDATE` BEFORE UPDATE ON `t_abilities` FOR EACH ROW BEGIN
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
-- Table structure for table `t_pokemons`
--

DROP TABLE IF EXISTS `t_pokemons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_pokemons` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `birth_date` date NOT NULL,
  `owner_id` int NOT NULL,
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `t_species_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_t_pokemons_t_species1_idx` (`t_species_id`),
  CONSTRAINT `fk_t_pokemons_t_species1` FOREIGN KEY (`t_species_id`) REFERENCES `t_species` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_pokemons`
--

LOCK TABLES `t_pokemons` WRITE;
/*!40000 ALTER TABLE `t_pokemons` DISABLE KEYS */;
INSERT INTO `t_pokemons` VALUES (1,'Bulby','2023-01-01',1,'2025-03-03 14:04:56','2025-03-03 14:04:56',1),(2,'Charry','2023-02-01',2,'2025-03-03 14:04:56','2025-03-03 14:04:56',2),(3,'Squirty','2023-03-01',3,'2025-03-03 14:04:56','2025-03-03 14:04:56',3),(4,'Pika','2023-04-01',4,'2025-03-03 14:04:56','2025-03-03 14:04:56',4),(5,'Jiggly','2023-05-01',5,'2025-03-03 14:04:56','2025-03-03 14:04:56',5),(6,'Meow','2023-06-01',6,'2025-03-03 14:04:56','2025-03-03 14:04:56',6),(7,'Psy','2023-07-01',7,'2025-03-03 14:04:56','2025-03-03 14:04:56',7),(8,'Machy','2023-08-01',8,'2025-03-03 14:04:56','2025-03-03 14:04:56',8),(9,'Ghast','2023-09-01',9,'2025-03-03 14:04:56','2025-03-03 14:04:56',9),(10,'Snory','2023-10-01',10,'2025-03-03 14:04:56','2025-03-03 14:04:56',10);
/*!40000 ALTER TABLE `t_pokemons` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `t_pokemons_BEFORE_UPDATE` BEFORE UPDATE ON `t_pokemons` FOR EACH ROW BEGIN
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
-- Table structure for table `t_pokemons_has_abilities`
--

DROP TABLE IF EXISTS `t_pokemons_has_abilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_pokemons_has_abilities` (
  `t_pokemon_id` int NOT NULL,
  `t_ability_id` int NOT NULL,
  PRIMARY KEY (`t_pokemon_id`,`t_ability_id`),
  KEY `fk_t_pokemons_has_t_abilities_t_abilities1_idx` (`t_ability_id`),
  KEY `fk_t_pokemons_has_t_abilities_t_pokemons_idx` (`t_pokemon_id`),
  CONSTRAINT `fk_t_pokemons_has_t_abilities_t_abilities1` FOREIGN KEY (`t_ability_id`) REFERENCES `t_abilities` (`id`),
  CONSTRAINT `fk_t_pokemons_has_t_abilities_t_pokemons` FOREIGN KEY (`t_pokemon_id`) REFERENCES `t_pokemons` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_pokemons_has_abilities`
--

LOCK TABLES `t_pokemons_has_abilities` WRITE;
/*!40000 ALTER TABLE `t_pokemons_has_abilities` DISABLE KEYS */;
INSERT INTO `t_pokemons_has_abilities` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10);
/*!40000 ALTER TABLE `t_pokemons_has_abilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_species`
--

DROP TABLE IF EXISTS `t_species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_species` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `weight` varchar(45) DEFAULT NULL,
  `photo_image` varchar(200) DEFAULT NULL,
  `base_happiness` varchar(45) DEFAULT NULL,
  `capture_rate` varchar(45) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  `habitat` varchar(45) DEFAULT NULL,
  `is_mythical` tinyint DEFAULT NULL,
  `shape` varchar(45) DEFAULT NULL,
  `growth_rate` varchar(45) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_species`
--

LOCK TABLES `t_species` WRITE;
/*!40000 ALTER TABLE `t_species` DISABLE KEYS */;
INSERT INTO `t_species` VALUES (1,'Bulbasaur','Grass/Poison','6.9','bulbasaur.png','70','45','Green','Grassland',0,'Quadruped','Medium Slow','2025-03-03 14:04:35','2025-03-03 14:04:35'),(2,'Charmander','Fire','8.5','charmander.png','50','45','Red','Mountain',0,'Bipedal','Medium Slow','2025-03-03 14:04:35','2025-03-03 14:04:35'),(3,'Squirtle','Water','9.0','squirtle.png','50','45','Blue','Waters Edge',0,'Bipedal','Medium Slow','2025-03-03 14:04:35','2025-03-03 14:04:35'),(4,'Pikachu','Electric','6.0','pikachu.png','50','190','Yellow','Forest',0,'Quadruped','Medium Fast','2025-03-03 14:04:35','2025-03-03 14:04:35'),(5,'Jigglypuff','Normal/Fairy','5.5','jigglypuff.png','50','170','Pink','Grassland',0,'Ball','Fast','2025-03-03 14:04:35','2025-03-03 14:04:35'),(6,'Meowth','Normal','4.2','meowth.png','50','255','Brown','Urban',0,'Quadruped','Medium Fast','2025-03-03 14:04:35','2025-03-03 14:04:35'),(7,'Psyduck','Water','19.6','psyduck.png','50','190','Yellow','Waters Edge',0,'Bipedal','Medium Fast','2025-03-03 14:04:35','2025-03-03 14:04:35'),(8,'Machop','Fighting','19.5','machop.png','50','180','Gray','Mountain',0,'Bipedal','Medium Slow','2025-03-03 14:04:35','2025-03-03 14:04:35'),(9,'Gastly','Ghost/Poison','0.1','gastly.png','50','190','Purple','Cave',0,'Amorphous','Fast','2025-03-03 14:04:35','2025-03-03 14:04:35'),(10,'Snorlax','Normal','460.0','snorlax.png','50','25','Blue','Mountain',0,'Bipedal','Slow','2025-03-03 14:04:35','2025-03-03 14:04:35');
/*!40000 ALTER TABLE `t_species` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`%`*/ /*!50003 TRIGGER `t_species_BEFORE_UPDATE` BEFORE UPDATE ON `t_species` FOR EACH ROW BEGIN
	IF NEW.updated_at IS NULL THEN
        SET NEW.updated_at = CURRENT_TIMESTAMP;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

SET FOREIGN_KEY_CHECKS=1;
-- Dump completed on 2025-03-04 15:17:11
