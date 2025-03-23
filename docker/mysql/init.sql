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