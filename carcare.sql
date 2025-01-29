-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308:3308
-- Generation Time: Jan 14, 2024 at 06:50 PM
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
-- Database: `carcare`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `Customer_ID` int(5) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `NIC` varchar(12) NOT NULL,
  `Contact_No` int(255) NOT NULL,
  `Email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELATIONSHIPS FOR TABLE `customer`:
--

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`Customer_ID`, `Name`, `Address`, `NIC`, `Contact_No`, `Email`) VALUES
(1201, 'Thilini Silva', '123, Main Street, Colombo, Sri Lanka', '991234567V', 711234567, 'thilini.silva@gmail.com '),
(1202, 'Nimal Perera', '456, Industrial Avenue, Kandy, Sri Lanka', '992345678V', 772345678, 'nimal.perera@hotmail.com'),
(1203, 'Anjali Fernando', '901, Avenue Road, Negombo, Sri Lanka', '994567890V', 714567890, 'kavindu.jayawardena@gmail.com'),
(1204, 'Sahan Rajapakse', '789, High Road, Galle, Sri Lanka', '993456789V', 763456789, 'sahan.rajapakse@gmail.com'),
(1205, 'Kavindu Jayawardena', '789, High Road, Galle, Sri Lanka', '995678901V', 775678901, 'kavindu.jayawardena@gmail.com'),
(1207, 'Prasad Permal', '567, Hillside Lane, Nuwara Eliya, Sri Lanka', '998901234V', 769012345, 'prasad.permal@gmail.com'),
(1208, 'Prasad Permal', '678, Lake View Street, Anuradhapura, Sri Lanka', '998901234V', 769012345, 'prasad.permal@gmail.com'),
(1209, 'Nisansala Rajakaruna', '789, Temple Road, Polonnaruwa, Sri Lanka', '999012345V', 710123456, 'nisansala.rajakaruna@hotmail.com'),
(1210, 'Chamara Fernando', '890, River View Lane, Ratnapura, Sri Lanka', '990123456V', 771234567, 'chamara.fernando@gmail.com'),
(1211, 'Dilhani Rathnayake', '456, Mountain View Road, Ella, Sri Lanka', '991234567V', 712345678, 'sanjeewa.bandaranayake@hotmail.com'),
(1212, 'Madusha Ranasinghe', '567, Tea Estate Lane, Hatton, Sri Lanka', '993456789V', 764567890, 'madusha.ranasinghe@gmail.com'),
(1213, 'Prabath de Silva', '678, Waterfall View Street, Nuwara Eliya, Sri Lanka', '994567890V', 715678901, 'prabath.desilva@hotmail.com'),
(1260, 'Chamara Silva', '27, Maligawatte Road, Colombo 08', '900332145V', 771234567, 'chamara.silva@email.com'),
(1261, 'Nishan Fernando', '15, Galle Road, Matara', '973554321V', 762345678, 'nishan.fernando@email.com'),
(1270, 'Dilshan Silva', '123, Main Street, Colombo', '901234567V', 771234567, 'dilshan.silva@email.com'),
(1271, 'Nadeesha Perera', '456, Park Avenue, Kandy', '810987654V', 719876543, 'nadeesha.perera@email.com'),
(1272, 'Tharindu Fernando', '789, Beach Road, Galle', '990123456V', 763456789, 'tharindu.fernando@email.com'),
(1273, 'Anusha Bandara', '234, Hillside Lane, Nuwara Eliya', '990876544V', 702345678, 'anusha.bandara@gmail.com'),
(1288, 'Tharindu Gunasinghe', 'Colombo 04, Borella', '991451896V', 776214140, 'oshandilruk317@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `Employee_ID` int(5) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `NIC` varchar(12) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Contact_No` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELATIONSHIPS FOR TABLE `employee`:
--

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`Employee_ID`, `Name`, `NIC`, `Address`, `Email`, `Contact_No`) VALUES
(1501, 'Tharindu Perera', '991234567V', '123, Main Street, Colombo, Sri Lanka', 'tharindu.perera@gmail.com', 711234567),
(1502, 'Anusha Fernando', '992345678V', '456, Industrial Avenue, Kandy, Sri Lanka', 'anusha.fernando@hotmail.com', 772345678),
(1503, 'Sanjaya Rajapakse', '993456789V', '789, High Road, Galle, Sri Lanka', 'sanjaya.rajapakse@gmail.com', 763456789),
(1504, 'Ruwani Jayawardena', '995678901V', '901, Avenue Road, Negombo, Sri Lanka', 'ruwani.jayawardena@gmail.com', 775678901),
(1505, 'Dinusha Gunaratne', '996789012V', '345, Beach Road, Matara, Sri Lanka', 'dinusha.gunaratne@gmail.com', 716789012),
(1506, 'Shanika Bandara', '997890123V', '567, Hillside Lane, Nuwara Eliya, Sri Lanka', 'shanika.bandara@hotmail.com', 778901234),
(1507, 'Nishanthi Rajakaruna', '999012345V', '789, Temple Road, Polonnaruwa, Sri Lanka', 'nishanthi.rajakaruna@hotmail.com', 710123456),
(1508, 'Prabath Permal', '998901234V', '678, Lake View Street, Anuradhapura, Sri Lanka', 'prabath.permal@gmail.com', 769012345),
(1509, 'Charitha Fernando', '990123456V', '890, River View Lane, Ratnapura, Sri Lanka', 'charitha.fernando@gmail.com', 771234567),
(1510, 'Ashan Silva', '991234567V', '234, Forest Lane, Badulla, Sri Lanka', 'ashan.silva@gmail.com', 712345678),
(1511, 'Dhanuka Ranasinghe', '993456789V', '567, Tea Estate Lane, Hatton, Sri Lanka', 'dhanuka.ranasinghe@gmail.com', 764567890),
(1512, 'Imasha Rathnayake', '995678901V', '890, Mountain Road, Haputale, Sri Lanka', 'imasha.rathnayake@gmail.com', 776789012),
(1515, 'Imasha Rathnayake', '995678901V', '890, Mountain Road, Haputale, Sri Lanka', 'imasha.rathnayake@gmail.com', 776789012);

