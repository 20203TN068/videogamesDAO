-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 20-08-2021 a las 11:06:54
-- Versión del servidor: 10.4.20-MariaDB
-- Versión de PHP: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `videogames`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`` PROCEDURE `sp_create` (IN `p_name` VARCHAR(128), IN `p_img` LONGBLOB, IN `p_date_premiere` DATE, IN `p_Category_idCategory` TINYINT(4))  BEGIN
    INSERT INTO games (name, img, datePremiere, Category_idCategory) VALUES (p_name, p_img, p_date_premiere, p_Category_idCategory);
END$$

CREATE DEFINER=`` PROCEDURE `sp_delete` (IN `p_idGames` INT(11))  BEGIN
	UPDATE games AS g SET g.status = 0 WHERE g.idGames = p_idGames;
END$$

CREATE DEFINER=`` PROCEDURE `sp_findById` (IN `p_idGames` INT(11))  BEGIN
	SELECT * FROM games AS g INNER JOIN category AS c ON g.Category_idCategory = c.idCategory WHERE g.idGames = p_idGames;
END$$

CREATE DEFINER=`` PROCEDURE `sp_findGames` ()  BEGIN
	SELECT * FROM games AS g INNER JOIN category AS c ON g.Category_idCategory = c.idCategory;
END$$

CREATE DEFINER=`` PROCEDURE `sp_update` (IN `p_idGames` INT(11), IN `p_name` VARCHAR(128), IN `p_img` LONGBLOB, IN `p_date_premiere` DATE, IN `p_Category_idCategory` TINYINT(4))  BEGIN
	UPDATE games AS g SET g.name = p_name, g.img = p_img, g.date_premiere = p_date_premiere, g.Category_idCategory = p_Category_idCategory
	WHERE g.idGames = p_idGames;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE `category` (
  `idCategory` int(11) NOT NULL,
  `name` varchar(230) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `games`
--

CREATE TABLE `games` (
  `idGames` int(11) NOT NULL,
  `name` varchar(120) NOT NULL,
  `img_game` longblob NOT NULL,
  `datePremiere` date NOT NULL,
  `Category_idCategory` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`idCategory`);

--
-- Indices de la tabla `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`idGames`),
  ADD KEY `fk_games_idCategory` (`Category_idCategory`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `category`
--
ALTER TABLE `category`
  MODIFY `idCategory` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `games`
--
ALTER TABLE `games`
  MODIFY `idGames` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
