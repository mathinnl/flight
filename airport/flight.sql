-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Mar 17 Janvier 2017 à 19:22
-- Version du serveur :  5.5.53-0+deb8u1
-- Version de PHP :  5.6.29-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `flight`
--

-- --------------------------------------------------------

--
-- Structure de la table `airports`
--

CREATE TABLE IF NOT EXISTS `airports` (
  `id` int(6) NOT NULL DEFAULT '0',
  `ident` varchar(7) DEFAULT NULL,
  `type` varchar(14) DEFAULT NULL,
  `name` varchar(77) DEFAULT NULL,
  `latitude_deg` decimal(37,15) DEFAULT NULL,
  `longitude_deg` decimal(35,14) DEFAULT NULL,
  `elevation_ft` varchar(5) DEFAULT NULL,
  `continent` varchar(2) DEFAULT NULL,
  `iso_country` varchar(2) DEFAULT NULL,
  `iso_region` varchar(7) DEFAULT NULL,
  `municipality` varchar(60) DEFAULT NULL,
  `scheduled_service` varchar(3) DEFAULT NULL,
  `gps_code` varchar(4) DEFAULT NULL,
  `iata_code` varchar(3) DEFAULT NULL,
  `local_code` varchar(4) DEFAULT NULL,
  `home_link` varchar(128) DEFAULT NULL,
  `wikipedia_link` varchar(128) DEFAULT NULL,
  `keywords` varchar(228) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `countries`
--

CREATE TABLE IF NOT EXISTS `countries` (
  `id` int(6) NOT NULL DEFAULT '0',
  `code` varchar(2) DEFAULT NULL,
  `name` varchar(44) DEFAULT NULL,
  `continent` varchar(2) DEFAULT NULL,
  `wikipedia_link` varchar(73) DEFAULT NULL,
  `keywords` varchar(34) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `runways`
--

CREATE TABLE IF NOT EXISTS `runways` (
  `id` int(6) NOT NULL DEFAULT '0',
  `airport_ref` int(6) DEFAULT NULL,
  `airport_ident` varchar(7) DEFAULT NULL,
  `length_ft` varchar(6) DEFAULT NULL,
  `width_ft` varchar(4) DEFAULT NULL,
  `surface` varchar(59) DEFAULT NULL,
  `lighted` int(1) DEFAULT NULL,
  `closed` int(1) DEFAULT NULL,
  `le_ident` varchar(5) DEFAULT NULL,
  `le_latitude_deg` varchar(8) DEFAULT NULL,
  `le_longitude_deg` varchar(8) DEFAULT NULL,
  `le_elevation_ft` varchar(8) DEFAULT NULL,
  `le_heading_degT` varchar(7) DEFAULT NULL,
  `le_displaced_threshold_ft` varchar(8) DEFAULT NULL,
  `he_ident` varchar(6) DEFAULT NULL,
  `he_latitude_deg` varchar(8) DEFAULT NULL,
  `he_longitude_deg` varchar(8) DEFAULT NULL,
  `he_elevation_ft` varchar(5) DEFAULT NULL,
  `he_heading_degT` varchar(5) DEFAULT NULL,
  `he_displaced_threshold_ft` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `airports`
--
ALTER TABLE `airports`
 ADD PRIMARY KEY (`id`), ADD KEY `iso_country` (`iso_country`), ADD KEY `ident` (`ident`);

--
-- Index pour la table `countries`
--
ALTER TABLE `countries`
 ADD PRIMARY KEY (`id`);

--
-- Index pour la table `runways`
--
ALTER TABLE `runways`
 ADD PRIMARY KEY (`id`), ADD KEY `airport_ident` (`airport_ident`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
