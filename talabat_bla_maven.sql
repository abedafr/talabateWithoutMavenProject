-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 09, 2017 at 01:08 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `talabat_bla_maven`
--

-- --------------------------------------------------------

--
-- Table structure for table `adress`
--

CREATE TABLE IF NOT EXISTS `adress` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BUILDING` varchar(255) DEFAULT NULL,
  `FLOOR` int(11) DEFAULT NULL,
  `NUMBER` int(11) DEFAULT NULL,
  `STREET` varchar(255) DEFAULT NULL,
  `QUARTIER_ID` bigint(20) DEFAULT NULL,
  `USER_EMAIL` varchar(255) DEFAULT NULL,
  `VILLE_ID` bigint(20) DEFAULT NULL,
  `COMMANDE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ADRESS_USER_EMAIL` (`USER_EMAIL`),
  KEY `FK_ADRESS_QUARTIER_ID` (`QUARTIER_ID`),
  KEY `FK_ADRESS_VILLE_ID` (`VILLE_ID`),
  KEY `FK_ADRESS_COMMANDE_ID` (`COMMANDE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `adress`
--

INSERT INTO `adress` (`ID`, `BUILDING`, `FLOOR`, `NUMBER`, `STREET`, `QUARTIER_ID`, `USER_EMAIL`, `VILLE_ID`, `COMMANDE_ID`) VALUES
(2, '3123', 0, 3, '3', 6, '321', 2, NULL),
(3, '25', 3213, 321, '3513', 6, 'abed@afr', 2, 6),
(4, '321', 3, 2, '654', 6, '65', 2, 7),
(5, '3213', 321, 321, '321', 6, '321', 2, 8),
(6, '44', 1, 4, 'iuuif', 6, 'hghg@gyuge.com', 2, 10),
(7, '21', 1, 2, '3123', 6, '@aabk', 2, 11),
(8, '12', 3, 2, '32', 6, 'client', 2, 12),
(9, '12', 2, 23, '33', 6, 'client', 2, 13),
(10, '2387787', 0, 0, '874', 6, 'hhh@hh', 2, 14),
(11, 'uiiu', 0, 0, '54', 6, 'hiui', 2, 15),
(12, '2', 2, 45, '55', 6, 'sefsef@hjl.cm', 2, 16);

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE IF NOT EXISTS `commande` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATECMD` longblob,
  `TOTAL` double DEFAULT NULL,
  `RESTAURANT_ID` bigint(20) DEFAULT NULL,
  `USER_EMAIL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_COMMANDE_RESTAURANT_ID` (`RESTAURANT_ID`),
  KEY `FK_COMMANDE_USER_EMAIL` (`USER_EMAIL`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `commande`
--

INSERT INTO `commande` (`ID`, `DATECMD`, `TOTAL`, `RESTAURANT_ID`, `USER_EMAIL`) VALUES
(1, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e105041026300a037a0078, 164, 1, NULL),
(2, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e1050412072605b8d80078, 223, 1, NULL),
(5, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e10505010424058b114078, 141, 1, '321'),
(6, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e10505020a2e28a67e8078, 114, 1, 'abed@afr'),
(7, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e105050d0b033a9763c078, 499, 1, '65'),
(8, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e105050f3a3b255f42c078, 191, 1, '321'),
(10, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e1050512360c2ba16f0078, 258, 1, 'hghg@gyuge.com'),
(11, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e105060d16060d787c8078, 171, 1, '@aabk'),
(12, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e105070211091f4add4078, 134, 1, 'client'),
(13, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e105070322390dd40a0078, 87, 1, 'client'),
(14, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e10507072a0c2a9e08c078, 241, 1, 'hhh@hh'),
(15, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e105070b25271e56b94078, 273.5, 1, 'hiui'),
(16, 0xaced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770e05000007e105090b1c0d2ebae40078, 142, 1, 'sefsef@hjl.cm');

-- --------------------------------------------------------

--
-- Table structure for table `commandeitem`
--

CREATE TABLE IF NOT EXISTS `commandeitem` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PRIXTOTALITEM` double DEFAULT NULL,
  `QTE` int(11) DEFAULT NULL,
  `COMMANDE_ID` bigint(20) DEFAULT NULL,
  `PLATE_ID` bigint(20) DEFAULT NULL,
  `TOTALSUPPLEMENTS` double DEFAULT NULL,
  `PLATMENU_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_COMMANDEITEM_PLATE_ID` (`PLATE_ID`),
  KEY `FK_COMMANDEITEM_COMMANDE_ID` (`COMMANDE_ID`),
  KEY `FK_COMMANDEITEM_PLATMENU_ID` (`PLATMENU_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=804 ;

--
-- Dumping data for table `commandeitem`
--

INSERT INTO `commandeitem` (`ID`, `PRIXTOTALITEM`, `QTE`, `COMMANDE_ID`, `PLATE_ID`, `TOTALSUPPLEMENTS`, `PLATMENU_ID`) VALUES
(201, 54, 2, 1, NULL, NULL, 163),
(202, 60, 2, 1, NULL, NULL, 161),
(203, 50, 2, 1, NULL, NULL, 164),
(251, 90, 3, 2, NULL, NULL, 161),
(252, 108, 4, 2, NULL, NULL, 163),
(253, 25, 1, 2, NULL, NULL, 164),
(301, 60, 2, 5, NULL, NULL, 161),
(302, 81, 3, 5, NULL, NULL, 163),
(351, 54, 2, 6, NULL, NULL, 163),
(352, 60, 2, 6, NULL, NULL, 161),
(401, 499, 4, 7, NULL, 3.5, 165),
(451, 60, 2, 8, NULL, NULL, 161),
(452, 81, 3, 8, NULL, NULL, 163),
(453, 50, 2, 8, NULL, NULL, 164),
(501, 150, 5, 10, NULL, NULL, 161),
(502, 108, 4, 10, NULL, NULL, 163),
(551, 90, 3, 11, NULL, NULL, 161),
(552, 81, 3, 11, NULL, NULL, 163),
(601, 50, 2, 12, NULL, NULL, 164),
(602, 54, 2, 12, NULL, NULL, 163),
(603, 30, 1, 12, NULL, NULL, 161),
(651, 60, 2, 13, NULL, NULL, 161),
(652, 27, 1, 13, NULL, NULL, 163),
(701, 216, 8, 14, NULL, NULL, 163),
(702, 25, 1, 14, NULL, NULL, 164),
(751, 150, 5, 15, NULL, NULL, 161),
(752, 123.5, 1, 15, NULL, 3.5, 165),
(801, 90, 3, 16, NULL, NULL, 161),
(802, 27, 1, 16, NULL, NULL, 163),
(803, 25, 1, 16, NULL, NULL, 164);

-- --------------------------------------------------------

--
-- Table structure for table `commandeitem_supplementplat`
--

CREATE TABLE IF NOT EXISTS `commandeitem_supplementplat` (
  `CommandeItem_ID` bigint(20) NOT NULL,
  `supplementPlats_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`CommandeItem_ID`,`supplementPlats_ID`),
  KEY `FK_COMMANDEITEM_SUPPLEMENTPLAT_supplementPlats_ID` (`supplementPlats_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commandeitem_supplementplat`
--

INSERT INTO `commandeitem_supplementplat` (`CommandeItem_ID`, `supplementPlats_ID`) VALUES
(401, 5),
(752, 5);

-- --------------------------------------------------------

--
-- Table structure for table `cuisine`
--

CREATE TABLE IF NOT EXISTS `cuisine` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `cuisine`
--

INSERT INTO `cuisine` (`ID`, `NOM`) VALUES
(1, 'Arabe'),
(2, 'Americaine'),
(3, 'Italienne'),
(4, 'Japonaise'),
(5, 'Marocaine');

-- --------------------------------------------------------

--
-- Table structure for table `cuisine_menu`
--

CREATE TABLE IF NOT EXISTS `cuisine_menu` (
  `Cuisine_ID` bigint(20) NOT NULL,
  `menus_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`Cuisine_ID`,`menus_ID`),
  KEY `FK_CUISINE_MENU_menus_ID` (`menus_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cuisine_menu`
--

INSERT INTO `cuisine_menu` (`Cuisine_ID`, `menus_ID`) VALUES
(2, 1),
(5, 1),
(1, 2),
(4, 2),
(5, 2),
(2, 3),
(5, 3),
(5, 4);

-- --------------------------------------------------------

--
-- Table structure for table `device`
--

CREATE TABLE IF NOT EXISTS `device` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADRESSEIP` varchar(255) DEFAULT NULL,
  `ADRESSEMAC` varchar(255) DEFAULT NULL,
  `BROWSER` varchar(255) DEFAULT NULL,
  `DEVICECATEGORIE` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `OPERATINGSYSTEM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `USER_EMAIL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DEVICE_USER_EMAIL` (`USER_EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE IF NOT EXISTS `menu` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `RESTAURANT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_MENU_RESTAURANT_ID` (`RESTAURANT_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`ID`, `RESTAURANT_ID`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- --------------------------------------------------------

--
-- Table structure for table `menu_cuisine`
--

CREATE TABLE IF NOT EXISTS `menu_cuisine` (
  `Menu_ID` bigint(20) NOT NULL,
  `cuisines_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`Menu_ID`,`cuisines_ID`),
  KEY `FK_MENU_CUISINE_cuisines_ID` (`cuisines_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu_cuisine`
--

INSERT INTO `menu_cuisine` (`Menu_ID`, `cuisines_ID`) VALUES
(2, 1),
(1, 2),
(3, 2),
(2, 4),
(1, 5),
(2, 5),
(3, 5),
(4, 5);

-- --------------------------------------------------------

--
-- Table structure for table `plate`
--

CREATE TABLE IF NOT EXISTS `plate` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  `PRIX` double DEFAULT NULL,
  `CUISINE_ID` bigint(20) DEFAULT NULL,
  `MENU_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PLATE_CUISINE_ID` (`CUISINE_ID`),
  KEY `FK_PLATE_MENU_ID` (`MENU_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Dumping data for table `plate`
--

INSERT INTO `plate` (`ID`, `NOM`, `PRIX`, `CUISINE_ID`, `MENU_ID`) VALUES
(1, 'Cheese Burger', 30, 2, 1),
(2, 'Hamburger', 25, 2, 1),
(3, 'Couscous', 30, 5, 3),
(4, 'Sushi', 150, 4, 2),
(5, 'Tako Yaki', 125, 4, 2),
(6, 'Yakisoba', 170, 4, 2),
(7, 'Tajine poulet', 40, 5, 4),
(8, 'Tajine Viande', 40, 5, 4),
(9, 'Suki Yaki', 100, 4, 2),
(10, 'Chawarma', 20, 1, 2),
(11, 'Ramen', 30, 4, 2),
(12, 'Japanese Curry', 80, 4, 2),
(13, 'Sashimi', 160, 4, 2),
(14, 'Onigiri', 20, 4, 2),
(15, 'Chahan', 70, 4, 2),
(16, 'Udon', 30, 4, 2),
(17, 'Omuraisu', 30, 4, 2),
(18, 'Bocadios', 10, 5, 1),
(19, 'Chicken Burger', 35, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `platmenu`
--

CREATE TABLE IF NOT EXISTS `platmenu` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MENU_ID` bigint(20) DEFAULT NULL,
  `PLATE_ID` bigint(20) DEFAULT NULL,
  `CUISINE_ID` bigint(20) DEFAULT NULL,
  `PRIX` double DEFAULT NULL,
  `Costume` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PLATMENU_CUISINE_ID` (`CUISINE_ID`),
  KEY `FK_PLATMENU_MENU_ID` (`MENU_ID`),
  KEY `FK_PLATMENU_PLATE_ID` (`PLATE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=167 ;

--
-- Dumping data for table `platmenu`
--

INSERT INTO `platmenu` (`ID`, `MENU_ID`, `PLATE_ID`, `CUISINE_ID`, `PRIX`, `Costume`) VALUES
(1, 2, 5, 4, 120, 0),
(3, 2, 4, 4, 155, 0),
(154, 2, 6, 4, 120, 0),
(155, 2, 9, 4, 99, 0),
(156, 2, 11, 4, 20, 0),
(157, 2, 13, 4, 175, 0),
(158, 2, 14, 4, 15, 0),
(159, 2, 15, 4, 60, 0),
(160, 2, 17, 4, 25, 0),
(161, 1, 2, 2, 30, 0),
(162, 3, 2, 2, 25, 0),
(163, 1, 1, 2, 27, 0),
(164, 1, 1, 2, 25, 0),
(165, 1, 8, 5, 120, 1);

-- --------------------------------------------------------

--
-- Table structure for table `quartier`
--

CREATE TABLE IF NOT EXISTS `quartier` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  `VILLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_QUARTIER_VILLE_ID` (`VILLE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `quartier`
--

INSERT INTO `quartier` (`ID`, `NOM`, `VILLE_ID`) VALUES
(1, 'Dcheira', 1),
(2, 'Founty', 1),
(3, 'Talberjt', 1),
(4, 'Marina', 1),
(5, 'Daoudiate', 2),
(6, 'Gueliz', 2),
(7, 'Massira', 2),
(8, 'M''hamid', 2),
(9, 'Sokoma', 2),
(10, 'Medina', 2),
(11, 'Inezgane', 1);

-- --------------------------------------------------------

--
-- Table structure for table `restaurant`
--

CREATE TABLE IF NOT EXISTS `restaurant` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  `QUARTIER_ID` bigint(20) DEFAULT NULL,
  `ACCEPTED` tinyint(1) DEFAULT NULL,
  `ADMINRESTAU_EMAIL` varchar(255) DEFAULT NULL,
  `LAT` double DEFAULT NULL,
  `LNG` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_RESTAURANT_QUARTIER_ID` (`QUARTIER_ID`),
  KEY `FK_RESTAURANT_ADMINRESTAU_EMAIL` (`ADMINRESTAU_EMAIL`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`ID`, `NOM`, `QUARTIER_ID`, `ACCEPTED`, `ADMINRESTAU_EMAIL`, `LAT`, `LNG`) VALUES
(1, 'Mc Donald''s', 6, 1, 'restAdmin', NULL, NULL),
(2, 'Fayrouz', 6, 1, NULL, NULL, NULL),
(3, 'KFC ', 6, 1, NULL, NULL, NULL),
(4, 'Taiba', 8, 1, NULL, NULL, NULL),
(5, 'Hani', 5, 1, NULL, NULL, NULL),
(6, 'ma7laba', NULL, 0, 'abkjkjl@kj', NULL, NULL),
(10, 'ruibtyu', NULL, 0, 'ponyh@;nu', NULL, NULL),
(11, 'ertyuio', NULL, 0, 'rtyuio', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `sequence`
--

CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '850');

-- --------------------------------------------------------

--
-- Table structure for table `supplement`
--

CREATE TABLE IF NOT EXISTS `supplement` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DEFAULTPRICE` double DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `supplement`
--

INSERT INTO `supplement` (`ID`, `DEFAULTPRICE`, `NOM`) VALUES
(1, 3, 'Frites'),
(2, 2, 'Olives'),
(3, 1, 'Mayonnaise'),
(4, 1, 'Ketchup');

-- --------------------------------------------------------

--
-- Table structure for table `supplementplat`
--

CREATE TABLE IF NOT EXISTS `supplementplat` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDITION` double DEFAULT NULL,
  `PLATE_ID` bigint(20) DEFAULT NULL,
  `SUPPLEMENT_ID` bigint(20) DEFAULT NULL,
  `NEWPRICE` double DEFAULT NULL,
  `PLATMENU_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SUPPLEMENTPLAT_SUPPLEMENT_ID` (`SUPPLEMENT_ID`),
  KEY `FK_SUPPLEMENTPLAT_PLATE_ID` (`PLATE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `supplementplat`
--

INSERT INTO `supplementplat` (`ID`, `ADDITION`, `PLATE_ID`, `SUPPLEMENT_ID`, `NEWPRICE`, `PLATMENU_ID`) VALUES
(3, 0, NULL, 1, 3, 166),
(4, 0, NULL, 2, 2.5, 166),
(5, 0, NULL, 1, 3.5, 165);

-- --------------------------------------------------------

--
-- Table structure for table `supplementselected`
--

CREATE TABLE IF NOT EXISTS `supplementselected` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `COMMANDEITEM_ID` bigint(20) DEFAULT NULL,
  `SUPPLEMENTPLAT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SUPPLEMENTSELECTED_COMMANDEITEM_ID` (`COMMANDEITEM_ID`),
  KEY `FK_SUPPLEMENTSELECTED_SUPPLEMENTPLAT_ID` (`SUPPLEMENTPLAT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `EMAIL` varchar(255) NOT NULL,
  `ISADMIN` int(11) DEFAULT '0',
  `BLOCKED` tinyint(1) DEFAULT NULL,
  `DATENAISSANCE` longblob,
  `GENDER` varchar(255) DEFAULT NULL,
  `MDPCHANGED` tinyint(1) DEFAULT '0',
  `NBRCNX` int(11) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `TEL` varchar(255) DEFAULT NULL,
  `TENTATIVEREST` int(11) DEFAULT NULL,
  `RESTAURANT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`EMAIL`, `ISADMIN`, `BLOCKED`, `DATENAISSANCE`, `GENDER`, `MDPCHANGED`, `NBRCNX`, `NOM`, `PASSWORD`, `PRENOM`, `TEL`, `TENTATIVEREST`, `RESTAURANT_ID`) VALUES
('1111', 0, 0, NULL, 'M', 0, 0, '11', '4fc82b26aecb47d2868c4efbe3581732a3e7cbcc6c2efb32062c08170a05eeb8', '11', '11', 0, NULL),
('23', 0, 0, NULL, 'M', 0, 0, '', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', '', '', 0, NULL),
('23232', 0, 0, NULL, 'M', 0, 0, '', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', '', '', 0, NULL),
('321', 0, 0, NULL, NULL, 0, 0, '1321', NULL, '3213', '321', 0, NULL),
('65', 0, 0, NULL, NULL, 0, 0, '321', NULL, '654', '6549', 0, NULL),
('@aabk', 0, 0, NULL, NULL, 0, 0, 'client', NULL, 'clint', '21321', 0, NULL),
('abed', 2, 0, NULL, 'M', 0, 0, 'abed', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'afr', '2136549', 0, NULL),
('abed2', 0, 0, NULL, 'M', 0, 3, 'abed', '6ebfe7cb61ea44af9b44a1c1c51e15db75349ddea16cae87cd8f05ae40c0c76b', 'afr', '123456789', 0, NULL),
('abed@afr', 0, 0, NULL, 'M', 0, 0, 'abed ', '16ba98cf66cf7253aea999eaa6a5fabdc0c3aa69de11a844b474da6ca83ddb2a', 'afr', '21654969879', 0, NULL),
('abkjkjl@kj', 1, 1, NULL, NULL, 0, 0, 'abed', '16ba98cf66cf7253aea999eaa6a5fabdc0c3aa69de11a844b474da6ca83ddb2a', 'afriad', '9/7846513', 0, NULL),
('admin', 2, 1, NULL, 'M', 0, 3, 'adminNom', '16ba98cf66cf7253aea999eaa6a5fabdc0c3aa69de11a844b474da6ca83ddb2a', 'adminPrenom', '123456789', 0, NULL),
('client', 0, 0, NULL, NULL, 0, 0, NULL, '948fe603f61dc036b5c596dc09fe3ce3f3d30dc90f024c85f3c82db2ccab679d', NULL, '02135468', 0, NULL),
('hghg@gyuge.com', 0, 0, NULL, NULL, 0, 0, 'ali', NULL, 'bassou', '544985', 0, NULL),
('hhh@hh', 0, 0, NULL, NULL, 0, 0, '15', NULL, '351', '353', 0, NULL),
('hiui', 0, 0, NULL, NULL, 0, 0, 'yug', NULL, 'utui', 'iui', 0, NULL),
('larbi@', 0, 0, NULL, NULL, 0, 0, 'larbi', '123', 'id', '123456789', 0, NULL),
('ponyh@;nu', 1, 1, NULL, NULL, 0, 0, 'cvbn', NULL, 'erty', '13546987065', 0, NULL),
('restAdmin', 1, 0, NULL, 'M', 0, 0, 'restAdmin', '1779d653a3310f05ffc05bee85858667aa580909acf6926592bebfe4307358b3', 'restAdmin', 'restAdmin', 0, NULL),
('rtyuio', 1, 1, NULL, NULL, 0, 0, 'rtyui', NULL, 'rtyui', '653165', 0, NULL),
('SEFsef12364@gmail.com', 0, 0, NULL, 'M', 0, 0, 'Souhail', 'aee634bc2bff88a128bb080299088864883716dd70f58bb1306faa8970122acc', 'Elfahimi', '123456789', 0, NULL),
('sefsef@hjl.cm', 0, 0, NULL, NULL, 0, 0, 'souhail', NULL, 'elfahimi', '1346547', 0, NULL),
('younesss.bfs@gmail.com', 0, 0, NULL, 'M', 0, 0, 'youness', '541ac1c4c88e3ea8d80b2569424a0b4edc27f44762cae16047b97244ae20f2a5', 'boufous', '04653598', 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ville`
--

CREATE TABLE IF NOT EXISTS `ville` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `ville`
--

INSERT INTO `ville` (`ID`, `NOM`) VALUES
(1, 'Agadir'),
(2, 'Marrakech');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `adress`
--
ALTER TABLE `adress`
  ADD CONSTRAINT `FK_ADRESS_COMMANDE_ID` FOREIGN KEY (`COMMANDE_ID`) REFERENCES `commande` (`ID`),
  ADD CONSTRAINT `FK_ADRESS_QUARTIER_ID` FOREIGN KEY (`QUARTIER_ID`) REFERENCES `quartier` (`ID`),
  ADD CONSTRAINT `FK_ADRESS_USER_LOGIN` FOREIGN KEY (`USER_EMAIL`) REFERENCES `user` (`EMAIL`),
  ADD CONSTRAINT `FK_ADRESS_VILLE_ID` FOREIGN KEY (`VILLE_ID`) REFERENCES `ville` (`ID`);

--
-- Constraints for table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_COMMANDE_USER_EMAIL` FOREIGN KEY (`USER_EMAIL`) REFERENCES `user` (`EMAIL`),
  ADD CONSTRAINT `FK_COMMANDE_RESTAURANT_ID` FOREIGN KEY (`RESTAURANT_ID`) REFERENCES `restaurant` (`ID`);

--
-- Constraints for table `commandeitem`
--
ALTER TABLE `commandeitem`
  ADD CONSTRAINT `FK_COMMANDEITEM_COMMANDE_ID` FOREIGN KEY (`COMMANDE_ID`) REFERENCES `commande` (`ID`),
  ADD CONSTRAINT `FK_COMMANDEITEM_PLATMENU_ID` FOREIGN KEY (`PLATMENU_ID`) REFERENCES `platmenu` (`ID`);

--
-- Constraints for table `commandeitem_supplementplat`
--
ALTER TABLE `commandeitem_supplementplat`
  ADD CONSTRAINT `FK_COMMANDEITEM_SUPPLEMENTPLAT_CommandeItem_ID` FOREIGN KEY (`CommandeItem_ID`) REFERENCES `commandeitem` (`ID`),
  ADD CONSTRAINT `FK_COMMANDEITEM_SUPPLEMENTPLAT_supplementPlats_ID` FOREIGN KEY (`supplementPlats_ID`) REFERENCES `supplementplat` (`ID`);

--
-- Constraints for table `cuisine_menu`
--
ALTER TABLE `cuisine_menu`
  ADD CONSTRAINT `FK_CUISINE_MENU_Cuisine_ID` FOREIGN KEY (`Cuisine_ID`) REFERENCES `cuisine` (`ID`),
  ADD CONSTRAINT `FK_CUISINE_MENU_menus_ID` FOREIGN KEY (`menus_ID`) REFERENCES `menu` (`ID`);

--
-- Constraints for table `device`
--
ALTER TABLE `device`
  ADD CONSTRAINT `FK_DEVICE_USER_EMAIL` FOREIGN KEY (`USER_EMAIL`) REFERENCES `user` (`EMAIL`);

--
-- Constraints for table `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `FK_MENU_RESTAURANT_ID` FOREIGN KEY (`RESTAURANT_ID`) REFERENCES `restaurant` (`ID`);

--
-- Constraints for table `menu_cuisine`
--
ALTER TABLE `menu_cuisine`
  ADD CONSTRAINT `FK_MENU_CUISINE_cuisines_ID` FOREIGN KEY (`cuisines_ID`) REFERENCES `cuisine` (`ID`),
  ADD CONSTRAINT `FK_MENU_CUISINE_Menu_ID` FOREIGN KEY (`Menu_ID`) REFERENCES `menu` (`ID`);

--
-- Constraints for table `plate`
--
ALTER TABLE `plate`
  ADD CONSTRAINT `FK_PLATE_CUISINE_ID` FOREIGN KEY (`CUISINE_ID`) REFERENCES `cuisine` (`ID`),
  ADD CONSTRAINT `FK_PLATE_MENU_ID` FOREIGN KEY (`MENU_ID`) REFERENCES `menu` (`ID`);

--
-- Constraints for table `platmenu`
--
ALTER TABLE `platmenu`
  ADD CONSTRAINT `FK_PLATMENU_CUISINE_ID` FOREIGN KEY (`CUISINE_ID`) REFERENCES `cuisine` (`ID`),
  ADD CONSTRAINT `FK_PLATMENU_MENU_ID` FOREIGN KEY (`MENU_ID`) REFERENCES `menu` (`ID`),
  ADD CONSTRAINT `FK_PLATMENU_PLATE_ID` FOREIGN KEY (`PLATE_ID`) REFERENCES `plate` (`ID`);

--
-- Constraints for table `quartier`
--
ALTER TABLE `quartier`
  ADD CONSTRAINT `FK_QUARTIER_VILLE_ID` FOREIGN KEY (`VILLE_ID`) REFERENCES `ville` (`ID`);

--
-- Constraints for table `restaurant`
--
ALTER TABLE `restaurant`
  ADD CONSTRAINT `FK_RESTAURANT_QUARTIER_ID` FOREIGN KEY (`QUARTIER_ID`) REFERENCES `quartier` (`ID`);

--
-- Constraints for table `supplementplat`
--
ALTER TABLE `supplementplat`
  ADD CONSTRAINT `FK_SUPPLEMENTPLAT_PLATE_ID` FOREIGN KEY (`PLATE_ID`) REFERENCES `plate` (`ID`),
  ADD CONSTRAINT `FK_SUPPLEMENTPLAT_SUPPLEMENT_ID` FOREIGN KEY (`SUPPLEMENT_ID`) REFERENCES `supplement` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
