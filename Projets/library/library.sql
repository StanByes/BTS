-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 17 déc. 2024 à 08:20
-- Version du serveur : 11.5.2-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `library`
--

-- --------------------------------------------------------

--
-- Structure de la table `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `name` tinytext NOT NULL,
  `author` tinytext NOT NULL,
  `total` int(11) NOT NULL,
  `published_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `id` int(11) NOT NULL,
  `firstname` varchar(70) NOT NULL,
  `surname` varchar(70) NOT NULL,
  `birthday` date NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `registered_at` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

CREATE TABLE `reservations` (
  `id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `booked_at` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `book_unique` (`name`,`author`) USING HASH;

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `client_unique` (`firstname`,`surname`);

--
-- Index pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `reservation_unique` (`client_id`,`book_id`,`booked_at`),
  ADD KEY `FK_RESERVATIONS1` (`book_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `FK_RESERVATIONS1` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  ADD CONSTRAINT `FK_RESERVATIONS2` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
