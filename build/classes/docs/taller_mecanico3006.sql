-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: taller_mecanico
-- ------------------------------------------------------
-- Server version	8.0.46

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_agenda_disponibilidad`
--

LOCK TABLES `tb_agenda_disponibilidad` WRITE;
/*!40000 ALTER TABLE `tb_agenda_disponibilidad` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_detalle_trabajo_producto`
--

LOCK TABLES `tb_detalle_trabajo_producto` WRITE;
/*!40000 ALTER TABLE `tb_detalle_trabajo_producto` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_detalle_trabajo_servicio`
--

LOCK TABLES `tb_detalle_trabajo_servicio` WRITE;
/*!40000 ALTER TABLE `tb_detalle_trabajo_servicio` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_especialidad`
--

LOCK TABLES `tb_especialidad` WRITE;
/*!40000 ALTER TABLE `tb_especialidad` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_orden_trabajo`
--

LOCK TABLES `tb_orden_trabajo` WRITE;
/*!40000 ALTER TABLE `tb_orden_trabajo` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_persona`
--

LOCK TABLES `tb_persona` WRITE;
/*!40000 ALTER TABLE `tb_persona` DISABLE KEYS */;
INSERT INTO `tb_persona` VALUES (1,'73864177','James','Rafael','913701849','Jr Geranios 123','M','1','2026-06-25 17:12:31',NULL),(2,'10413456','Juan','Garcia Supa','936459521','Av Los olivos 1342 Independencia','M','1','2026-06-26 00:12:11',NULL);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_producto`
--

LOCK TABLES `tb_producto` WRITE;
/*!40000 ALTER TABLE `tb_producto` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_reserva`
--

LOCK TABLES `tb_reserva` WRITE;
/*!40000 ALTER TABLE `tb_reserva` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_servicio`
--

LOCK TABLES `tb_servicio` WRITE;
/*!40000 ALTER TABLE `tb_servicio` DISABLE KEYS */;
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
INSERT INTO `tb_usuario` VALUES (1,2,'JAMESR','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','1'),(2,1,'juang','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','1');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_vehiculo`
--

LOCK TABLES `tb_vehiculo` WRITE;
/*!40000 ALTER TABLE `tb_vehiculo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_vehiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'taller_mecanico'
--

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

-- Dump completed on 2026-06-30 23:19:50
