-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: grievance
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `assignee`
--

DROP TABLE IF EXISTS `assignee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignee` (
  `assignee_id` int NOT NULL AUTO_INCREMENT,
  `assignee_no` varchar(15) DEFAULT NULL,
  `campus_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`assignee_id`),
  UNIQUE KEY `UK_il89sv0nh8te9egateff9yib8` (`assignee_no`),
  KEY `FK67476vfqt2oh9jei1uyxcsj7i` (`campus_id`),
  KEY `FKk302bwfxxslxcb74j3m184ejt` (`user_id`),
  CONSTRAINT `FK67476vfqt2oh9jei1uyxcsj7i` FOREIGN KEY (`campus_id`) REFERENCES `campus` (`campus_id`),
  CONSTRAINT `FKk302bwfxxslxcb74j3m184ejt` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `assignee_category`
--

DROP TABLE IF EXISTS `assignee_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignee_category` (
  `assignee_id` int NOT NULL,
  `assignee_categories_category_id` int NOT NULL,
  KEY `UK_l6dolnivkmr395519ieddw7c1` (`assignee_categories_category_id`),
  KEY `FKb16ui3mw2d03k5pe8p1r3euqq` (`assignee_id`),
  CONSTRAINT `FKb16ui3mw2d03k5pe8p1r3euqq` FOREIGN KEY (`assignee_id`) REFERENCES `assignee` (`assignee_id`),
  CONSTRAINT `FKn053p885gpdjpwrxd3uwitxhs` FOREIGN KEY (`assignee_categories_category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `campus`
--

DROP TABLE IF EXISTS `campus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campus` (
  `campus_id` int NOT NULL AUTO_INCREMENT,
  `campus_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`campus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_description` varchar(122) DEFAULT NULL,
  `category_type` varchar(32) DEFAULT NULL,
  `assignee_assignee_id` int DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `UK_7c3243soeeq0cgcerl2jqirit` (`category_type`),
  KEY `FK1pcce3hnblxetocexkdfvhuwm` (`assignee_assignee_id`),
  CONSTRAINT `FK1pcce3hnblxetocexkdfvhuwm` FOREIGN KEY (`assignee_assignee_id`) REFERENCES `assignee` (`assignee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grievance`
--

DROP TABLE IF EXISTS `grievance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grievance` (
  `grievance_id` int NOT NULL AUTO_INCREMENT,
  `attachment` longblob,
  `complaint_no` varchar(25) DEFAULT NULL,
  `description` varchar(122) DEFAULT NULL,
  `grievance_status` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `location_id` int DEFAULT NULL,
  PRIMARY KEY (`grievance_id`),
  UNIQUE KEY `UK_m1em875sqta2ux3undiej8m3m` (`complaint_no`),
  KEY `FK2m3ghglkev6qlrhq8fqn7xym0` (`user_id`),
  KEY `FKsxn8mido37icpvsf03tnhspqk` (`category_id`),
  KEY `FKapfkbd2bspoduisb0tuhjbxhw` (`location_id`),
  CONSTRAINT `FK2m3ghglkev6qlrhq8fqn7xym0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKapfkbd2bspoduisb0tuhjbxhw` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`),
  CONSTRAINT `FKsxn8mido37icpvsf03tnhspqk` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grievance_action`
--

DROP TABLE IF EXISTS `grievance_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grievance_action` (
  `action_id` int NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `grievance_status` int DEFAULT NULL,
  `remark` varchar(55) DEFAULT NULL,
  `assignee_id` int DEFAULT NULL,
  `assigner_id` int DEFAULT NULL,
  `grievance_id` int DEFAULT NULL,
  PRIMARY KEY (`action_id`),
  KEY `FKgcy0y4566m18mg0n7mxm1uu7b` (`assignee_id`),
  KEY `FKiixtxjuouvm41ga1afo7a9tko` (`assigner_id`),
  KEY `FKm9rvjyxftohwwdpcad1cw1ot9` (`grievance_id`),
  CONSTRAINT `FKgcy0y4566m18mg0n7mxm1uu7b` FOREIGN KEY (`assignee_id`) REFERENCES `assignee` (`assignee_id`),
  CONSTRAINT `FKiixtxjuouvm41ga1afo7a9tko` FOREIGN KEY (`assigner_id`) REFERENCES `assignee` (`assignee_id`),
  CONSTRAINT `FKm9rvjyxftohwwdpcad1cw1ot9` FOREIGN KEY (`grievance_id`) REFERENCES `grievance` (`grievance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `location_id` int NOT NULL AUTO_INCREMENT,
  `location_name` varchar(45) DEFAULT NULL,
  `campus_id` int DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  KEY `FKe1cvmgysl6hmqy5jvyaxy562` (`campus_id`),
  CONSTRAINT `FKe1cvmgysl6hmqy5jvyaxy562` FOREIGN KEY (`campus_id`) REFERENCES `campus` (`campus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(32) DEFAULT NULL,
  `last_name` varchar(32) DEFAULT NULL,
  `offical_email` varchar(32) DEFAULT NULL,
  `phone1` varchar(12) DEFAULT NULL,
  `phone2` varchar(12) DEFAULT NULL,
  `user_type` int DEFAULT NULL,
  `campus_id` int DEFAULT NULL,
  `login_id` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `official_email` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK4n8iy5figgcyabhj6s7xbdg0x` (`campus_id`),
  CONSTRAINT `FK4n8iy5figgcyabhj6s7xbdg0x` FOREIGN KEY (`campus_id`) REFERENCES `campus` (`campus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=434 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-24 12:37:30