-- --------------------------------------------------------

--
-- Table structure for table `income`
--

CREATE TABLE `income` (
  `Order_ID` int(5) NOT NULL,
  `Finished_Date` varchar(255) DEFAULT NULL,
  `Amount` int(255) DEFAULT NULL,
  `Cost` int(255) DEFAULT NULL,
  `Profit` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELATIONSHIPS FOR TABLE `income`:
--   `Order_ID`
--       `orders` -> `Order_ID`
--

--
-- Dumping data for table `income`
--

INSERT INTO `income` (`Order_ID`, `Finished_Date`, `Amount`, `Cost`, `Profit`) VALUES
(1058, '2023-01-15', 29725, 20500, 9225),
(1059, '2023-03-15', 57275, 39500, 17775),
(1060, '2023-05-10', 30740, 21200, 9540),
(1061, '2023-07-23', 54665, 37700, 16965),
(1074, '2024-06-18', 34075, 23500, 10575);

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `Part_ID` int(5) NOT NULL,
  `Part_Name` varchar(255) NOT NULL,
  `Price` int(255) NOT NULL,
  `Units` int(255) NOT NULL,
  `Total` int(255) NOT NULL,
  `Supplier` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELATIONSHIPS FOR TABLE `inventory`:
--

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`Part_ID`, `Part_Name`, `Price`, `Units`, `Total`, `Supplier`) VALUES
(2201, 'Brake Pads', 5000, 20, 100000, 'AutoTech Spares'),
(2202, 'Oil Filter', 800, 30, 24000, 'Precision Parts Ltd'),
(2203, 'Air Filter', 1200, 40, 48000, 'Speedy Motors Parts'),
(2204, 'Spark Plugs', 1500, 25, 37500, 'ProSpares Solutions'),
(2205, 'Battery', 12000, 50, 600000, 'Auto Components'),
(2206, 'Alternator', 8000, 25, 200000, 'Precision Parts Ltd'),
(2207, 'Starter Motor', 6500, 15, 97500, 'Speedy Motors Parts'),
(2208, 'Timing Belt', 3500, 30, 105000, 'ProSpares Solutions'),
(2209, 'Radiator', 7000, 65, 455000, 'AutoTech Spares'),
(2210, 'Thermostat', 1500, 30, 45000, 'Auto Components'),
(2211, 'Shock Absorbers', 15000, 100, 1500000, 'Speedy Motors Parts'),
(2212, 'Tires ', 6000, 150, 900000, 'Precision Parts Ltd'),
(2213, 'CV Joint', 2500, 30, 75000, 'AutoTech Spares'),
(2214, 'Clutch Kit', 8500, 50, 425000, 'ProSpares Solutions'),
(2215, 'Fuel Pump', 4500, 25, 112500, 'Auto Components');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `Order_ID` int(5) NOT NULL,
  `Customer` varchar(255) DEFAULT NULL,
  `Vehicle_Manufacturer` varchar(255) NOT NULL,
  `Model` varchar(255) NOT NULL,
  `Year` int(255) NOT NULL,
  `License_Plate_No` varchar(255) NOT NULL,
  `Service` varchar(255) NOT NULL,
  `Employee_ID` int(5) DEFAULT NULL,
  `Assigned_Employee` varchar(255) NOT NULL,
  `Repair_parts` varchar(255) NOT NULL,
  `Total_amount` int(255) DEFAULT NULL,
  `Start_Date` varchar(255) NOT NULL,
  `Finished_Date` varchar(255) DEFAULT NULL,
  `Job_Status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELATIONSHIPS FOR TABLE `orders`:
--   `Employee_ID`
--       `employee` -> `Employee_ID`
--

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`Order_ID`, `Customer`, `Vehicle_Manufacturer`, `Model`, `Year`, `License_Plate_No`, `Service`, `Employee_ID`, `Assigned_Employee`, `Repair_parts`, `Total_amount`, `Start_Date`, `Finished_Date`, `Job_Status`) VALUES
(1058, 'Dilshan Silva', 'Toyota ', 'Toyota Camry', 2012, 'WPAB 5678 ', 'Repaint / Repair / Maintenance', 1501, 'Tharindu Perera', 'Battery/ Spark Plugs/ Air Filter/ Oil Filter/ Brake Pads', 29725, '2023-01-05', '2023-01-15', 'COMPLETED'),
(1059, 'Nadeesha Perera', 'Honda ', 'Honda Accord', 2022, 'CPXY 1234 ', 'Repaint / Repair /', 1503, 'Sanjaya Rajapakse', 'Tires / Shock Absorbers/ Thermostat/ Radiator/ Timing Belt/ Starter Motor', 57275, '2023-03-12', '2023-03-15', 'COMPLETED'),
(1060, 'Tharindu Fernando', 'Toyota ', 'Toyota Corolla', 2013, 'NWKL 9876', 'Repaint / Repair / Maintenance', 1504, 'Ruwani Jayawardena', 'Spark Plugs/ Air Filter/ Alternator/ Fuel Pump/ Tires ', 30740, '2023-05-08', '2023-05-10', 'COMPLETED'),
(1061, 'Anusha Bandara', 'Honda ', 'Honda Civic ', 2018, 'EPPQ 4567', 'Repaint / Repair / Maintenance', 1509, 'Charitha Fernando', 'Starter Motor/ Alternator/ Battery/ Spark Plugs/ Air Filter/ Thermostat/ Radiator', 54665, '2023-07-20', '2023-07-23', 'NOT COMPLETED'),
(1074, 'Tharindu Gunasinghe', 'Toyota ', 'Toyota Camry', 2022, 'CBC-6465', 'Repaint / Repair / Maintenance', 1501, 'Tharindu Perera', 'Air Filter/ Oil Filter/ Spark Plugs/ Battery/ Alternator', 34075, '2024-06-05', '2024-06-18', 'COMPLETED');

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `Supplier_ID` int(5) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `NIC` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `Contact_No` int(10) NOT NULL,
  `Email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELATIONSHIPS FOR TABLE `suppliers`:
