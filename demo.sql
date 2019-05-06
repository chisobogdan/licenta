-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Gazdă: 127.0.0.1
-- Timp de generare: mai 06, 2019 la 11:26 AM
-- Versiune server: 10.1.37-MariaDB
-- Versiune PHP: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Bază de date: `demo`
--

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `localitati`
--

CREATE TABLE `localitati` (
  `id` int(11) NOT NULL,
  `nume` varchar(200) NOT NULL,
  `id_sistem` int(11) NOT NULL,
  `id_zona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Eliminarea datelor din tabel `localitati`
--

INSERT INTO `localitati` (`id`, `nume`, `id_sistem`, `id_zona`) VALUES
(1, 'Cluj', 1, 1),
(2, 'Floresti', 1, 1),
(4, 'Mogoseni', 3, 2),
(5, 'Baciu', 2, 3),
(7, 'Baciu', 2, 3),
(8, 'Sibiu', 1, 4);

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `sisteme`
--

CREATE TABLE `sisteme` (
  `id` int(11) NOT NULL,
  `nume` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Eliminarea datelor din tabel `sisteme`
--

INSERT INTO `sisteme` (`id`, `nume`) VALUES
(1, 'ISU'),
(2, 'Tawn-Hall'),
(3, 'Police');

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `zone`
--

CREATE TABLE `zone` (
  `id` int(11) NOT NULL,
  `nume` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Eliminarea datelor din tabel `zone`
--

INSERT INTO `zone` (`id`, `nume`) VALUES
(1, 'ZONA1'),
(2, 'ZONA2'),
(3, 'ZONA3'),
(4, 'ZONA4');

--
-- Indexuri pentru tabele eliminate
--

--
-- Indexuri pentru tabele `localitati`
--
ALTER TABLE `localitati`
  ADD PRIMARY KEY (`id`);

--
-- Indexuri pentru tabele `sisteme`
--
ALTER TABLE `sisteme`
  ADD PRIMARY KEY (`id`);

--
-- Indexuri pentru tabele `zone`
--
ALTER TABLE `zone`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pentru tabele eliminate
--

--
-- AUTO_INCREMENT pentru tabele `localitati`
--
ALTER TABLE `localitati`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pentru tabele `sisteme`
--
ALTER TABLE `sisteme`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pentru tabele `zone`
--
ALTER TABLE `zone`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
