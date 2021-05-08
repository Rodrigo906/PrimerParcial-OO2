-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 08-05-2021 a las 12:42:45
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `estacionservicioypzw`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `combustibles`
--

CREATE TABLE IF NOT EXISTS `combustibles` (
  `precioxlitro` double NOT NULL,
  `descripcion` varchar(30) NOT NULL,
  `id_combustible` int(10) NOT NULL AUTO_INCREMENT,
  `descuento_mañana` double DEFAULT NULL,
  `descuento_domingo` double DEFAULT NULL,
  `descuento_sabado` double DEFAULT NULL,
  `min_litros_para_descuento` double DEFAULT NULL,
  PRIMARY KEY (`id_combustible`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE IF NOT EXISTS `ventas` (
  `fecha_venta` datetime NOT NULL,
  `litros_cargados` double NOT NULL,
  `monto_facturado` double NOT NULL,
  `combustible_cargado` varchar(30) NOT NULL,
  `id_venta` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_venta`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
