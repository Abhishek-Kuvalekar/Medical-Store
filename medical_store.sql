-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: medical_store
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.18.04.1

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
-- Current Database: `medical_store`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `medical_store` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `medical_store`;

--
-- Table structure for table `attendance_details`
--

DROP TABLE IF EXISTS `attendance_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendance_details` (
  `emp_id` int(11) NOT NULL,
  `day` date NOT NULL,
  `attendance` enum('F','T') DEFAULT NULL,
  PRIMARY KEY (`emp_id`,`day`),
  CONSTRAINT `attendance_details_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance_details`
--

LOCK TABLES `attendance_details` WRITE;
/*!40000 ALTER TABLE `attendance_details` DISABLE KEYS */;
INSERT INTO `attendance_details` VALUES (1,'2018-10-02','T');
/*!40000 ALTER TABLE `attendance_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `defect`
--

DROP TABLE IF EXISTS `defect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `defect` (
  `defect_id` int(11) NOT NULL AUTO_INCREMENT,
  `defect_description` text,
  PRIMARY KEY (`defect_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `defect`
--

LOCK TABLES `defect` WRITE;
/*!40000 ALTER TABLE `defect` DISABLE KEYS */;
/*!40000 ALTER TABLE `defect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(50) NOT NULL,
  `emp_role_id` int(11) DEFAULT NULL,
  `emp_addr` varchar(255) DEFAULT NULL,
  `emp_dob` date DEFAULT NULL,
  `emp_email` varchar(255) DEFAULT NULL,
  `emp_mobile_num` varchar(11) DEFAULT NULL,
  `emp_alternate_num` varchar(11) DEFAULT NULL,
  `emp_salary` decimal(12,2) DEFAULT NULL,
  `emp_joining_date` date DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `emp_role_id` (`emp_role_id`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`emp_role_id`) REFERENCES `role` (`role_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'employee1',2,'address1','1997-04-29','email1','9876543210','98765433210',12000.00,'2018-10-02');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `login_role_id` int(11) NOT NULL,
  `login_username` varchar(20) DEFAULT NULL,
  `login_password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`login_role_id`),
  UNIQUE KEY `login_username` (`login_username`),
  CONSTRAINT `login_ibfk_1` FOREIGN KEY (`login_role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'owner','ownerpass'),(2,'employee','employeepass');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine` (
  `medc_id` varchar(20) NOT NULL,
  `medc_name` varchar(50) NOT NULL,
  `medc_supplier_id` int(11) DEFAULT NULL,
  `medc_type_id` int(11) DEFAULT NULL,
  `medc_per_strip` decimal(10,2) DEFAULT NULL,
  `medc_cost_per_strip` decimal(10,2) DEFAULT NULL,
  `medc_description` text,
  `shelf_no` varchar(10) DEFAULT NULL,
  `medc_power` int(11) NOT NULL,
  `medc_quantity_in_tablets` int(11) NOT NULL,
  PRIMARY KEY (`medc_id`),
  KEY `medc_supplier_id` (`medc_supplier_id`),
  KEY `medc_type_id` (`medc_type_id`),
  CONSTRAINT `medicine_ibfk_1` FOREIGN KEY (`medc_supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE CASCADE,
  CONSTRAINT `medicine_ibfk_2` FOREIGN KEY (`medc_type_id`) REFERENCES `medicine_type` (`type_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES ('medc_id1','med1',1,1,10.00,100.00,'Description1 med1','shelf1',250,60),('medc_id2','med2',2,2,10.00,340.00,'Description2 Med2','shelf2',500,195);
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine_type`
--

DROP TABLE IF EXISTS `medicine_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) NOT NULL,
  `type_description` text,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine_type`
--

LOCK TABLES `medicine_type` WRITE;
/*!40000 ALTER TABLE `medicine_type` DISABLE KEYS */;
INSERT INTO `medicine_type` VALUES (1,'Type1','Description1'),(2,'Type2','Description2'),(3,'Type3','Description3'),(4,'Type4','Description4'),(5,'Type5','Description5');
/*!40000 ALTER TABLE `medicine_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'owner','Owner login'),(2,'employee','Employee login');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales` (
  `bill_no` int(11) NOT NULL AUTO_INCREMENT,
  `sale_timestamp` datetime NOT NULL,
  `medc_id` varchar(20) NOT NULL,
  `sold_quantity` int(11) DEFAULT NULL,
  `sale_total_amount` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`bill_no`,`sale_timestamp`,`medc_id`),
  KEY `medc_id` (`medc_id`),
  CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`medc_id`) REFERENCES `medicine` (`medc_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (1,'2018-10-02 18:22:43','medc_id1',10,100.00);
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_defect`
--

DROP TABLE IF EXISTS `stock_defect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_defect` (
  `medc_id` varchar(20) NOT NULL,
  `supplier_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `defect_id` int(11) NOT NULL,
  `defect_description_in_detail` text,
  `return_date` date NOT NULL,
  `amount_received_back` decimal(12,2) DEFAULT NULL,
  `amount_pending` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`medc_id`,`defect_id`,`return_date`),
  KEY `defect_id` (`defect_id`),
  KEY `supplier_id` (`supplier_id`),
  CONSTRAINT `stock_defect_ibfk_1` FOREIGN KEY (`medc_id`) REFERENCES `stock_details` (`medc_id`) ON DELETE CASCADE,
  CONSTRAINT `stock_defect_ibfk_2` FOREIGN KEY (`defect_id`) REFERENCES `defect` (`defect_id`) ON DELETE CASCADE,
  CONSTRAINT `stock_defect_ibfk_3` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_defect`
--

LOCK TABLES `stock_defect` WRITE;
/*!40000 ALTER TABLE `stock_defect` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock_defect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_details`
--

DROP TABLE IF EXISTS `stock_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_details` (
  `medc_id` varchar(20) NOT NULL,
  `supplier_id` int(11) NOT NULL,
  `purchase_date_timestamp` datetime NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total_cost_of_purchase` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`medc_id`,`supplier_id`,`purchase_date_timestamp`),
  KEY `supplier_id` (`supplier_id`),
  CONSTRAINT `stock_details_ibfk_1` FOREIGN KEY (`medc_id`) REFERENCES `medicine` (`medc_id`) ON DELETE CASCADE,
  CONSTRAINT `stock_details_ibfk_2` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_details`
--

LOCK TABLES `stock_details` WRITE;
/*!40000 ALTER TABLE `stock_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supplier_id` int(11) NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(255) DEFAULT NULL,
  `supplier_addr` text,
  `supplier_phone_num` varchar(11) DEFAULT NULL,
  `supplier_email` varchar(255) DEFAULT NULL,
  `supplier_contact_person` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'supllier1','supplier-address1','9876543210','supplier-email1','supplier-contact1'),(2,'supplier2','supplier-address2	','9876543210','supplier-email2','supplier-contact2');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-02 18:40:36
