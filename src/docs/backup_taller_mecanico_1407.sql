-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: taller_mecanico
-- ------------------------------------------------------
-- Server version	8.0.46

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
-- Table structure for table `tb_agenda_disponibilidad`
--

DROP TABLE IF EXISTS `tb_agenda_disponibilidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_agenda_disponibilidad` (
  `id_age_age_dis` int NOT NULL AUTO_INCREMENT,
  `fec_age_dis` date NOT NULL,
  `hor_ini_age_dis` time NOT NULL,
  `hor_fin_age_dis` time NOT NULL,
  `est_slo_age_dis` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'libre',
  PRIMARY KEY (`id_age_age_dis`),
  UNIQUE KEY `uq_agenda_slot` (`fec_age_dis`,`hor_ini_age_dis`),
  KEY `ix_agenda_fecha` (`fec_age_dis`,`est_slo_age_dis`),
  CONSTRAINT `ck_agenda_estado` CHECK ((`est_slo_age_dis` in (_utf8mb4'libre',_utf8mb4'ocupado',_utf8mb4'bloqueado'))),
  CONSTRAINT `ck_agenda_horario` CHECK ((`hor_fin_age_dis` > `hor_ini_age_dis`))
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_agenda_disponibilidad`
--

LOCK TABLES `tb_agenda_disponibilidad` WRITE;
/*!40000 ALTER TABLE `tb_agenda_disponibilidad` DISABLE KEYS */;
INSERT INTO `tb_agenda_disponibilidad` VALUES (1,'2026-07-14','08:00:00','10:00:00','ocupado'),(2,'2026-07-14','10:00:00','12:00:00','ocupado'),(3,'2026-07-15','08:00:00','10:00:00','ocupado'),(4,'2026-07-15','14:00:00','16:00:00','ocupado'),(5,'2026-07-16','09:00:00','11:00:00','ocupado'),(6,'2026-07-16','11:00:00','13:00:00','bloqueado'),(7,'2026-07-17','08:00:00','10:00:00','ocupado'),(8,'2026-07-17','10:00:00','12:00:00','ocupado'),(9,'2026-01-05','09:00:00','10:30:00','ocupado'),(10,'2026-01-20','10:00:00','11:30:00','ocupado'),(11,'2026-02-10','08:30:00','10:00:00','ocupado'),(12,'2026-02-24','11:00:00','12:00:00','ocupado'),(13,'2026-03-12','09:00:00','10:30:00','ocupado'),(14,'2026-03-27','08:00:00','09:30:00','ocupado'),(15,'2026-04-14','10:00:00','11:30:00','ocupado'),(16,'2026-05-02','09:30:00','10:30:00','ocupado'),(17,'2026-06-03','08:00:00','10:00:00','ocupado'),(18,'2026-07-02','11:00:00','12:00:00','ocupado');
/*!40000 ALTER TABLE `tb_agenda_disponibilidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_detalle_trabajo_producto`
--

DROP TABLE IF EXISTS `tb_detalle_trabajo_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_detalle_trabajo_producto` (
  `id_det_tra_pro` int NOT NULL AUTO_INCREMENT,
  `id_ord_det_tra_pro` int NOT NULL,
  `id_pro_det_tra_pro` int NOT NULL,
  `can_det_tra_pro` int NOT NULL DEFAULT '1',
  `pre_uni_det_tra_pro` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_det_tra_pro`),
  KEY `fk_dproducto_ot` (`id_ord_det_tra_pro`),
  KEY `fk_dproducto_producto` (`id_pro_det_tra_pro`),
  CONSTRAINT `fk_dproducto_ot` FOREIGN KEY (`id_ord_det_tra_pro`) REFERENCES `tb_orden_trabajo` (`id_ord_trab`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_dproducto_producto` FOREIGN KEY (`id_pro_det_tra_pro`) REFERENCES `tb_producto` (`id_prod`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `ck_dproducto_cantidad` CHECK ((`can_det_tra_pro` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_detalle_trabajo_producto`
--

LOCK TABLES `tb_detalle_trabajo_producto` WRITE;
/*!40000 ALTER TABLE `tb_detalle_trabajo_producto` DISABLE KEYS */;
INSERT INTO `tb_detalle_trabajo_producto` VALUES (1,1,1,4,35.00),(2,1,3,1,25.00),(3,1,2,1,45.00),(4,2,4,1,180.00),(5,2,3,1,25.00),(6,2,1,3,35.00),(7,3,1,4,35.00),(8,3,5,1,250.00),(9,3,2,1,45.00),(10,58,1,1,35.00),(11,60,5,2,250.00),(12,61,5,2,250.00),(13,61,2,1,45.00);
/*!40000 ALTER TABLE `tb_detalle_trabajo_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_detalle_trabajo_servicio`
--

DROP TABLE IF EXISTS `tb_detalle_trabajo_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_detalle_trabajo_servicio` (
  `id_det_tra_ser` int NOT NULL AUTO_INCREMENT,
  `id_ord_det_tra_ser` int NOT NULL,
  `id_ser_det_tra_ser` int NOT NULL,
  `can_det_tra_ser` int NOT NULL DEFAULT '1',
  `pre_uni_det_tra_ser` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_det_tra_ser`),
  KEY `fk_dservicio_ot` (`id_ord_det_tra_ser`),
  KEY `fk_dservicio_servicio` (`id_ser_det_tra_ser`),
  CONSTRAINT `fk_dservicio_ot` FOREIGN KEY (`id_ord_det_tra_ser`) REFERENCES `tb_orden_trabajo` (`id_ord_trab`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_dservicio_servicio` FOREIGN KEY (`id_ser_det_tra_ser`) REFERENCES `tb_servicio` (`id_serv`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `ck_dservicio_cantidad` CHECK ((`can_det_tra_ser` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=260 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_detalle_trabajo_servicio`
--

LOCK TABLES `tb_detalle_trabajo_servicio` WRITE;
/*!40000 ALTER TABLE `tb_detalle_trabajo_servicio` DISABLE KEYS */;
INSERT INTO `tb_detalle_trabajo_servicio` VALUES (1,1,1,1,80.00),(2,1,4,1,200.00),(3,1,5,1,100.00),(4,2,3,1,150.00),(5,2,2,1,120.00),(6,2,1,1,80.00),(7,3,4,1,200.00),(8,3,5,1,100.00),(9,3,2,1,120.00),(213,16,1,1,80.00),(214,16,5,1,100.00),(215,17,9,1,250.00),(216,18,3,1,150.00),(217,18,10,1,180.00),(218,19,1,1,80.00),(219,20,2,1,120.00),(220,20,15,1,70.00),(221,21,4,1,200.00),(222,21,1,1,80.00),(223,22,5,1,100.00),(224,22,12,1,90.00),(225,23,9,1,250.00),(226,23,2,1,120.00),(227,24,1,1,80.00),(228,24,13,1,50.00),(229,25,8,1,150.00),(230,26,12,1,90.00),(231,26,14,1,100.00),(232,27,4,1,200.00),(233,27,1,1,80.00),(234,27,2,1,120.00),(235,28,5,1,100.00),(236,29,3,1,150.00),(237,29,10,1,180.00),(238,30,9,1,250.00),(239,46,1,1,80.00),(240,47,9,1,250.00),(241,48,2,1,120.00),(242,48,15,1,70.00),(243,49,5,1,100.00),(244,50,3,1,150.00),(245,50,10,1,180.00),(246,51,1,1,80.00),(247,51,12,1,90.00),(248,52,4,1,200.00),(249,52,2,1,120.00),(250,53,8,1,150.00),(251,54,9,1,250.00),(252,54,2,1,120.00),(253,55,1,1,80.00),(254,55,13,1,50.00),(255,59,1,1,80.00),(256,59,9,1,250.00),(257,62,6,1,80.00),(258,62,15,1,70.00),(259,63,12,1,90.00);
/*!40000 ALTER TABLE `tb_detalle_trabajo_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_especialidad`
--

DROP TABLE IF EXISTS `tb_especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_especialidad` (
  `id_esp_esp` int NOT NULL AUTO_INCREMENT,
  `des_esp` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_esp_esp`),
  UNIQUE KEY `uq_especialidad_descripcion` (`des_esp`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_especialidad`
--

LOCK TABLES `tb_especialidad` WRITE;
/*!40000 ALTER TABLE `tb_especialidad` DISABLE KEYS */;
INSERT INTO `tb_especialidad` VALUES (5,'Diagnóstico Electrónico'),(2,'Electricidad Automotriz'),(3,'Frenos y Suspensión'),(1,'Mecánica General'),(4,'Transmisión y Caja de Cambios');
/*!40000 ALTER TABLE `tb_especialidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_estado_orden_trabajo`
--

DROP TABLE IF EXISTS `tb_estado_orden_trabajo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_estado_orden_trabajo` (
  `id_est_est_ord_tra` int NOT NULL AUTO_INCREMENT,
  `des_est_ord_tra` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_est_est_ord_tra`),
  UNIQUE KEY `uq_estadoot_descripcion` (`des_est_ord_tra`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_estado_orden_trabajo`
--

LOCK TABLES `tb_estado_orden_trabajo` WRITE;
/*!40000 ALTER TABLE `tb_estado_orden_trabajo` DISABLE KEYS */;
INSERT INTO `tb_estado_orden_trabajo` VALUES (2,'Activa'),(1,'Borrador'),(6,'Cancelada'),(3,'En proceso'),(5,'Entregada'),(4,'Finalizada');
/*!40000 ALTER TABLE `tb_estado_orden_trabajo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_marca`
--

DROP TABLE IF EXISTS `tb_marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_marca` (
  `id_mar` int NOT NULL AUTO_INCREMENT,
  `nom_mar` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id_mar`),
  UNIQUE KEY `uq_marca_nombre` (`nom_mar`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_marca`
--

LOCK TABLES `tb_marca` WRITE;
/*!40000 ALTER TABLE `tb_marca` DISABLE KEYS */;
INSERT INTO `tb_marca` VALUES (6,'Chevrolet'),(7,'Ford'),(2,'Honda'),(4,'Hyundai'),(5,'Kia'),(8,'Mazda'),(3,'Nissan'),(10,'Suzuki'),(1,'Toyota'),(9,'Volkswagen');
/*!40000 ALTER TABLE `tb_marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_mecanico`
--

DROP TABLE IF EXISTS `tb_mecanico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_mecanico` (
  `id_per_mec` int NOT NULL,
  `est_mec` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_per_mec`),
  CONSTRAINT `fk_mecanico_persona` FOREIGN KEY (`id_per_mec`) REFERENCES `tb_persona` (`id_per`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `ck_mecanico_estado` CHECK ((`est_mec` in (_utf8mb4'0',_utf8mb4'1')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_mecanico`
--

LOCK TABLES `tb_mecanico` WRITE;
/*!40000 ALTER TABLE `tb_mecanico` DISABLE KEYS */;
INSERT INTO `tb_mecanico` VALUES (6,'1'),(7,'1'),(8,'1'),(9,'1'),(10,'1');
/*!40000 ALTER TABLE `tb_mecanico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_mecanico_especialidad`
--

DROP TABLE IF EXISTS `tb_mecanico_especialidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_mecanico_especialidad` (
  `id_per_mec_esp` int NOT NULL,
  `id_esp_mec_esp` int NOT NULL,
  PRIMARY KEY (`id_per_mec_esp`,`id_esp_mec_esp`),
  KEY `fk_mecesp_especialidad` (`id_esp_mec_esp`),
  CONSTRAINT `fk_mecesp_especialidad` FOREIGN KEY (`id_esp_mec_esp`) REFERENCES `tb_especialidad` (`id_esp_esp`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_mecesp_mecanico` FOREIGN KEY (`id_per_mec_esp`) REFERENCES `tb_mecanico` (`id_per_mec`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_mecanico_especialidad`
--

LOCK TABLES `tb_mecanico_especialidad` WRITE;
/*!40000 ALTER TABLE `tb_mecanico_especialidad` DISABLE KEYS */;
INSERT INTO `tb_mecanico_especialidad` VALUES (6,1),(8,1),(10,1),(7,2),(10,2),(6,3),(9,3),(8,4),(7,5),(9,5);
/*!40000 ALTER TABLE `tb_mecanico_especialidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_modelo`
--

DROP TABLE IF EXISTS `tb_modelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_modelo` (
  `id_mod` int NOT NULL AUTO_INCREMENT,
  `id_mar_mod` int NOT NULL,
  `nom_mod` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `est_mod` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_mod`),
  UNIQUE KEY `uq_modelo_marca` (`id_mar_mod`,`nom_mod`),
  CONSTRAINT `fk_modelo_marca` FOREIGN KEY (`id_mar_mod`) REFERENCES `tb_marca` (`id_mar`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `ck_modelo_estado` CHECK ((`est_mod` in (_utf8mb4'0',_utf8mb4'1')))
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_modelo`
--

LOCK TABLES `tb_modelo` WRITE;
/*!40000 ALTER TABLE `tb_modelo` DISABLE KEYS */;
INSERT INTO `tb_modelo` VALUES (1,1,'Corolla','1'),(2,1,'Yaris','1'),(3,1,'Hilux','1'),(4,1,'RAV4','1'),(5,2,'Civic','1'),(6,2,'Accord','1'),(7,2,'CR-V','1'),(8,3,'Sentra','1'),(9,3,'Versa','1'),(10,3,'Frontier','1'),(11,4,'Accent','1'),(12,4,'Elantra','1'),(13,4,'Tucson','1'),(14,5,'Rio','1'),(15,5,'Cerato','1'),(16,5,'Sportage','1'),(17,6,'Onix','1'),(18,6,'Cruze','1'),(19,6,'Tracker','1'),(20,7,'Fiesta','1'),(21,7,'Focus','1'),(22,7,'Ranger','1'),(23,8,'Mazda 2','1'),(24,8,'Mazda 3','1'),(25,8,'CX-5','1'),(26,9,'Gol','1'),(27,9,'Polo','1'),(28,9,'Tiguan','1'),(29,10,'Swift','1'),(30,10,'Vitara','1'),(31,10,'Jimny','1');
/*!40000 ALTER TABLE `tb_modelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_orden_trabajo`
--

DROP TABLE IF EXISTS `tb_orden_trabajo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_orden_trabajo` (
  `id_ord_trab` int NOT NULL AUTO_INCREMENT,
  `id_res_ord_tra` int DEFAULT NULL,
  `id_veh_ord_tra` int NOT NULL,
  `id_mec_ord_tra` int DEFAULT NULL,
  `id_est_est_ord_tra` int NOT NULL,
  `fec_ing_ord_tra` datetime DEFAULT NULL,
  `fec_ent_ord_tra` datetime DEFAULT NULL,
  `obs_cli_ord_tra` text COLLATE utf8mb4_unicode_ci,
  `obs_vis_ord_tra` text COLLATE utf8mb4_unicode_ci,
  `tot_ord_tra` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id_ord_trab`),
  UNIQUE KEY `uq_ot_reserva` (`id_res_ord_tra`),
  KEY `fk_ot_vehiculo` (`id_veh_ord_tra`),
  KEY `ix_ot_mecanico` (`id_mec_ord_tra`),
  KEY `ix_ot_estado` (`id_est_est_ord_tra`),
  KEY `ix_ot_reserva` (`id_res_ord_tra`),
  CONSTRAINT `fk_ot_estado` FOREIGN KEY (`id_est_est_ord_tra`) REFERENCES `tb_estado_orden_trabajo` (`id_est_est_ord_tra`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_ot_mecanico` FOREIGN KEY (`id_mec_ord_tra`) REFERENCES `tb_mecanico` (`id_per_mec`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_ot_reserva` FOREIGN KEY (`id_res_ord_tra`) REFERENCES `tb_reserva` (`id_resv`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_ot_vehiculo` FOREIGN KEY (`id_veh_ord_tra`) REFERENCES `tb_vehiculo` (`id_veh`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_orden_trabajo`
--

LOCK TABLES `tb_orden_trabajo` WRITE;
/*!40000 ALTER TABLE `tb_orden_trabajo` DISABLE KEYS */;
INSERT INTO `tb_orden_trabajo` VALUES (1,1,1,6,3,'2026-07-13 08:15:00',NULL,'Cambio de aceite, revisión general solicitada por cliente',NULL,590.00),(2,NULL,2,7,4,'2026-07-10 09:00:00',NULL,'Ruidos al frenar, cliente menciona vibración en el volante',NULL,660.00),(3,NULL,3,8,3,'2026-07-12 10:30:00',NULL,'Mantenimiento 10 000 km, incluir revisión de correas',NULL,855.00),(16,NULL,1,7,5,'2026-01-10 09:00:00',NULL,'Aceite y diagnóstico preventivo',NULL,180.00),(17,NULL,2,8,4,'2026-01-25 10:00:00',NULL,'Mantenimiento general programado',NULL,250.00),(18,NULL,3,9,5,'2026-02-05 08:30:00',NULL,'Frenos con ruido al frenar',NULL,330.00),(19,NULL,4,10,4,'2026-02-18 11:00:00',NULL,'Cambio de aceite rutinario',NULL,80.00),(20,NULL,5,6,5,'2026-03-03 09:30:00',NULL,'Vibración al manejar en autopista',NULL,190.00),(21,NULL,7,7,4,'2026-03-20 08:00:00',NULL,'Mantenimiento 10 000 km más aceite',NULL,280.00),(22,NULL,8,8,3,'2026-04-05 10:30:00',NULL,'Motor con falla esporádica',NULL,190.00),(23,NULL,9,9,5,'2026-04-22 09:00:00',NULL,'Mantenimiento y alineación completa',NULL,370.00),(24,NULL,10,10,4,'2026-05-08 11:00:00',NULL,'Aceite y filtro de aire',NULL,130.00),(25,NULL,11,6,3,'2026-05-25 08:30:00',NULL,'Revisión de frenos traseros',NULL,150.00),(26,NULL,12,7,5,'2026-06-10 10:00:00',NULL,'Bujías y revisión de suspensión',NULL,190.00),(27,NULL,14,8,4,'2026-06-25 09:00:00',NULL,'Mantenimiento 10 000 km + alineación',NULL,400.00),(28,NULL,1,9,2,'2026-07-07 11:30:00',NULL,'Luz de falla encendida en tablero',NULL,100.00),(29,NULL,2,10,3,'2026-07-11 08:00:00',NULL,'Frenos duros y pastillas gastadas',NULL,330.00),(30,NULL,3,6,2,'2026-07-13 10:00:00',NULL,'Mantenimiento preventivo 20 000 km',NULL,250.00),(31,NULL,1,7,5,'2026-01-10 09:00:00',NULL,'Aceite y diagnóstico preventivo',NULL,180.00),(32,NULL,2,8,4,'2026-01-25 10:00:00',NULL,'Mantenimiento general programado',NULL,250.00),(33,NULL,3,9,5,'2026-02-05 08:30:00',NULL,'Frenos con ruido al frenar',NULL,330.00),(34,NULL,4,10,4,'2026-02-18 11:00:00',NULL,'Cambio de aceite rutinario',NULL,80.00),(35,NULL,5,6,5,'2026-03-03 09:30:00',NULL,'Vibración al manejar en autopista',NULL,190.00),(36,NULL,7,7,4,'2026-03-20 08:00:00',NULL,'Mantenimiento 10 000 km más aceite',NULL,280.00),(37,NULL,8,8,3,'2026-04-05 10:30:00',NULL,'Motor con falla esporádica',NULL,190.00),(38,NULL,9,9,5,'2026-04-22 09:00:00',NULL,'Mantenimiento y alineación completa',NULL,370.00),(39,NULL,10,10,4,'2026-05-08 11:00:00',NULL,'Aceite y filtro de aire',NULL,130.00),(40,NULL,11,6,3,'2026-05-25 08:30:00',NULL,'Revisión de frenos traseros',NULL,150.00),(41,NULL,12,7,5,'2026-06-10 10:00:00',NULL,'Bujías y revisión de suspensión',NULL,190.00),(42,NULL,14,8,4,'2026-06-25 09:00:00',NULL,'Mantenimiento 10 000 km + alineación',NULL,400.00),(43,NULL,1,9,2,'2026-07-07 11:30:00',NULL,'Luz de falla encendida en tablero',NULL,100.00),(44,NULL,2,10,6,'2026-07-11 08:00:00',NULL,'Frenos duros y pastillas gastadas',NULL,330.00),(45,NULL,3,6,2,'2026-07-13 10:00:00',NULL,'Mantenimiento preventivo 20 000 km',NULL,250.00),(46,6,5,6,6,'2026-01-05 09:00:00',NULL,'Cliente ingresó con reserva previa',NULL,80.00),(47,7,2,7,6,'2026-01-20 10:00:00',NULL,'Mantenimiento agendado',NULL,250.00),(48,8,3,8,4,'2026-02-10 08:30:00',NULL,'Alineación y líquido de frenos',NULL,190.00),(49,9,4,9,4,'2026-02-24 11:00:00',NULL,'Diagnóstico por alerta en tablero',NULL,100.00),(50,10,7,10,5,'2026-03-12 09:00:00',NULL,'Revisión y cambio de pastillas',NULL,330.00),(51,11,8,6,4,'2026-03-27 08:00:00',NULL,'Aceite y bujías por fecha de servicio',NULL,170.00),(52,12,9,7,5,'2026-04-14 10:00:00',NULL,'Mantenimiento completo con alineación',NULL,320.00),(53,13,10,8,4,'2026-05-02 09:30:00',NULL,'Revisión de frenos traseros',NULL,150.00),(54,14,14,9,5,'2026-06-03 08:00:00',NULL,'Mantenimiento y alineación',NULL,370.00),(55,15,8,10,4,'2026-07-02 11:00:00',NULL,'Aceite y filtro de aire',NULL,130.00),(56,NULL,2,8,3,NULL,NULL,'No enciende',NULL,0.00),(57,NULL,4,9,3,NULL,NULL,'No enciende',NULL,0.00),(58,NULL,11,8,3,'2026-07-14 03:06:28',NULL,'no tiene aceite y le falla el filtro de aire',NULL,35.00),(59,NULL,9,7,3,'2026-07-14 03:08:46',NULL,'Matenimiento general y cambio de aceite',NULL,330.00),(60,NULL,10,10,3,'2026-07-14 03:11:24',NULL,'Dos correas y un filtro de aire',NULL,500.00),(61,NULL,1,6,3,'2026-07-14 03:13:45',NULL,'test',NULL,545.00),(62,5,5,9,3,'2026-07-14 03:52:39',NULL,'tester',NULL,150.00),(63,16,14,9,3,'2026-07-14 03:53:12',NULL,'dddassa',NULL,90.00);
/*!40000 ALTER TABLE `tb_orden_trabajo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_persona`
--

DROP TABLE IF EXISTS `tb_persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_persona` (
  `id_per` int NOT NULL AUTO_INCREMENT,
  `num_doc_per` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nom_per` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ape_per` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tel_per` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dir_per` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sexo_per` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `est_per` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1',
  `fec_cre_per` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fec_mod_per` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_per`),
  UNIQUE KEY `uq_persona_documento` (`num_doc_per`),
  CONSTRAINT `ck_persona_estado` CHECK ((`est_per` in (_utf8mb4'0',_utf8mb4'1'))),
  CONSTRAINT `ck_persona_sexo` CHECK ((`sexo_per` in (_utf8mb4'M',_utf8mb4'F')))
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_persona`
--

LOCK TABLES `tb_persona` WRITE;
/*!40000 ALTER TABLE `tb_persona` DISABLE KEYS */;
INSERT INTO `tb_persona` VALUES (1,'73864177','James','Rafael','913701849','Jr Geranios 123','M','1','2026-06-25 17:12:31',NULL),(2,'10413456','Luis','Garcia Supa','936459521','Av Los olivos 1342 Independencia','M','1','2026-06-26 00:12:11','2026-07-13 18:47:06'),(3,'45123678','Carlos','Mendoza Ríos','987654321','Av. Los Jardines 456, San Juan','M','1','2026-07-13 20:47:07',NULL),(4,'52369874','María','Torres Vega','976543210','Jr. Las Flores 789, Miraflores','F','1','2026-07-13 20:47:07',NULL),(5,'63147852','Roberto','Sánchez Luna','965432109','Calle Las Rosas 321, Surco','M','1','2026-07-13 20:47:07',NULL),(6,'74258963','Luis','Quispe Mamani','954321098','Av. Industrial 123, Ate','M','1','2026-07-13 20:47:07',NULL),(7,'85369741','Pedro','Flores Ccahua','943210987','Jr. Los Álamos 567, Villa María','M','1','2026-07-13 20:47:07',NULL),(8,'96147852','Jorge','Ramírez Coila','932109876','Calle Los Pinos 890, SJL','M','1','2026-07-13 20:47:07',NULL),(9,'10258369','Ana','García Huanca','921098765','Av. Los Olivos 234, Independencia','F','1','2026-07-13 20:47:07',NULL),(10,'11369741','Diego','Castillo Paredes','910987654','Jr. Las Violetas 678, Comas','M','1','2026-07-13 20:47:07',NULL),(11,'45123456','Maria','Torres Quispe','987654321','Av. Lima 456, Miraflores','F','1','2026-07-13 23:46:50',NULL),(12,'62345678','Carlos','Mendez Huanca','976543210','Jr. Arequipa 789, Lince','M','1','2026-07-13 23:46:50',NULL),(13,'73456789','Ana','Flores Vega','965432109','Calle Tacna 321, Barranco','F','1','2026-07-13 23:46:50',NULL),(14,'84567890','Roberto','Quispe Mamani','954321098','Av. Brasil 654, Pueblo Libre','M','1','2026-07-13 23:46:50',NULL),(15,'95678901','Sandra','Lima Paredes','943210987','Jr. Cusco 987, Rimac','F','1','2026-07-13 23:46:50',NULL),(16,'06789012','Pedro','Vargas Ccama','932109876','Av. Colonial 147, Callao','M','1','2026-07-13 23:46:50',NULL),(17,'17890123','Luis','Huanca Mamani','921098765','Av. Tupac Amaru 258, SJL','M','1','2026-07-13 23:46:50',NULL),(18,'28901234','Jorge','Quispe Flores','910987654','Jr. Azangaro 369, Centro','M','1','2026-07-13 23:46:50',NULL),(19,'39012345','Miguel','Flores Vargas','909876543','Av. Próceres 741, SJM','M','1','2026-07-13 23:46:50',NULL);
/*!40000 ALTER TABLE `tb_persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_producto`
--

DROP TABLE IF EXISTS `tb_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_producto` (
  `id_prod` int NOT NULL AUTO_INCREMENT,
  `sku_pro` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `des_pro` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pre_ven_pro` decimal(10,2) NOT NULL,
  `sto_act_pro` int NOT NULL DEFAULT '0',
  `est_pro` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_prod`),
  UNIQUE KEY `uq_producto_sku` (`sku_pro`),
  CONSTRAINT `ck_producto_estado` CHECK ((`est_pro` in (_utf8mb4'0',_utf8mb4'1'))),
  CONSTRAINT `ck_producto_stock` CHECK ((`sto_act_pro` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_producto`
--

LOCK TABLES `tb_producto` WRITE;
/*!40000 ALTER TABLE `tb_producto` DISABLE KEYS */;
INSERT INTO `tb_producto` VALUES (1,'ACE-5W30-1L','Aceite Motor 5W30 1L',35.00,50,'1'),(2,'FIL-AIR-001','Filtro de aire universal',45.00,30,'1'),(3,'FIL-ACE-001','Filtro de aceite estándar',25.00,40,'1'),(4,'PAD-FRE-DEL','Pastillas de freno delanteras',180.00,20,'1'),(5,'COR-DIS-001','Correa de distribución',250.00,15,'1');
/*!40000 ALTER TABLE `tb_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_reserva`
--

DROP TABLE IF EXISTS `tb_reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_reserva` (
  `id_resv` int NOT NULL AUTO_INCREMENT,
  `id_per_res` int NOT NULL,
  `id_veh_res` int NOT NULL,
  `id_age_res` int NOT NULL,
  `mot_res` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fec_cre_res` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `est_res_res` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'pendiente',
  PRIMARY KEY (`id_resv`),
  UNIQUE KEY `uq_reserva_agenda` (`id_age_res`),
  KEY `fk_reserva_vehiculo` (`id_veh_res`),
  KEY `ix_reserva_persona` (`id_per_res`),
  KEY `ix_reserva_estado` (`est_res_res`),
  CONSTRAINT `fk_reserva_agenda` FOREIGN KEY (`id_age_res`) REFERENCES `tb_agenda_disponibilidad` (`id_age_age_dis`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_reserva_vehiculo` FOREIGN KEY (`id_veh_res`) REFERENCES `tb_vehiculo` (`id_veh`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `tb_reserva_tb_persona_FK` FOREIGN KEY (`id_per_res`) REFERENCES `tb_persona` (`id_per`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `ck_reserva_estado` CHECK ((`est_res_res` in (_utf8mb4'pendiente',_utf8mb4'confirmada',_utf8mb4'cancelada',_utf8mb4'vencida',_utf8mb4'convertida')))
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_reserva`
--

LOCK TABLES `tb_reserva` WRITE;
/*!40000 ALTER TABLE `tb_reserva` DISABLE KEYS */;
INSERT INTO `tb_reserva` VALUES (1,2,1,1,'Cambio de aceite y revisión general del motor','2026-07-13 20:47:07','convertida'),(2,3,2,3,'Ruidos extraños al frenar, revisar pastillas','2026-07-13 20:47:07','confirmada'),(3,4,3,4,'Mantenimiento preventivo programado','2026-07-13 20:47:07','pendiente'),(4,5,4,7,'Alineación y balanceo después de golpe en llanta','2026-07-13 20:47:07','confirmada'),(5,2,5,8,'Luz de motor encendida, solicita diagnóstico general','2026-07-13 20:47:07','convertida'),(6,2,5,9,'Cambio de aceite preventivo','2026-01-03 11:00:00','convertida'),(7,3,2,10,'Mantenimiento general 20 000 km','2026-01-17 09:00:00','convertida'),(8,4,3,11,'Vibración al frenar','2026-02-07 16:00:00','convertida'),(9,5,4,12,'Revisión de luces y diagnóstico','2026-02-21 10:00:00','convertida'),(10,2,7,13,'Frenos en mal estado','2026-03-10 14:00:00','convertida'),(11,3,8,14,'Aceite y revisión de bujías','2026-03-25 09:00:00','convertida'),(12,4,9,15,'Mantenimiento 10 000 km programado','2026-04-11 11:00:00','convertida'),(13,5,10,16,'Pedal de freno esponjoso','2026-04-30 17:00:00','convertida'),(14,2,14,17,'Mantenimiento preventivo CR-V','2026-06-01 12:00:00','convertida'),(15,3,8,18,'Cambio de aceite y filtros','2026-06-30 10:00:00','convertida'),(16,2,14,5,'Luz de motor encendida, solicita diagnóstico genera','2026-07-14 01:16:44','convertida'),(17,2,14,2,'sin faros','2026-07-14 03:32:01','pendiente');
/*!40000 ALTER TABLE `tb_reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_rol`
--

DROP TABLE IF EXISTS `tb_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_rol` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `nom_rol` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `est_rol` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `uq_rol_nombre` (`nom_rol`),
  CONSTRAINT `ck_rol_estado` CHECK ((`est_rol` in (_utf8mb4'0',_utf8mb4'1')))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_rol`
--

LOCK TABLES `tb_rol` WRITE;
/*!40000 ALTER TABLE `tb_rol` DISABLE KEYS */;
INSERT INTO `tb_rol` VALUES (1,'Cliente','1'),(2,'Administrador','1');
/*!40000 ALTER TABLE `tb_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_servicio`
--

DROP TABLE IF EXISTS `tb_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_servicio` (
  `id_serv` int NOT NULL AUTO_INCREMENT,
  `des_ser` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pre_bas_ser` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_serv`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_servicio`
--

LOCK TABLES `tb_servicio` WRITE;
/*!40000 ALTER TABLE `tb_servicio` DISABLE KEYS */;
INSERT INTO `tb_servicio` VALUES (1,'Cambio de aceite y filtro',80.00),(2,'Alineación y balanceo',120.00),(3,'Revisión y cambio de frenos',150.00),(4,'Mantenimiento preventivo 10 000 km',200.00),(5,'Diagnóstico computarizado',100.00),(6,'Cambio de aceite y filtro',80.00),(7,'Alineación y balanceo',120.00),(8,'Revisión de frenos',150.00),(9,'Mantenimiento general',250.00),(10,'Cambio de pastillas de freno',180.00),(11,'Escaneo diagnóstico OBD',60.00),(12,'Cambio de bujías',90.00),(13,'Cambio de filtro de aire',50.00),(14,'Revisión de suspensión',100.00),(15,'Cambio de líquido de frenos',70.00);
/*!40000 ALTER TABLE `tb_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuario`
--

DROP TABLE IF EXISTS `tb_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuario` (
  `id_per_usu` int NOT NULL,
  `id_rol_usu` int NOT NULL,
  `usu_usu` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pas_h_usu` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `est_usu` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_per_usu`),
  UNIQUE KEY `uq_usuario_login` (`usu_usu`),
  KEY `fk_usuario_rol` (`id_rol_usu`),
  CONSTRAINT `fk_usuario_persona` FOREIGN KEY (`id_per_usu`) REFERENCES `tb_persona` (`id_per`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_usuario_rol` FOREIGN KEY (`id_rol_usu`) REFERENCES `tb_rol` (`id_rol`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `ck_usuario_estado` CHECK ((`est_usu` in (_utf8mb4'0',_utf8mb4'1')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuario`
--

LOCK TABLES `tb_usuario` WRITE;
/*!40000 ALTER TABLE `tb_usuario` DISABLE KEYS */;
INSERT INTO `tb_usuario` VALUES (1,2,'JAMESR','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1'),(2,1,'luisg','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1'),(3,1,'carlosm','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1'),(4,1,'mariat','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1'),(5,1,'robertos','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1'),(11,2,'mariatq','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1'),(12,1,'carlosmh','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1'),(13,1,'anafv','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1'),(14,1,'robertoqm','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1'),(15,1,'sandralp','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1'),(16,1,'pedrovc','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1'),(17,1,'luishm','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1'),(18,1,'jorgeqf','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1'),(19,1,'miguelfv','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1');
/*!40000 ALTER TABLE `tb_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_vehiculo`
--

DROP TABLE IF EXISTS `tb_vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_vehiculo` (
  `id_veh` int NOT NULL AUTO_INCREMENT,
  `id_per_veh` int NOT NULL,
  `id_mod_veh` int NOT NULL,
  `pla_veh` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ani_veh` int DEFAULT NULL,
  `col_veh` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_veh`),
  UNIQUE KEY `uq_vehiculo_placa` (`pla_veh`),
  KEY `fk_vehiculo_modelo` (`id_mod_veh`),
  KEY `ix_vehiculo_persona` (`id_per_veh`),
  CONSTRAINT `fk_vehiculo_modelo` FOREIGN KEY (`id_mod_veh`) REFERENCES `tb_modelo` (`id_mod`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `tb_vehiculo_tb_persona_FK` FOREIGN KEY (`id_per_veh`) REFERENCES `tb_persona` (`id_per`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_vehiculo`
--

LOCK TABLES `tb_vehiculo` WRITE;
/*!40000 ALTER TABLE `tb_vehiculo` DISABLE KEYS */;
INSERT INTO `tb_vehiculo` VALUES (1,2,1,'ABC-123',2020,'Blanco'),(2,3,5,'DEF-456',2019,'Negro'),(3,4,8,'GHI-789',2021,'Plata'),(4,5,14,'JKL-012',2018,'Rojo'),(5,2,17,'MNO-345',2022,'Azul'),(7,2,1,'ABD-123',2020,'Blanco'),(8,3,5,'BEF-456',2019,'Rojo'),(9,4,8,'CGH-789',2021,'Negro'),(10,5,2,'DHI-012',2018,'Plata'),(11,6,12,'EJK-345',2022,'Azul'),(12,7,14,'FLM-678',2020,'Gris'),(13,8,24,'GNO-901',2017,'Blanco'),(14,2,7,'HOP-234',2023,'Negro'),(16,2,1,'XYZ-124',2012,'Blanco');
/*!40000 ALTER TABLE `tb_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'taller_mecanico'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-14 13:49:30
