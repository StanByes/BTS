-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 18 jan. 2025 à 16:34
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
-- Base de données : `ap1`
--

-- --------------------------------------------------------

--
-- Structure de la table `internships`
--

CREATE TABLE `internships` (
  `intern_id` int(10) NOT NULL,
  `supervisor_id` int(11) NOT NULL,
  `start_at` date NOT NULL,
  `end_at` date NOT NULL,
  `day_start_at` time DEFAULT NULL,
  `day_end_at` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reports`
--

CREATE TABLE `reports` (
  `id` int(10) NOT NULL,
  `creator_id` int(10) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `date` date NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reset_password_queries`
--

CREATE TABLE `reset_password_queries` (
  `id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `code` varchar(20) NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `unavailable_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `display_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `login` varchar(100) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `password` tinytext NOT NULL,
  `role_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `internships`
--
ALTER TABLE `internships`
  ADD UNIQUE KEY `intern_id` (`intern_id`),
  ADD KEY `FK_INTERNSHIP_2` (`supervisor_id`);

--
-- Index pour la table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_REPORTS_1` (`creator_id`);

--
-- Index pour la table `reset_password_queries`
--
ALTER TABLE `reset_password_queries`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_RESET_PASSWORD_1` (`user_id`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQUE` (`firstname`,`surname`) USING BTREE,
  ADD KEY `FK_USER_1` (`role_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `reports`
--
ALTER TABLE `reports`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `reset_password_queries`
--
ALTER TABLE `reset_password_queries`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `internships`
--
ALTER TABLE `internships`
  ADD CONSTRAINT `FK_INTERNSHIP_1` FOREIGN KEY (`intern_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK_INTERNSHIP_2` FOREIGN KEY (`supervisor_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `reports`
--
ALTER TABLE `reports`
  ADD CONSTRAINT `FK_REPORTS_1` FOREIGN KEY (`creator_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `reset_password_queries`
--
ALTER TABLE `reset_password_queries`
  ADD CONSTRAINT `FK_RESET_PASSWORD_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK_USER_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
