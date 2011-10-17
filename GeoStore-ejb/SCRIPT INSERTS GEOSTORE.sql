-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.58-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


USE geostore;
INSERT INTO `gs_cidades` (`id_cidade`,`descricao`,`id_unidade_federacao`) VALUES 
  (1,'Curitiba',1);
INSERT INTO `gs_cidades` (`id_cidade`,`descricao`,`id_unidade_federacao`) VALUES 
  (2,'Colombo',1);
INSERT INTO `gs_cidades` (`id_cidade`,`descricao`,`id_unidade_federacao`) VALUES 
  (3,'São José dos Pinhais',1);
INSERT INTO `gs_cidades` (`id_cidade`,`descricao`,`id_unidade_federacao`) VALUES 
  (4,'São Paulo',2);
INSERT INTO `gs_cidades` (`id_cidade`,`descricao`,`id_unidade_federacao`) VALUES 
  (5,'Santos',2);
INSERT INTO `gs_cidades` (`id_cidade`,`descricao`,`id_unidade_federacao`) VALUES 
  (6,'Rio de Janeiro',3);
INSERT INTO `gs_status_empresa` (`id`,`descricao`) VALUES 
  (1,'Ativo');
INSERT INTO `gs_status_empresa` (`id`,`descricao`) VALUES 
  (2,'Inativo');
INSERT INTO `gs_status_empresa` (`id`,`descricao`) VALUES 
  (3,'Bloqueado');
INSERT INTO `gs_status_loja` (`id`,`descricao`) VALUES 
  (1,'Ativo');
INSERT INTO `gs_status_loja` (`id`,`descricao`) VALUES 
  (2,'Inativo');
INSERT INTO `gs_status_loja` (`id`,`descricao`) VALUES 
  (3,'Bloqueado');
INSERT INTO `gs_status_produto` (`id`,`descricao`) VALUES 
  (1,'Ativo');
INSERT INTO `gs_status_produto` (`id`,`descricao`) VALUES 
  (2,'Inativo');
INSERT INTO `gs_status_produto` (`id`,`descricao`) VALUES 
  (3,'Bloqueado');
INSERT INTO `gs_status_usuario` (`id`,`descricao`) VALUES 
  (1,'Ativo');
INSERT INTO `gs_status_usuario` (`id`,`descricao`) VALUES 
  (2,'Inativo');
INSERT INTO `gs_status_usuario` (`id`,`descricao`) VALUES 
  (3,'Bloqueado');
INSERT INTO `gs_tipo_usuario` (`id`,`descricao`) VALUES 
  (1,'Administrador');
INSERT INTO `gs_tipo_usuario` (`id`,`descricao`) VALUES 
  (2,'Operador');
INSERT INTO `gs_tipo_usuario` (`id`,`descricao`) VALUES 
  (3,'Final');
INSERT INTO `gs_unidades_federacao` (`id_unidade_federacao`,`descricao`,`sigla`) VALUES 
  (1,'Paraná','PR');
INSERT INTO `gs_unidades_federacao` (`id_unidade_federacao`,`descricao`,`sigla`) VALUES 
  (2,'São Paulo','SP');
INSERT INTO `gs_unidades_federacao` (`id_unidade_federacao`,`descricao`,`sigla`) VALUES 
  (3,'Rio de Janeiro','RJ');
INSERT INTO `gs_usuarios` (`id`,`email`,`senha`,`id_status_usuario`,`id_tipo_usuario`) VALUES 
  (1,'admin','a',1,1);
  
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
