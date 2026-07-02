-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: student_management
-- ------------------------------------------------------
-- Server version	8.4.8

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
-- Table structure for table `class_scheduling`
--

DROP TABLE IF EXISTS `class_scheduling`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_scheduling` (
  `scheduling_id` int NOT NULL AUTO_INCREMENT,
  `course_id` int DEFAULT NULL,
  `subject_id` int DEFAULT NULL,
  `lecture_id` int DEFAULT NULL,
  `class_date` date NOT NULL,
  `class_time` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`scheduling_id`),
  KEY `course_id` (`course_id`),
  KEY `subject_id` (`subject_id`),
  KEY `lecture_id` (`lecture_id`),
  CONSTRAINT `class_scheduling_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course_management` (`course_id`),
  CONSTRAINT `class_scheduling_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`subject_id`),
  CONSTRAINT `class_scheduling_ibfk_3` FOREIGN KEY (`lecture_id`) REFERENCES `lecture_management` (`lecture_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_scheduling`
--

LOCK TABLES `class_scheduling` WRITE;
/*!40000 ALTER TABLE `class_scheduling` DISABLE KEYS */;
INSERT INTO `class_scheduling` VALUES (1,2,2,1,'2026-07-08','9.00-11.00'),(2,2,2,1,'2026-06-04','9.00-11.00'),(3,3,1,2,'2026-06-04','11.00-13.00'),(4,1,1,1,'2026-06-03','9.00-11.00'),(5,2,1,2,'2026-07-15','11.00-13.00');
/*!40000 ALTER TABLE `class_scheduling` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_management`
--

DROP TABLE IF EXISTS `course_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_management` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(50) NOT NULL,
  `duration` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_management`
--

LOCK TABLES `course_management` WRITE;
/*!40000 ALTER TABLE `course_management` DISABLE KEYS */;
INSERT INTO `course_management` VALUES (1,'sudo code','12 months'),(2,'php','6moths'),(3,'photoshop','2 months');
/*!40000 ALTER TABLE `course_management` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture_management`
--

DROP TABLE IF EXISTS `lecture_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture_management` (
  `lecture_id` int NOT NULL AUTO_INCREMENT,
  `lecture_name` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone_number` char(10) DEFAULT NULL,
  `subject_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`lecture_id`),
  KEY `subject_id` (`subject_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `lecture_management_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`subject_id`),
  CONSTRAINT `lecture_management_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture_management`
--

LOCK TABLES `lecture_management` WRITE;
/*!40000 ALTER TABLE `lecture_management` DISABLE KEYS */;
INSERT INTO `lecture_management` VALUES (1,'senuth','senuth@gmail.com','0777954451',1,6),(2,'sugath priyantha','sugath@gmail.com','0745478216',2,7),(3,'Dinush','dinush678@gmail.com','9876543210',1,7);
/*!40000 ALTER TABLE `lecture_management` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `positions`
--

DROP TABLE IF EXISTS `positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `positions` (
  `position_id` int NOT NULL AUTO_INCREMENT,
  `position_name` enum('admin','lecture') NOT NULL,
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `positions`
--

LOCK TABLES `positions` WRITE;
/*!40000 ALTER TABLE `positions` DISABLE KEYS */;
INSERT INTO `positions` VALUES (1,'admin'),(2,'lecture');
/*!40000 ALTER TABLE `positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_attendance`
--

DROP TABLE IF EXISTS `student_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_attendance` (
  `attendance_id` int NOT NULL AUTO_INCREMENT,
  `scheduling_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `status` enum('present','absent') NOT NULL,
  `attendance_date` date DEFAULT (curdate()),
  PRIMARY KEY (`attendance_id`),
  KEY `scheduling_id` (`scheduling_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `student_attendance_ibfk_1` FOREIGN KEY (`scheduling_id`) REFERENCES `class_scheduling` (`scheduling_id`),
  CONSTRAINT `student_attendance_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student_management` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_attendance`
--

LOCK TABLES `student_attendance` WRITE;
/*!40000 ALTER TABLE `student_attendance` DISABLE KEYS */;
INSERT INTO `student_attendance` VALUES (7,1,1,'present','2026-06-30'),(8,1,4,'present','2026-06-30'),(9,1,1,'present','2026-06-30'),(10,1,4,'absent','2026-06-30'),(11,3,5,'present','2026-07-02');
/*!40000 ALTER TABLE `student_attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_management`
--

DROP TABLE IF EXISTS `student_management`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_management` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `student_name` varchar(50) NOT NULL,
  `telephone_number` char(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `student_management_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course_management` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_management`
--

LOCK TABLES `student_management` WRITE;
/*!40000 ALTER TABLE `student_management` DISABLE KEYS */;
INSERT INTO `student_management` VALUES (1,'sineth kavind','0123467899','sineth@gmail.com',1),(2,'kamal fernando','0773087662','kamal@gmail.com',1),(3,'JESON kalhara','4567891235','jason@gmail.com',2),(4,'sumudiii','1475452145','saath@gmail.com',1),(5,'Dinush','0123456789','dinush123@gmail.com',3),(6,'swera','1451547854','suman@gmail.com',1);
/*!40000 ALTER TABLE `student_management` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjects` (
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `subjects_name` varchar(50) NOT NULL,
  `course_id` int DEFAULT NULL,
  PRIMARY KEY (`subject_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `subjects_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course_management` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` VALUES (1,'maths',1),(2,'sinhala',2);
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `position_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  KEY `position_id` (`position_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`position_id`) REFERENCES `positions` (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (5,'1','123',1),(6,'sineth','11234',2),(7,'dinush','sineth2006',2),(8,'sinethk','saman',1);
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

-- Dump completed on 2026-07-02 15:05:45