--

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`Supplier_ID`, `Name`, `NIC`, `Address`, `Contact_No`, `Email`) VALUES
(1101, 'AutoTech Spares', '991234567V', '123, Main Street, Colombo, Sri Lanka', 711234567, 'autotechspares@gmail.com'),
(1102, 'Precision Parts Ltd', '992345678V', '456, Industrial Avenue, Kandy, Sri Lanka', 772345678, 'precisionparts@hotmail.com'),
(1103, 'Speedy Motors Parts', '993456789V', '789, High Road, Galle, Sri Lanka', 763456789, 'speedymotorsparts@gmail.com'),
(1104, 'ProSpares Solutions', '994567890V', '890, New Lane, Jaffna, Sri Lanka', 714567890, 'prospares.solutions@hotmail.com'),
(1105, 'Auto Components', '995678901V', '901, Avenue Road, Negombo, Sri Lanka', 775678901, 'reliableauto.components@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `vehicles`
--

CREATE TABLE `vehicles` (
  `Vehicle_ID` int(5) NOT NULL,
  `Vehicle_Manufacturer` varchar(255) NOT NULL,
  `Model` varchar(255) NOT NULL,
  `Year` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELATIONSHIPS FOR TABLE `vehicles`:
--

--
-- Dumping data for table `vehicles`
--

INSERT INTO `vehicles` (`Vehicle_ID`, `Vehicle_Manufacturer`, `Model`, `Year`) VALUES
(1, 'Toyota ', 'Toyota Camry', 2012),
(2, 'Toyota ', 'Toyota Corolla', 2013),
(3, 'Toyota ', 'Toyota Prius', 2018),
(4, 'Toyota', 'Toyota RAV4', 2018),
(5, 'Toyota ', 'Toyota LandCruiser', 2022),
(6, 'Honda ', 'Honda Accord', 2022),
(7, 'Honda ', 'Honda Civic ', 2018),
(8, 'Honda ', 'Honda CR-V', 2018),
(9, 'Honda ', 'Honda Pilot', 2019),
(10, 'Honda', 'Honda Odyssey', 2022),
(11, 'Toyota ', 'Toyota Camry', 2013),
(12, 'Toyota ', 'Toyota Camry', 2015),
(13, 'Toyota ', 'Toyota Camry', 2016),
(14, 'Toyota ', 'Toyota Camry', 2017),
(15, 'Toyota ', 'Toyota Corolla', 2014),
(16, 'Toyota ', 'Toyota Corolla', 2015),
(17, 'Toyota ', 'Toyota Corolla', 2016),
(18, 'Toyota ', 'Toyota Corolla', 2017),
(19, 'Toyota ', 'Toyota Corolla', 2018),
(20, 'Toyota ', 'Toyota Corolla', 2019),
(21, 'Toyota ', 'Toyota Corolla', 2020),
(22, 'Toyota ', 'Toyota Corolla', 2021),
(23, 'Toyota ', 'Toyota Corolla', 2022),
(24, 'Toyota ', 'Toyota Corolla', 2023),
(25, 'Toyota ', 'Toyota Camry', 2018),
(26, 'Toyota ', 'Toyota Camry', 2019),
(27, 'Toyota ', 'Toyota Camry', 2020),
(28, 'Toyota ', 'Toyota Camry', 2021),
(29, 'Toyota ', 'Toyota Camry', 2022),
(30, 'Toyota ', 'Toyota Prius', 2019),
(31, 'Honda ', 'Honda Civic ', 2019),
(32, 'Honda ', 'Honda Civic ', 2020),
(33, 'Honda ', 'Honda Civic ', 2021),
(34, 'Honda ', 'Honda Accord', 2019),
(35, 'Honda ', 'Honda Accord', 2018),
(36, 'Toyota', 'Toyota RAV4', 2019),
(37, 'Toyota ', 'Toyota LandCruiser', 2020),
(38, 'Toyota ', 'Toyota LandCruiser', 2021),
(39, 'Toyota ', 'Toyota LandCruiser', 2022),
(46, 'Honda', 'Honda Accord', 2023);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`Customer_ID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`Employee_ID`);

--
-- Indexes for table `income`
--
ALTER TABLE `income`
  ADD PRIMARY KEY (`Order_ID`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`Part_ID`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`Order_ID`),
  ADD KEY `orders_ibfk_1` (`Employee_ID`),
  ADD KEY `Customer_ID` (`Customer`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`Supplier_ID`);

--
-- Indexes for table `vehicles`
--
ALTER TABLE `vehicles`
  ADD PRIMARY KEY (`Vehicle_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `Customer_ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1289;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `Employee_ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1516;

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `Part_ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2216;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `Order_ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1075;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `Supplier_ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1106;

--
-- AUTO_INCREMENT for table `vehicles`
--
ALTER TABLE `vehicles`
  MODIFY `Vehicle_ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `income`
--
ALTER TABLE `income`
  ADD CONSTRAINT `income_ibfk_1` FOREIGN KEY (`Order_ID`) REFERENCES `orders` (`Order_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`Employee_ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
