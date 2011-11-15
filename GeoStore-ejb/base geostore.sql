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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gs_cidades`
--

/*!40000 ALTER TABLE `gs_cidades` DISABLE KEYS */;
INSERT INTO `gs_cidades` (`id_cidade`,`descricao`,`id_unidade_federacao`) VALUES 
 (1,'Curitiba',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gs_empresa`
--

/*!40000 ALTER TABLE `gs_empresa` DISABLE KEYS */;
INSERT INTO `gs_empresa` (`id`,`contato`,`documento`,`email`,`inscricao_estadual`,`nome_fantasia`,`razao_social`,`telefone`,`id_endereco`,`id_status_empresa`) VALUES 
 (1,'José Silvestre','40327754000175','livrariascuritiba@livrariascuritiba.com.br','ISENTO','Livrarias Curitiba','Livrarias Curitiba','4133305000',1,1),
 (2,'Wagner Abreu','61365284000104','saraiva@saraiva.com.br','ISENTO','Livrarias Saraiva','Livrarias Saraiva','4133247475',2,1),
 (3,'Rita de Cassia','83741627000120','comlombo@colombo.com.br','ISENTO','Lojas Colombo','Lojas Colombo','4132246091',3,1),
 (4,'Ana Cristina','24708175000116','multiloja@multiloja.com.br','ISENTO','Multiloja','Multiloja','4139014471',4,1),
 (5,'Silvestre Mariano','28230005000120','contato@driveinburger.com.br','ISENTO','Drive-in Burger','Drive-in Burger','4133299966',5,1),
 (6,'Carolina da Silva','06088593000176','burguerking@burguerking.com.br','ISENTO','Burguer King','Burguer King','4130274970',11,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gs_enderecos`
--

/*!40000 ALTER TABLE `gs_enderecos` DISABLE KEYS */;
INSERT INTO `gs_enderecos` (`id_endereco`,`CEP`,`bairro`,`complemento_logradouro`,`latitude`,`logradouro`,`longitude`,`numero_logradouro`,`id_cidade`) VALUES 
 (1,'80230110','Rebouças','',-25.4451779,'Avenida Marechal Floriano Peixoto',-49.2651104,'1762',1),
 (2,'80420000','Centro','',-25.4380036,'Rua Comendador Araújo',-49.2813826,'731',1),
 (3,'80010200','Centro','',-25.430781,'Rua João Negrão',-49.2664382,'156',1),
 (4,'82300000','São Braz','',-25.4226694,'Avenida Vereador Toaldo Túlio',-49.3497453,'3781',1),
 (5,'81070050','Portão','',-25.475677,'Rua Luiz Parigot de Souza',-49.2954514,'248',1),
 (6,'80230110','Rebouças','',-25.4451779,'Avenida Marechal Floriano Peixoto',-49.2651104,'1762',1),
 (7,'80420000','Centro','',-25.4380036,'Rua Comendador Araújo',-49.2813826,'731',1),
 (8,'80010200','Centro','',-25.430781,'Rua João Negrão',-49.2664382,'156',1),
 (9,'82300000','São Braz','',-25.4226694,'Avenida Vereador Toaldo Túlio',-49.3497453,'3781',1),
 (10,'81070050','Portão','',-25.475677,'Rua Luiz Parigot de Souza',-49.2954514,'248',1),
 (11,'80250030','Centro','',-25.4405231,'Rua Brigadeiro Franco',-49.2768726,'2300',1),
 (12,'80250030','Centro','',-25.4405231,'Rua Brigadeiro Franco',-49.2768726,'2300',1);
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
  CONSTRAINT `FK374808A6A8CC54C7` FOREIGN KEY (`id_status_loja`) REFERENCES `gs_status_usuario` (`id`),
  CONSTRAINT `FK374808A674A8D51C` FOREIGN KEY (`id_empresa_superior`) REFERENCES `gs_empresa` (`id`),
  CONSTRAINT `FK374808A691ADF2EE` FOREIGN KEY (`id_endereco`) REFERENCES `gs_enderecos` (`id_endereco`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gs_lojas`
--

/*!40000 ALTER TABLE `gs_lojas` DISABLE KEYS */;
INSERT INTO `gs_lojas` (`id`,`contato`,`documento`,`email`,`inscricao_estadual`,`nome_fantasia`,`razao_social`,`telefone`,`id_empresa_superior`,`id_endereco`,`id_status_loja`) VALUES 
 (1,'José Silvestre','40327754000175','livrariascuritiba@livrariascuritiba.com.br','ISENTO','Livrarias Curitiba','Livrarias Curitiba','4133305000',1,6,1),
 (2,'Wagner Abreu','61365284000104','saraiva@saraiva.com.br','ISENTO','Saraiva MegaStore Crystal Plaza Shopping ','Livrarias Saraiva','4133247475',2,7,1),
 (3,'Rita de Cassia','83741627000120','comlombo@colombo.com.br','ISENTO','Lojas Colombo','Lojas Colombo','4132246091',3,8,1),
 (4,'Ana Cristina','24708175000116','multiloja@multiloja.com.br','ISENTO','Multiloja','Multiloja','4139014471',4,9,1),
 (5,'Silvestre Mariano','28230005000120','contato@driveinburger.com.br','ISENTO','Drive-in Burger','Drive-in Burger','4133299966',5,10,1),
 (6,'Carolina da Silva','06088593000176','burguerking@burguerking.com.br','ISENTO','Burguer King','Burguer King','4130274970',6,12,1);
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
  CONSTRAINT `FK6E92FE8DEC3FC03F` FOREIGN KEY (`id_status_produto`) REFERENCES `gs_status_produto` (`id`),
  CONSTRAINT `FK6E92FE8D65A8BD50` FOREIGN KEY (`id_loja`) REFERENCES `gs_lojas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gs_produtos`
--

/*!40000 ALTER TABLE `gs_produtos` DISABLE KEYS */;
INSERT INTO `gs_produtos` (`id`,`descricao`,`nome`,`valor`,`id_loja`,`id_status_produto`) VALUES 
 (1,'Hipnotista, O - Intrinseca','Livro',33.9,1,1),
 (2,'Rafa - Sextante','Livro',25.4,1,1),
 (3,'Não Sou Um Anjo - Novo Conceito','Livro',53.9,1,1),
 (4,'God Of War 3 - PS3','Jogo',189.9,1,1),
 (5,'Mortal Kombat 9 - PS3','Jogo',179.9,1,1),
 (6,'SPC 230NC - Philips','WebCam',69.9,1,1),
 (7,'Mochila Notebook Dermiwil','Mochila',182,1,1),
 (8,'Digital Maxprint','Porta Retrato',209,1,1),
 (9,'Nintendo Wii Black','Console',749.9,1,1),
 (10,'WAG120N Linksys','Modem',279,1,1),
 (11,'Um Homem de Sorte','Livro',19.8,2,1),
 (12,'Investimentos à Prova de Crise','Livro',13,2,1),
 (13,'Samsung GTI6712','Celular',599,2,1),
 (14,'Harry Potter E As Relíquias Da Morte','DVD',44.9,2,1),
 (15,'Navegador Garmin 3760','GPS',896,2,1),
 (16,'LG A520 Preto','Notebook',1699,2,1),
 (17,'Motorola EX119 Branco','Celular',499,2,1),
 (18,'Positivo Intel Atom D425','Notebook',819,3,1),
 (19,'LCD 32\" AOC LC32W053','TV',999,3,1),
 (20,'LCD 32\" Samsung LN32D550 Full HD','TV',1279,3,1),
 (21,'LG P350 Preto GSM','Celular',379,3,1),
 (22,'LG P920 Optimus 3D','Celular',1599,3,1),
 (23,'LED 40\" Samsung Full HD','TV',1999,3,1),
 (24,'HP G42-413BR','Notebook',1179,3,1),
 (25,'Aro 26 18 Marchas Mormaii','Bicicleta',379,4,1),
 (26,'Lava e Seca 9Kg Eletrolux','Lavadoura de Roupas',2099,4,1),
 (27,'Philco 25L PME25 Prata','Microondas',249,4,1),
 (28,'iPhone 4','Celular',1649,4,1),
 (29,'Eletrolux Split 9000 BTU','Condicionador de Ar',819,4,1),
 (30,'Cheese Burger','Sanduíches',6.1,5,1),
 (31,'Cheese Salada','Sanduíches',7.8,5,1),
 (32,'Cheese Calabresa','Sanduíches',8.7,5,1),
 (33,'Strogonoff de Mignon','Pratos Executivos',13.4,5,1),
 (34,'Mignon na Chapa','Pratos Executivos',15.5,5,1),
 (35,'Frango na Chapa','Pratos Executivos',10.9,5,1),
 (36,'Whopper Duplo ','Sanduíches',17.8,6,1);
/*!40000 ALTER TABLE `gs_produtos` ENABLE KEYS */;


--
-- Definition of table `gs_promocao`
--

DROP TABLE IF EXISTS `gs_promocao`;
CREATE TABLE `gs_promocao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `qde_solicitada` bigint(20) DEFAULT '0',
  `qde_voucher` int(11) DEFAULT NULL,
  `id_produto` bigint(20) DEFAULT NULL,
  `id_status_promocao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6F0ED655E1689F16` (`id_produto`),
  KEY `FK6F0ED6559CAFF817` (`id_status_promocao`),
  CONSTRAINT `FK6F0ED6559CAFF817` FOREIGN KEY (`id_status_promocao`) REFERENCES `gs_status_promocao` (`id`),
  CONSTRAINT `FK6F0ED655E1689F16` FOREIGN KEY (`id_produto`) REFERENCES `gs_produtos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gs_promocao`
--

/*!40000 ALTER TABLE `gs_promocao` DISABLE KEYS */;
INSERT INTO `gs_promocao` (`id`,`descricao`,`qde_solicitada`,`qde_voucher`,`id_produto`,`id_status_promocao`) VALUES 
 (1,'5% de desconto na apresentação deste.',1,5,9,1),
 (2,'Ganhe 3 meses de assinatura NET.',0,10,19,1),
 (3,'Suco de Laranja de Brinde.',0,999,34,1),
 (4,'Chip TIM de brinde.',1,99,28,1);
/*!40000 ALTER TABLE `gs_promocao` ENABLE KEYS */;


--
-- Definition of table `gs_status_empresa`
--

DROP TABLE IF EXISTS `gs_status_empresa`;
CREATE TABLE `gs_status_empresa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gs_status_promocao`
--

/*!40000 ALTER TABLE `gs_status_promocao` DISABLE KEYS */;
INSERT INTO `gs_status_promocao` (`id`,`descricao`) VALUES 
 (1,'Ativo'),
 (2,'Inativo'),
 (3,'Bloqueado');
/*!40000 ALTER TABLE `gs_status_promocao` ENABLE KEYS */;


--
-- Definition of table `gs_status_usuario`
--

DROP TABLE IF EXISTS `gs_status_usuario`;
CREATE TABLE `gs_status_usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
-- Definition of table `gs_status_voucher`
--

DROP TABLE IF EXISTS `gs_status_voucher`;
CREATE TABLE `gs_status_voucher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gs_status_voucher`
--

/*!40000 ALTER TABLE `gs_status_voucher` DISABLE KEYS */;
INSERT INTO `gs_status_voucher` (`id`,`descricao`) VALUES 
 (1,'Não resgatado'),
 (2,'Resgatado');
/*!40000 ALTER TABLE `gs_status_voucher` ENABLE KEYS */;


--
-- Definition of table `gs_tipo_usuario`
--

DROP TABLE IF EXISTS `gs_tipo_usuario`;
CREATE TABLE `gs_tipo_usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gs_unidades_federacao`
--

/*!40000 ALTER TABLE `gs_unidades_federacao` DISABLE KEYS */;
INSERT INTO `gs_unidades_federacao` (`id_unidade_federacao`,`descricao`,`sigla`) VALUES 
 (1,'Paraná','PR');
/*!40000 ALTER TABLE `gs_unidades_federacao` ENABLE KEYS */;


--
-- Definition of table `gs_usuarios`
--

DROP TABLE IF EXISTS `gs_usuarios`;
CREATE TABLE `gs_usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(11) DEFAULT NULL,
  `email` varchar(90) DEFAULT NULL,
  `flag` int(11) NOT NULL,
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
  CONSTRAINT `FKB4F060F814E08A9` FOREIGN KEY (`id_status_usuario`) REFERENCES `gs_status_usuario` (`id`),
  CONSTRAINT `FKB4F060F8406D6369` FOREIGN KEY (`id_tipo_usuario`) REFERENCES `gs_tipo_usuario` (`id`),
  CONSTRAINT `FKB4F060F88A16FC07` FOREIGN KEY (`id_empresa_vinculo`) REFERENCES `gs_empresa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gs_usuarios`
--

/*!40000 ALTER TABLE `gs_usuarios` DISABLE KEYS */;
INSERT INTO `gs_usuarios` (`id`,`cpf`,`email`,`flag`,`nome`,`senha`,`telefone`,`id_empresa_vinculo`,`id_status_usuario`,`id_tipo_usuario`) VALUES 
 (1,NULL,'admin',0,'admin','a',NULL,NULL,1,1),
 (2,'04696097960','jonascarig@gmail.com',0,'Jonascarig@gmail.com','qwerty',NULL,NULL,1,3),
 (3,'04413737989','evandrohagy@gmail.com',0,'Evandro','123456',NULL,NULL,1,3);
/*!40000 ALTER TABLE `gs_usuarios` ENABLE KEYS */;


--
-- Definition of table `gs_voucher`
--

DROP TABLE IF EXISTS `gs_voucher`;
CREATE TABLE `gs_voucher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo_voucher` varchar(100) DEFAULT NULL,
  `id_promocao` bigint(20) DEFAULT NULL,
  `id_status_voucher` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9702965BF676E780` (`id_usuario`),
  KEY `FK9702965B4CA2F420` (`id_promocao`),
  KEY `FK9702965B5D753FA9` (`id_status_voucher`),
  CONSTRAINT `FK9702965B5D753FA9` FOREIGN KEY (`id_status_voucher`) REFERENCES `gs_status_voucher` (`id`),
  CONSTRAINT `FK9702965B4CA2F420` FOREIGN KEY (`id_promocao`) REFERENCES `gs_promocao` (`id`),
  CONSTRAINT `FK9702965BF676E780` FOREIGN KEY (`id_usuario`) REFERENCES `gs_usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gs_voucher`
--

/*!40000 ALTER TABLE `gs_voucher` DISABLE KEYS */;
INSERT INTO `gs_voucher` (`id`,`codigo_voucher`,`id_promocao`,`id_status_voucher`,`id_usuario`) VALUES 
 (3,'4228',4,1,2),
 (4,'139',1,2,3);
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
