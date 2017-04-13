-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 13, 2017 at 02:45 AM
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
  `NOM` varchar(255) DEFAULT NULL,
  `NUMBER` int(11) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `STREET` varchar(255) DEFAULT NULL,
  `TEL` varchar(255) DEFAULT NULL,
  `TELFIX` varchar(255) DEFAULT NULL,
  `QUARTIER_ID` bigint(20) DEFAULT NULL,
  `USER_LOGIN` varchar(255) DEFAULT NULL,
  `VILLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ADRESS_USER_LOGIN` (`USER_LOGIN`),
  KEY `FK_ADRESS_QUARTIER_ID` (`QUARTIER_ID`),
  KEY `FK_ADRESS_VILLE_ID` (`VILLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE IF NOT EXISTS `commande` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATECMD` longblob,
  `TOTAL` double DEFAULT NULL,
  `RESTAURANT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
  KEY `FK_COMMANDEITEM_COMMANDE_ID` (`COMMANDE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
  `USER_LOGIN` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DEVICE_USER_LOGIN` (`USER_LOGIN`)
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
  KEY `FK_PLATE_CUISINE_ID` (`CUISINE_ID`)
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
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=166 ;

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
(165, 1, 8, 5, 123, 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

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
  PRIMARY KEY (`ID`),
  KEY `FK_RESTAURANT_QUARTIER_ID` (`QUARTIER_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`ID`, `NOM`, `QUARTIER_ID`, `ACCEPTED`) VALUES
(1, 'Mc Donald''s', 6, 0),
(2, 'Fayrouz', 6, 0),
(3, 'KFC ', 6, 0),
(4, 'Taiba', 8, 0),
(5, 'Hani', 5, 0);

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
('SEQ_GEN', '200');

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
(3, 0, NULL, 1, NULL, 166),
(4, 0, NULL, 2, NULL, 166),
(5, 0, NULL, 1, NULL, 165);

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
  `LOGIN` varchar(255) NOT NULL,
  `ADMIIN` tinyint(1) DEFAULT '0',
  `BLOCKED` int(11) DEFAULT NULL,
  `DATENAISSANCE` longblob,
  `EMAIL` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `MDPCHANGED` tinyint(1) DEFAULT '0',
  `NBRCNX` int(11) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `TEL` varchar(255) DEFAULT NULL,
  `TENTATIVEREST` int(11) DEFAULT NULL,
  PRIMARY KEY (`LOGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`LOGIN`, `ADMIIN`, `BLOCKED`, `DATENAISSANCE`, `EMAIL`, `GENDER`, `MDPCHANGED`, `NBRCNX`, `NOM`, `PASSWORD`, `PRENOM`, `TEL`, `TENTATIVEREST`) VALUES
('abed', 0, 0, NULL, 'abed@dlks', 'M', 0, 0, 'abed', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'afr', '2136549', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_commande`
--

CREATE TABLE IF NOT EXISTS `user_commande` (
  `commandes_ID` bigint(20) NOT NULL,
  `users_LOGIN` varchar(255) NOT NULL,
  PRIMARY KEY (`commandes_ID`,`users_LOGIN`),
  KEY `FK_USER_COMMANDE_users_LOGIN` (`users_LOGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ville`
--

CREATE TABLE IF NOT EXISTS `ville` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

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
  ADD CONSTRAINT `FK_ADRESS_QUARTIER_ID` FOREIGN KEY (`QUARTIER_ID`) REFERENCES `quartier` (`ID`),
  ADD CONSTRAINT `FK_ADRESS_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`),
  ADD CONSTRAINT `FK_ADRESS_VILLE_ID` FOREIGN KEY (`VILLE_ID`) REFERENCES `ville` (`ID`);

--
-- Constraints for table `commandeitem`
--
ALTER TABLE `commandeitem`
  ADD CONSTRAINT `FK_COMMANDEITEM_COMMANDE_ID` FOREIGN KEY (`COMMANDE_ID`) REFERENCES `commande` (`ID`),
  ADD CONSTRAINT `FK_COMMANDEITEM_PLATE_ID` FOREIGN KEY (`PLATE_ID`) REFERENCES `plate` (`ID`);

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
  ADD CONSTRAINT `FK_DEVICE_USER_LOGIN` FOREIGN KEY (`USER_LOGIN`) REFERENCES `user` (`LOGIN`);

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
  ADD CONSTRAINT `FK_PLATE_CUISINE_ID` FOREIGN KEY (`CUISINE_ID`) REFERENCES `cuisine` (`ID`);

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

--
-- Constraints for table `supplementselected`
--
ALTER TABLE `supplementselected`
  ADD CONSTRAINT `FK_SUPPLEMENTSELECTED_COMMANDEITEM_ID` FOREIGN KEY (`COMMANDEITEM_ID`) REFERENCES `commandeitem` (`ID`),
  ADD CONSTRAINT `FK_SUPPLEMENTSELECTED_SUPPLEMENTPLAT_ID` FOREIGN KEY (`SUPPLEMENTPLAT_ID`) REFERENCES `supplementplat` (`ID`);

--
-- Constraints for table `user_commande`
--
ALTER TABLE `user_commande`
  ADD CONSTRAINT `FK_USER_COMMANDE_commandes_ID` FOREIGN KEY (`commandes_ID`) REFERENCES `commande` (`ID`),
  ADD CONSTRAINT `FK_USER_COMMANDE_users_LOGIN` FOREIGN KEY (`users_LOGIN`) REFERENCES `user` (`LOGIN`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
