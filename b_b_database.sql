-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 17, 2024 at 07:05 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `b&b_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `Bid` int(11) NOT NULL,
  `Bpassword` varchar(20) DEFAULT NULL,
  `Blocation` varchar(50) DEFAULT NULL,
  `Cname` varchar(20) DEFAULT NULL,
  `Bphone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `Cid` int(11) NOT NULL,
  `Fname` varchar(20) NOT NULL,
  `Lname` varchar(20) NOT NULL,
  `Caddress` varchar(50) DEFAULT NULL,
  `Cnumber` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`Cid`, `Fname`, `Lname`, `Caddress`, `Cnumber`) VALUES
(1, 'John', 'Doe', '123 Main St, Cityville', '555-1234'),
(2, 'Jane', 'Smith', '456 Oak St, Townsville', '555-5678'),
(3, 'Robert', 'Johnson', '789 Pine St, Villagetown', '555-9101'),
(4, 'Emily', 'Davis', '101 Elm St, Hamletville', '555-1122'),
(5, 'Michael', 'Miller', '202 Maple St, Suburbia', '555-3344'),
(6, 'Samantha', 'Anderson', '303 Birch St, Countryside', '555-5566'),
(7, 'William', 'Clark', '404 Cedar St, Mountainside', '555-7788'),
(8, 'Olivia', 'White', '505 Spruce St, Riverside', '555-9900'),
(9, 'Daniel', 'Brown', '606 Fir St, Lakeside', '555-1122'),
(10, 'Sophia', 'Jones', '707 Redwood St, Valleytown', '555-3344');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `Pid` int(11) NOT NULL,
  `Pname` varchar(20) DEFAULT NULL,
  `Pcategory` varchar(20) DEFAULT NULL,
  `Pqty` varchar(20) DEFAULT NULL,
  `Pprice` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `salesinvoice`
--

CREATE TABLE `salesinvoice` (
  `Invoiceid` int(11) NOT NULL,
  `Idate` varchar(20) NOT NULL,
  `Bid` int(11) NOT NULL,
  `Cname` varchar(20) NOT NULL,
  `Cid` int(11) NOT NULL,
  `Pid` int(11) NOT NULL,
  `Sales` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`Bid`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`Cid`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`Pid`);

--
-- Indexes for table `salesinvoice`
--
ALTER TABLE `salesinvoice`
  ADD PRIMARY KEY (`Invoiceid`),
  ADD KEY `fk_salesinvoice_branch` (`Bid`),
  ADD KEY `fk_salesinvoice_customer` (`Cid`),
  ADD KEY `fk_salesinvoice_product` (`Pid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `Cid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `salesinvoice`
--
ALTER TABLE `salesinvoice`
  MODIFY `Invoiceid` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `salesinvoice`
--
ALTER TABLE `salesinvoice`
  ADD CONSTRAINT `fk_salesinvoice_branch` FOREIGN KEY (`Bid`) REFERENCES `branch` (`Bid`),
  ADD CONSTRAINT `fk_salesinvoice_customer` FOREIGN KEY (`Cid`) REFERENCES `customer` (`Cid`),
  ADD CONSTRAINT `fk_salesinvoice_product` FOREIGN KEY (`Pid`) REFERENCES `product` (`Pid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
