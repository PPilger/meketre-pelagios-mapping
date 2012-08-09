CREATE DATABASE  IF NOT EXISTS `meketre_application` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `meketre_application`;
-- MySQL dump 10.13  Distrib 5.5.24, for Linux (x86_64)
--
-- Host: mysql.mminf.univie.ac.at    Database: meketre_application
-- ------------------------------------------------------
-- Server version	5.1.12-beta-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Fragment`
--

DROP TABLE IF EXISTS `Fragment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Fragment` (
  `id` bigint(20) NOT NULL,
  `createdBy` varchar(255) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `lastModifiedBy` varchar(255) DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `mainMediaObject_id` bigint(20) DEFAULT NULL,
  `date_id` bigint(20) DEFAULT NULL,
  `necropolis_id` bigint(20) DEFAULT NULL,
  `proprietor_id` bigint(20) DEFAULT NULL,
  `currentLocation_id` bigint(20) DEFAULT NULL,
  `origin_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8D469B461712A839a17d4670` (`proprietor_id`),
  KEY `FKA17D4670888A2663` (`origin_id`),
  KEY `FKA17D4670EF096C38` (`currentLocation_id`),
  KEY `FK8D469B461B84D7F9a17d4670` (`necropolis_id`),
  KEY `FKDEC20408351BCBD48d469b46a17d4670` (`mainMediaObject_id`),
  CONSTRAINT `FK8D469B461712A839a17d4670` FOREIGN KEY (`proprietor_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FK8D469B461B84D7F9a17d4670` FOREIGN KEY (`necropolis_id`) REFERENCES `Term` (`id`),
  CONSTRAINT `FKA17D4670888A2663` FOREIGN KEY (`origin_id`) REFERENCES `Tomb` (`id`),
  CONSTRAINT `FKA17D4670EF096C38` FOREIGN KEY (`currentLocation_id`) REFERENCES `Displaced` (`id`),
  CONSTRAINT `FKDEC20408351BCBD48d469b46a17d4670` FOREIGN KEY (`mainMediaObject_id`) REFERENCES `MediaObject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fragment`
--

LOCK TABLES `Fragment` WRITE;
/*!40000 ALTER TABLE `Fragment` DISABLE KEYS */;
INSERT INTO `Fragment` VALUES (524290,'lubiz','2011-07-13 07:48:44','lubiz','2011-07-13 07:54:13',481,360448,16105,16021,1,NULL),(524292,'lubiz','2011-07-13 09:56:25','lubiz','2011-07-13 11:26:37',NULL,360449,16096,16022,2,NULL),(524294,'lubiz','2011-07-13 11:19:27','lubiz','2011-10-21 15:03:48',NULL,393234,16105,16025,3,NULL),(524296,'lubiz','2011-07-13 12:38:56','lubiz','2011-10-21 15:06:52',NULL,393237,16105,15643,NULL,NULL),(524298,'lubiz','2011-07-13 13:31:11',NULL,NULL,NULL,360450,16099,16028,4,NULL),(524300,'lubiz','2011-07-13 14:46:27','lubiz','2011-07-13 14:46:46',NULL,360451,16099,15643,5,NULL),(524302,'lubiz','2011-07-13 15:06:50','lubiz','2011-07-13 15:22:16',NULL,360452,16100,16034,6,NULL),(655367,'lubiz','2011-12-13 16:24:42','lubiz','2011-12-13 16:30:50',NULL,NULL,NULL,NULL,32768,360449);
/*!40000 ALTER TABLE `Fragment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-07-09 17:03:57
