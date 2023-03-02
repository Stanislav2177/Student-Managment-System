-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: student_info
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `faculty_number` int NOT NULL,
  `sender` varchar(255) NOT NULL,
  `message` text NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `faculty_number` int NOT NULL,
  `EGN` varchar(10) DEFAULT NULL,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `semester` int NOT NULL,
  `faculty` varchar(70) NOT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`faculty_number`),
  UNIQUE KEY `faculty_number` (`faculty_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (10011010,'0022456419','NovoIme','bezPrezime',7,'faculty','2002-10-10 00:00:00'),(401221001,'411223242','Hristo','Hristov',2,'faculty','2021-09-10 00:00:00'),(401221002,'411223242','Hristian','Hristov',2,'faculty','2021-09-10 00:00:00'),(501221001,'0234325310','Aleksei','Yankov',2,'FPMI','2021-09-27 00:00:00'),(501221002,'0012245310','Stanislav','Yankov',4,'FKST','2021-09-27 00:00:00'),(501221004,'0320405044','Spas','Min',4,'FSI','2022-09-27 00:00:00'),(501221022,'0124354294','Martin','Topch',2,'FSC','2020-09-27 00:00:00'),(501221043,'246405060','Stancho','Yan',4,'FKST','2020-09-27 00:00:00'),(503221015,'0123442940','Vitomir','Moll',6,'FKT','2020-09-27 00:00:00'),(503223101,'100120243','Jani','Vaklinov',4,'FKST','2022-09-27 00:00:00'),(503223102,'100120245','Anton','Malinov',4,'FKST','2022-09-27 00:00:00'),(503223103,'100120534','Mitko','Malinov',4,'FKST','2022-09-27 00:00:00'),(503223104,'100120221','Mitko','Naidenov',4,'FKST','2022-09-27 00:00:00'),(503223105,'100120201','Blagoi','Naidenov',4,'FKST','2022-09-27 00:00:00'),(503223106,'100124322','Blagoi','Naidenov',4,'FKST','2022-09-27 00:00:00'),(503223107,'100124322','Simeon','Spasov',4,'FKST','2022-09-27 00:00:00'),(503223108,'100124324','Todor','Spasov',4,'FKST','2022-09-27 00:00:00'),(503223109,'100124324','Kristian','Mladenov',4,'FKST','2022-09-27 00:00:00'),(503223111,'100124324','Miroslav','Mladenov',4,'FKST','2022-09-27 00:00:00'),(503223112,'100125004','Miroslav','Kirilov',4,'FKST','2022-09-27 00:00:00'),(504423301,'245344332','Hristo','Minkov',4,'FKST','2021-09-11 00:00:00');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-02 23:28:44
