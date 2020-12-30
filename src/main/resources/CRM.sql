-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 30, 2020 at 10:10 AM
-- Server version: 5.7.26
-- PHP Version: 5.6.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `opencrm`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_customer`
--

CREATE TABLE `t_customer` (
                              `customer_id` int(11) NOT NULL,
                              `name` varchar(45) DEFAULT NULL,
                              `address` varchar(100) DEFAULT NULL,
                              `credit` int(11) DEFAULT NULL,
                              `phone` varchar(20) DEFAULT NULL,
                              `employee_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_customer`
--

INSERT INTO `t_customer` (`customer_id`, `name`, `address`, `credit`, `phone`, `employee_id`) VALUES
(1, '唐钱进', '江苏省南京市栖霞区羊山北路一号', 90, '13861948872', 1),
(2, '余欣婷', '江苏省南京市栖霞区羊山北路一号', 90, '13861948872', 1),
(3, '唐小余', '江苏省南通市如东县洋口镇', 80, '13861948872', 1),
(4, '余小唐', '江西省抚州市临川区', 80, '13861948872', 1);

-- --------------------------------------------------------

--
-- Table structure for table `t_employee`
--

CREATE TABLE `t_employee` (
                              `employee_id` int(11) NOT NULL,
                              `name` varchar(45) DEFAULT NULL,
                              `produce_type` varchar(45) DEFAULT NULL,
                              `department_id` int(11) DEFAULT NULL,
                              `salary` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_employee`
--

INSERT INTO `t_employee` (`employee_id`, `name`, `produce_type`, `department_id`, `salary`) VALUES
(1, 'kirito', '主机类', 1, '24000');

-- --------------------------------------------------------

--
-- Table structure for table `t_feedback`
--

CREATE TABLE `t_feedback` (
                              `produce_id` int(11) NOT NULL,
                              `customer_id` int(11) NOT NULL,
                              `feedback_id` int(11) NOT NULL,
                              `content` varchar(500) DEFAULT NULL,
                              `create_time` date DEFAULT NULL,
                              `deal_status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_produce`
--

CREATE TABLE `t_produce` (
                             `produce_id` int(11) NOT NULL,
                             `name` varchar(45) DEFAULT NULL,
                             `price` decimal(10,0) DEFAULT NULL,
                             `produce_date` date DEFAULT NULL,
                             `produce_type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_task`
--

CREATE TABLE `t_task` (
                          `task_id` int(11) NOT NULL,
                          `task_time` date DEFAULT NULL,
                          `customer_num` int(11) DEFAULT NULL,
                          `employee_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_trade`
--

CREATE TABLE `t_trade` (
                           `customer_id` int(11) NOT NULL,
                           `produce_id` int(11) NOT NULL,
                           `trade_id` int(11) NOT NULL,
                           `employee_id` int(11) DEFAULT NULL,
                           `amount` decimal(10,0) DEFAULT NULL,
                           `produce_num` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_user`
--

CREATE TABLE `t_user` (
                          `id` int(11) NOT NULL,
                          `username` varchar(50) NOT NULL,
                          `password` varchar(50) NOT NULL,
                          `email` varchar(50) NOT NULL,
                          `account_level` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_user`
--

INSERT INTO `t_user` (`id`, `username`, `password`, `email`, `account_level`) VALUES
(1, 'kirito', 'e10adc3949ba59abbe56e057f20f883e', 'finger@spianmo.com', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_customer`
--
ALTER TABLE `t_customer`
    ADD PRIMARY KEY (`customer_id`),
    ADD KEY `fk_t_customer_t_employee1_idx` (`employee_id`);

--
-- Indexes for table `t_employee`
--
ALTER TABLE `t_employee`
    ADD PRIMARY KEY (`employee_id`),
    ADD UNIQUE KEY `employee_id_UNIQUE` (`employee_id`);

--
-- Indexes for table `t_feedback`
--
ALTER TABLE `t_feedback`
    ADD PRIMARY KEY (`feedback_id`),
    ADD KEY `fk_t_produce_has_t_customer_t_customer1_idx` (`customer_id`),
    ADD KEY `fk_t_produce_has_t_customer_t_produce1_idx` (`produce_id`);

--
-- Indexes for table `t_produce`
--
ALTER TABLE `t_produce`
    ADD PRIMARY KEY (`produce_id`);

--
-- Indexes for table `t_task`
--
ALTER TABLE `t_task`
    ADD PRIMARY KEY (`task_id`),
    ADD UNIQUE KEY `task_id_UNIQUE` (`task_id`),
    ADD KEY `fk_t_task_t_employee1_idx` (`employee_id`);

--
-- Indexes for table `t_trade`
--
ALTER TABLE `t_trade`
    ADD PRIMARY KEY (`trade_id`),
    ADD KEY `fk_t_customer_has_t_produce_t_produce1_idx` (`produce_id`),
    ADD KEY `fk_t_customer_has_t_produce_t_customer1_idx` (`customer_id`);

--
-- Indexes for table `t_user`
--
ALTER TABLE `t_user`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_employee`
--
ALTER TABLE `t_employee`
    MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `t_feedback`
--
ALTER TABLE `t_feedback`
    MODIFY `feedback_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_task`
--
ALTER TABLE `t_task`
    MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_trade`
--
ALTER TABLE `t_trade`
    MODIFY `trade_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_user`
--
ALTER TABLE `t_user`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_customer`
--
ALTER TABLE `t_customer`
    ADD CONSTRAINT `fk_t_customer_t_employee1` FOREIGN KEY (`employee_id`) REFERENCES `t_employee` (`employee_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `t_feedback`
--
ALTER TABLE `t_feedback`
    ADD CONSTRAINT `fk_t_produce_has_t_customer_t_customer1` FOREIGN KEY (`customer_id`) REFERENCES `t_customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    ADD CONSTRAINT `fk_t_produce_has_t_customer_t_produce1` FOREIGN KEY (`produce_id`) REFERENCES `t_produce` (`produce_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `t_task`
--
ALTER TABLE `t_task`
    ADD CONSTRAINT `fk_t_task_t_employee1` FOREIGN KEY (`employee_id`) REFERENCES `t_employee` (`employee_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `t_trade`
--
ALTER TABLE `t_trade`
    ADD CONSTRAINT `fk_t_customer_has_t_produce_t_customer1` FOREIGN KEY (`customer_id`) REFERENCES `t_customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    ADD CONSTRAINT `fk_t_customer_has_t_produce_t_produce1` FOREIGN KEY (`produce_id`) REFERENCES `t_produce` (`produce_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
