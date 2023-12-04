-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2023 at 04:55 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistemamedico`
--

-- --------------------------------------------------------

--
-- Table structure for table `agendamento`
--

CREATE TABLE `agendamento` (
  `id` int(11) NOT NULL,
  `especialidade` varchar(100) NOT NULL,
  `nome_medico` varchar(255) NOT NULL,
  `data_consulta` date NOT NULL,
  `horario_consulta` time NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `consultas`
--

CREATE TABLE `consultas` (
  `id` int(11) NOT NULL,
  `NomePaciente` varchar(255) NOT NULL,
  `Especialidade` varchar(100) NOT NULL,
  `NomeMedico` varchar(255) NOT NULL,
  `DataConsulta` date NOT NULL,
  `HorarioConsulta` time NOT NULL,
  `Email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `consultas`
--

INSERT INTO `consultas` (`id`, `NomePaciente`, `Especialidade`, `NomeMedico`, `DataConsulta`, `HorarioConsulta`, `Email`) VALUES
(2, 'mari', 'Dermatologia', 'Dra.Santos', '2023-12-15', '14:00:00', 'mari@gmail.com'),
(3, 'nicole', 'dermatologia', 'dr ronaldo', '2023-02-10', '11:00:00', 'nicole@gmail.com'),
(4, 'Ana', 'Dermatologia', 'Dra.Silvia', '2023-10-10', '12:20:00', 'ana@gmail.com'),
(5, 'Luiz', 'Cardiologia', 'Dr Carlos', '2020-12-10', '16:30:00', 'luiz@gmail.com'),
(6, 'Luiz', 'Cardiologia', 'Dr Carlos', '2020-12-10', '16:31:00', 'luiz@gmail.com'),
(7, 'Teste', 'Psicologia', 'Dr.Teste', '2023-10-10', '15:30:00', 'teste@gmail.com'),
(8, 'TESTE', 'TESTE', 'TESTE', '2020-10-10', '13:00:00', 'TESTE@GMAIL.COM');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `usuario` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `tipoUsuario` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `usuario`, `senha`, `tipoUsuario`) VALUES
(1, 'Mariana', '123', 'Medico'),
(2, 'Mariana', '123', 'Medico'),
(3, 'Alex', '456', 'Paciente');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agendamento`
--
ALTER TABLE `agendamento`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `consultas`
--
ALTER TABLE `consultas`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agendamento`
--
ALTER TABLE `agendamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `consultas`
--
ALTER TABLE `consultas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
