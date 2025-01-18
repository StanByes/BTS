-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 18 jan. 2025 à 16:46
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

--
-- Déchargement des données de la table `internships`
--

INSERT INTO `internships` (`intern_id`, `supervisor_id`, `start_at`, `end_at`, `day_start_at`, `day_end_at`) VALUES
(1, 2, '2025-01-07', '2025-01-23', NULL, '17:00:00'),
(7, 2, '2025-01-08', '2025-01-10', '10:00:00', '18:00:00');

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

--
-- Déchargement des données de la table `reports`
--

INSERT INTO `reports` (`id`, `creator_id`, `title`, `content`, `date`, `created_at`) VALUES
(1, 1, '1er test !', 'Je suis un premier test de raport de stage inclus à la mano dans la BDD.', '2025-01-01', '2025-01-06 14:38:55'),
(2, 1, 'test', 'test', '2025-01-02', '2025-01-06 14:57:44'),
(3, 1, 'Je suis un deuxieme test', 'uqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfbuqzbduqzdbquzdbqiugbqzudbqiuzfb', '2025-01-03', '2025-01-06 14:57:58'),
(4, 1, '3eme test', 'qzdbqzudbqzdubqziufbqeiufbqziucbqzuivbqeiuvbqzcbqizucbqziudqbzd\r\n', '2025-01-04', '2025-01-06 15:00:53'),
(5, 1, 'test flash', 'teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh teqqnzdiqzndiqznd qizfhqifb qzjdqzi fqifh qzdqiz dj qizhdiqz ifqh egiqdizhdi qzhfiqz d iqzhfiqhfi qzfh \r\n', '2025-01-05', '2025-01-06 15:59:17'),
(6, 1, 'sfsf', 'test', '2025-01-06', '2025-01-07 10:28:49'),
(7, 7, 'test', 'test', '2025-01-04', '2025-01-07 16:26:32'),
(8, 1, 'Jour 1', 'Je suis le premier jour', '2025-01-08', '2025-01-07 16:41:45');

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

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id`, `name`, `display_name`) VALUES
(1, 'intern', 'Stagiaire'),
(2, 'supervisor', 'Maître de stage');

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
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `firstname`, `surname`, `login`, `mail`, `password`, `role_id`) VALUES
(1, 'Bastien', 'Riot', 'StanByes', 'bastienriot2@gmail.com', '$2y$10$QeHoI8vqpOcMoNpp8xpAU.lN0YRx2BHndnfsaT9/pdkfbdJy4JrUG', 1),
(2, 'Prof', 'Prof', 'prof', 'prof.prof@gmail.com', '$2a$12$lRorEMFV5oQ/EfhtUK822.qVLy0ZS0W9KBf7EJWgWVaY5WINq3CrG', 2),
(7, 'Test', 'test', 'test', 'test@gmail.com', '$2a$12$gnqMu3dsmyFaelr.1vzP.uHyuio8LeUWRiCvwl71XsKrJ5OVOo2pG', 1);

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
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `reset_password_queries`
--
ALTER TABLE `reset_password_queries`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

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
