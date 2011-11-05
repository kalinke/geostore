-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.16


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema geostore
--

CREATE DATABASE IF NOT EXISTS geostore;
USE geostore;

--
-- Definition of table `gs_cidades`
--

DROP TABLE IF EXISTS `gs_cidades`;
CREATE TABLE `gs_cidades` (
  `id_cidade` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `id_unidade_federacao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_cidade`),
  KEY `FK9EBE957C8891AEB9` (`id_unidade_federacao`),
  CONSTRAINT `FK9EBE957C8891AEB9` FOREIGN KEY (`id_unidade_federacao`) REFERENCES `gs_unidades_federacao` (`id_unidade_federacao`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_cidades`
--

/*!40000 ALTER TABLE `gs_cidades` DISABLE KEYS */;
INSERT INTO `gs_cidades` (`id_cidade`,`descricao`,`id_unidade_federacao`) VALUES 
 (1,'Curitiba',1),
 (2,'Colombo',1),
 (3,'São José dos Pinhais',1),
 (4,'São Paulo',2),
 (5,'Santos',2),
 (6,'Rio de Janeiro',3);
/*!40000 ALTER TABLE `gs_cidades` ENABLE KEYS */;


--
-- Definition of table `gs_empresa`
--

DROP TABLE IF EXISTS `gs_empresa`;
CREATE TABLE `gs_empresa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contato` varchar(30) DEFAULT NULL,
  `documento` varchar(14) DEFAULT NULL,
  `email` varchar(90) DEFAULT NULL,
  `inscricao_estadual` varchar(20) DEFAULT NULL,
  `nome_fantasia` varchar(200) DEFAULT NULL,
  `razao_social` varchar(200) DEFAULT NULL,
  `telefone` varchar(11) DEFAULT NULL,
  `id_endereco` bigint(20) DEFAULT NULL,
  `id_status_empresa` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK100F483691ADF2EE` (`id_endereco`),
  KEY `FK100F48364F8EA35F` (`id_status_empresa`),
  CONSTRAINT `FK100F48364F8EA35F` FOREIGN KEY (`id_status_empresa`) REFERENCES `gs_status_empresa` (`id`),
  CONSTRAINT `FK100F483691ADF2EE` FOREIGN KEY (`id_endereco`) REFERENCES `gs_enderecos` (`id_endereco`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_empresa`
--

/*!40000 ALTER TABLE `gs_empresa` DISABLE KEYS */;
INSERT INTO `gs_empresa` (`id`,`contato`,`documento`,`email`,`inscricao_estadual`,`nome_fantasia`,`razao_social`,`telefone`,`id_endereco`,`id_status_empresa`) VALUES 
 (2,'WAGNER','00000000000000','WAGNER@GMAIL.COM','000000000000000000','WAGNER','WAGNER LOJAS SA','4188064406',2,1),
 (3,'HAGY','00000000000000','HAGY@GMAIL.COM','000000000000000000','HAGY','HAGY LOJA SA','4188460289',3,1),
 (4,'ALISSON','00000000000000','ALISSON@GMAIL.COM','0000000000000000000','ALISSON','ALISSON SA','4130927084',4,1),
 (5,'JONAS','00000000000000','JONASCARIG@GMAIL.COM','0000000000000000000','JONAS','JONAS SA','4198411211',7,1);
/*!40000 ALTER TABLE `gs_empresa` ENABLE KEYS */;


--
-- Definition of table `gs_enderecos`
--

DROP TABLE IF EXISTS `gs_enderecos`;
CREATE TABLE `gs_enderecos` (
  `id_endereco` bigint(20) NOT NULL AUTO_INCREMENT,
  `CEP` varchar(8) DEFAULT NULL,
  `bairro` varchar(40) DEFAULT NULL,
  `complemento_logradouro` varchar(30) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `logradouro` varchar(150) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `numero_logradouro` varchar(6) DEFAULT NULL,
  `id_cidade` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_endereco`),
  KEY `FK20F662377F08F8A4` (`id_cidade`),
  CONSTRAINT `FK20F662377F08F8A4` FOREIGN KEY (`id_cidade`) REFERENCES `gs_cidades` (`id_cidade`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_enderecos`
--

/*!40000 ALTER TABLE `gs_enderecos` DISABLE KEYS */;
INSERT INTO `gs_enderecos` (`id_endereco`,`CEP`,`bairro`,`complemento_logradouro`,`latitude`,`logradouro`,`longitude`,`numero_logradouro`,`id_cidade`) VALUES 
 (2,'81320180','Fazendinha','APTO 14',-25.4746599,'Rua Alfredo José Pinto',-49.3261979,'1640',1),
 (3,'81670110','Boqueirão','CASA 06',-25.4980833,'Rua Major Theolindo Ferreira Ribas',-49.2502819,'2527',1),
 (4,'81570210','Uberaba','CASA',-25.4798216,'Rua Antônio Daniel Dalcuche Filho',-49.2278134,'607',1),
 (7,'81050500','Novo Mundo','APTO 02',-25.4904714,'Rua General Potiguara',-49.3156566,'1428',1),
 (8,'81050500','Novo Mundo','',-25.4904714,'Rua General Potiguara',-49.3156566,'1428',1),
 (9,'81570060','Uberaba','',-25.4764116,'Rua Santa Brígida',-49.2353602,'123',1),
 (10,'81510001','Guabirotuba','',-25.4677121,'Avenida Senador Salgado Filho',-49.240624,'353',1),
 (11,'82120100','Pilarzinho','',-25.4283563,'Rua Primeiro-Ministro Brochado da Rocha',-49.2732515,'1428',1),
 (12,'81320170','Fazendinha','',-25.4813421,'Rua Antônio Rechetelo',-49.3221625,'100',1),
 (14,'81110180','Capão Raso','',-25.5018777,'Rua Osório dos Santos Pacheco',-49.2853166,'539',1),
 (15,'81670110','Boqueirão','',-25.4980833,'Rua Major Theolindo Ferreira Ribas',-49.2502819,'2527',1),
 (16,'81320160','Fazendinha','',-25.4823652,'Rua Sérgio Navarro',-49.3223171,'123',1);
/*!40000 ALTER TABLE `gs_enderecos` ENABLE KEYS */;


--
-- Definition of table `gs_lojas`
--

DROP TABLE IF EXISTS `gs_lojas`;
CREATE TABLE `gs_lojas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contato` varchar(30) DEFAULT NULL,
  `documento` varchar(14) DEFAULT NULL,
  `email` varchar(90) DEFAULT NULL,
  `inscricao_estadual` varchar(20) DEFAULT NULL,
  `nome_fantasia` varchar(200) DEFAULT NULL,
  `razao_social` varchar(200) DEFAULT NULL,
  `telefone` varchar(11) DEFAULT NULL,
  `id_empresa_superior` bigint(20) DEFAULT NULL,
  `id_endereco` bigint(20) DEFAULT NULL,
  `id_status_loja` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK374808A691ADF2EE` (`id_endereco`),
  KEY `FK374808A674A8D51C` (`id_empresa_superior`),
  KEY `FK374808A6A8CC54C7` (`id_status_loja`),
  CONSTRAINT `FK374808A674A8D51C` FOREIGN KEY (`id_empresa_superior`) REFERENCES `gs_empresa` (`id`),
  CONSTRAINT `FK374808A691ADF2EE` FOREIGN KEY (`id_endereco`) REFERENCES `gs_enderecos` (`id_endereco`),
  CONSTRAINT `FK374808A6A8CC54C7` FOREIGN KEY (`id_status_loja`) REFERENCES `gs_status_usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_lojas`
--

/*!40000 ALTER TABLE `gs_lojas` DISABLE KEYS */;
INSERT INTO `gs_lojas` (`id`,`contato`,`documento`,`email`,`inscricao_estadual`,`nome_fantasia`,`razao_social`,`telefone`,`id_empresa_superior`,`id_endereco`,`id_status_loja`) VALUES 
 (3,'JONAS','00000000000000','JONAS@GMAIL.COM','0000000000000000000','LOJA 01 JONAS','LOJA 01 JONAS','4133675234',5,8,1),
 (4,'ALISSON','46941612000180','loja@alisson.com','353553','LOJA ALISSON 01','LOJA ALISSON 01','4130927084',4,9,1),
 (5,'ALISSON 02','54834720000181','loja02@alisson.com','34354','LOJA ALISSON 02','LOJA ALISSON 02','4196926477',4,10,1),
 (6,'JONAS','00000000000000','JONAS@GMAIL.COM','0000000000000000000','LOJA 02 JONAS','LOJA 02 JONAS','4121694848',5,11,1),
 (7,'Wagner','08731560000128','wabroli@gmail.com','123456','Wagner01','Loja Wagner01','4184808909',2,12,1),
 (8,'Hagy','95111164000100','lojahagy@gmail.com','6677','Loja Hagy 1','Loja Hagy 1','4188460289',3,14,1),
 (9,'Hagy','54466258000107','lojahagy2@gmail.com','234','Loja Hagy 2','Loja Hagy 2','4188460289',3,15,1),
 (10,'','32605642000100','wabroli77@gmail.com','123457','Wagner02','Loja Wagner02','4198746783',2,16,1);
/*!40000 ALTER TABLE `gs_lojas` ENABLE KEYS */;


--
-- Definition of table `gs_produtos`
--

DROP TABLE IF EXISTS `gs_produtos`;
CREATE TABLE `gs_produtos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `id_loja` bigint(20) DEFAULT NULL,
  `id_status_produto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6E92FE8D65A8BD50` (`id_loja`),
  KEY `FK6E92FE8DEC3FC03F` (`id_status_produto`),
  CONSTRAINT `FK6E92FE8D65A8BD50` FOREIGN KEY (`id_loja`) REFERENCES `gs_lojas` (`id`),
  CONSTRAINT `FK6E92FE8DEC3FC03F` FOREIGN KEY (`id_status_produto`) REFERENCES `gs_status_produto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_produtos`
--

/*!40000 ALTER TABLE `gs_produtos` DISABLE KEYS */;
INSERT INTO `gs_produtos` (`id`,`descricao`,`nome`,`valor`,`id_loja`,`id_status_produto`) VALUES 
 (1,'NIKE AIR','TENIS',0,3,1),
 (2,'celular nokia sl30','celular',1213,4,1),
 (3,'MOTOROLA DEFY','CELULAR',0,3,1),
 (4,'microondas philco 30l','microondas',2342,4,1),
 (5,'notebook toshiba com processador amd','notebook',324,5,1),
 (6,'TARGUS PARA NOTEBOOK 15','MOCHILA',0,6,1),
 (7,'sofa de courino preto','sofa',524,5,1),
 (8,'CITZEN WR100','RELOGIO DE PULSO',0,6,1),
 (9,'SONY VAIO F226','NOTEBOOK',0,3,1),
 (10,'Microsoft Basic Optical Mouse V2.0','Mouse',39.9,7,1),
 (11,'Google Android - Aprenda a criar aplicações para smartphone Android','LIVRO',81,7,1),
 (12,'LCD 42 - Phillips','Televisão',1500,8,1),
 (13,'Helivana - Buffet Livre','Restaurante',6.7,8,1),
 (14,'DiFrango - Buffet Livre','Restaurante',11.9,9,1),
 (15,'HD Externo Raptor Desktop 500GB','HD Externo',349,10,1),
 (16,'Pen Drive Cruzer Blade 16GB','Pen Drive',39.9,10,1);
/*!40000 ALTER TABLE `gs_produtos` ENABLE KEYS */;


--
-- Definition of table `gs_promocao`
--

DROP TABLE IF EXISTS `gs_promocao`;
CREATE TABLE `gs_promocao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qde_solicitada` varchar(100) DEFAULT NULL,
  `qde_voucher` varchar(100) DEFAULT NULL,
  `id_produto` bigint(20) DEFAULT NULL,
  `id_status_promocao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6F0ED655E1689F16` (`id_produto`),
  KEY `FK6F0ED6559CAFF817` (`id_status_promocao`),
  CONSTRAINT `FK6F0ED6559CAFF817` FOREIGN KEY (`id_status_promocao`) REFERENCES `gs_status_promocao` (`id`),
  CONSTRAINT `FK6F0ED655E1689F16` FOREIGN KEY (`id_produto`) REFERENCES `gs_produtos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_promocao`
--

/*!40000 ALTER TABLE `gs_promocao` DISABLE KEYS */;
/*!40000 ALTER TABLE `gs_promocao` ENABLE KEYS */;


--
-- Definition of table `gs_status_empresa`
--

DROP TABLE IF EXISTS `gs_status_empresa`;
CREATE TABLE `gs_status_empresa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_status_empresa`
--

/*!40000 ALTER TABLE `gs_status_empresa` DISABLE KEYS */;
INSERT INTO `gs_status_empresa` (`id`,`descricao`) VALUES 
 (1,'Ativo'),
 (2,'Inativo'),
 (3,'Bloqueado');
/*!40000 ALTER TABLE `gs_status_empresa` ENABLE KEYS */;


--
-- Definition of table `gs_status_loja`
--

DROP TABLE IF EXISTS `gs_status_loja`;
CREATE TABLE `gs_status_loja` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_status_loja`
--

/*!40000 ALTER TABLE `gs_status_loja` DISABLE KEYS */;
INSERT INTO `gs_status_loja` (`id`,`descricao`) VALUES 
 (1,'Ativo'),
 (2,'Inativo'),
 (3,'Bloqueado');
/*!40000 ALTER TABLE `gs_status_loja` ENABLE KEYS */;


--
-- Definition of table `gs_status_produto`
--

DROP TABLE IF EXISTS `gs_status_produto`;
CREATE TABLE `gs_status_produto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_status_produto`
--

/*!40000 ALTER TABLE `gs_status_produto` DISABLE KEYS */;
INSERT INTO `gs_status_produto` (`id`,`descricao`) VALUES 
 (1,'Ativo'),
 (2,'Inativo'),
 (3,'Bloqueado');
/*!40000 ALTER TABLE `gs_status_produto` ENABLE KEYS */;


--
-- Definition of table `gs_status_promocao`
--

DROP TABLE IF EXISTS `gs_status_promocao`;
CREATE TABLE `gs_status_promocao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_status_promocao`
--

/*!40000 ALTER TABLE `gs_status_promocao` DISABLE KEYS */;
/*!40000 ALTER TABLE `gs_status_promocao` ENABLE KEYS */;


--
-- Definition of table `gs_status_usuario`
--

DROP TABLE IF EXISTS `gs_status_usuario`;
CREATE TABLE `gs_status_usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_status_usuario`
--

/*!40000 ALTER TABLE `gs_status_usuario` DISABLE KEYS */;
INSERT INTO `gs_status_usuario` (`id`,`descricao`) VALUES 
 (1,'Ativo'),
 (2,'Inativo'),
 (3,'Bloqueado');
/*!40000 ALTER TABLE `gs_status_usuario` ENABLE KEYS */;


--
-- Definition of table `gs_tipo_usuario`
--

DROP TABLE IF EXISTS `gs_tipo_usuario`;
CREATE TABLE `gs_tipo_usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_tipo_usuario`
--

/*!40000 ALTER TABLE `gs_tipo_usuario` DISABLE KEYS */;
INSERT INTO `gs_tipo_usuario` (`id`,`descricao`) VALUES 
 (1,'Administrador'),
 (2,'Operador'),
 (3,'Final');
/*!40000 ALTER TABLE `gs_tipo_usuario` ENABLE KEYS */;


--
-- Definition of table `gs_unidades_federacao`
--

DROP TABLE IF EXISTS `gs_unidades_federacao`;
CREATE TABLE `gs_unidades_federacao` (
  `id_unidade_federacao` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `sigla` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_unidade_federacao`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_unidades_federacao`
--

/*!40000 ALTER TABLE `gs_unidades_federacao` DISABLE KEYS */;
INSERT INTO `gs_unidades_federacao` (`id_unidade_federacao`,`descricao`,`sigla`) VALUES 
 (1,'Paraná','PR'),
 (2,'São Paulo','SP'),
 (3,'Rio de Janeiro','RJ');
/*!40000 ALTER TABLE `gs_unidades_federacao` ENABLE KEYS */;


--
-- Definition of table `gs_usuarios`
--

DROP TABLE IF EXISTS `gs_usuarios`;
CREATE TABLE `gs_usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(90) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `senha` varchar(40) DEFAULT NULL,
  `telefone` varchar(11) DEFAULT NULL,
  `id_empresa_vinculo` bigint(20) DEFAULT NULL,
  `id_status_usuario` bigint(20) DEFAULT NULL,
  `id_tipo_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB4F060F88A16FC07` (`id_empresa_vinculo`),
  KEY `FKB4F060F8406D6369` (`id_tipo_usuario`),
  KEY `FKB4F060F814E08A9` (`id_status_usuario`),
  CONSTRAINT `FKB4F060F814E08A9` FOREIGN KEY (`id_status_usuario`) REFERENCES `gs_status_loja` (`id`),
  CONSTRAINT `FKB4F060F8406D6369` FOREIGN KEY (`id_tipo_usuario`) REFERENCES `gs_tipo_usuario` (`id`),
  CONSTRAINT `FKB4F060F88A16FC07` FOREIGN KEY (`id_empresa_vinculo`) REFERENCES `gs_empresa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_usuarios`
--

/*!40000 ALTER TABLE `gs_usuarios` DISABLE KEYS */;
INSERT INTO `gs_usuarios` (`id`,`email`,`nome`,`senha`,`telefone`,`id_empresa_vinculo`,`id_status_usuario`,`id_tipo_usuario`) VALUES 
 (1,'admin',NULL,'a',NULL,NULL,1,1);
/*!40000 ALTER TABLE `gs_usuarios` ENABLE KEYS */;


--
-- Definition of table `gs_voucher`
--

DROP TABLE IF EXISTS `gs_voucher`;
CREATE TABLE `gs_voucher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo_voucher` varchar(100) DEFAULT NULL,
  `num_gerado` int(11) DEFAULT NULL,
  `id_promocao` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9702965BF676E780` (`id_usuario`),
  KEY `FK9702965B4CA2F420` (`id_promocao`),
  CONSTRAINT `FK9702965B4CA2F420` FOREIGN KEY (`id_promocao`) REFERENCES `gs_promocao` (`id`),
  CONSTRAINT `FK9702965BF676E780` FOREIGN KEY (`id_usuario`) REFERENCES `gs_usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gs_voucher`
--

/*!40000 ALTER TABLE `gs_voucher` DISABLE KEYS */;
/*!40000 ALTER TABLE `gs_voucher` ENABLE KEYS */;


--
-- Definition of function `calcDistCoord`
--

DROP FUNCTION IF EXISTS `calcDistCoord`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `calcDistCoord`(latIni FLOAT, lonIni FLOAT, latFim FLOAT, lonFim FLOAT) RETURNS float
BEGIN
   RETURN (SQRT(((latIni - latFim)*(latIni - latFim) + (lonIni - lonFim)*(lonIni - lonFim))) * 111.18) * 1000;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
