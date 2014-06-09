-- phpMyAdmin SQL Dump
-- version 4.0.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 03, 2014 at 11:13 AM
-- Server version: 5.5.37-MariaDB-log
-- PHP Version: 5.5.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Ulebank`
--
CREATE DATABASE IF NOT EXISTS `Ulebank` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `Ulebank`;

-- --------------------------------------------------------

--
-- Table structure for table `Accounts`
--

DROP TABLE IF EXISTS `Accounts`;
CREATE TABLE IF NOT EXISTS `Accounts` (
  `accountID` varchar(32) COLLATE utf8_bin NOT NULL,
  `balance` double NOT NULL,
  `lastLiquidationTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `liquidationFrequency` int(11) NOT NULL,
  `maxOverdraft` double NOT NULL,
  PRIMARY KEY (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Cuentas del cliente';

-- --------------------------------------------------------

--
-- Table structure for table `AccountsClients`
--

DROP TABLE IF EXISTS `AccountsClients`;
CREATE TABLE IF NOT EXISTS `AccountsClients` (
  `accountID` varchar(32) COLLATE utf8_bin NOT NULL,
  `clientID` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`accountID`,`clientID`),
  KEY `clientID` (`clientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Libilities: Relación entre cuentas y clientes';

-- --------------------------------------------------------

--
-- Table structure for table `Cards`
--

DROP TABLE IF EXISTS `Cards`;
CREATE TABLE IF NOT EXISTS `Cards` (
  `cardID` varchar(32) COLLATE utf8_bin NOT NULL,
  `clientID` varchar(32) COLLATE utf8_bin NOT NULL,
  `accountID` varchar(32) COLLATE utf8_bin NOT NULL,
  `pin` char(4) COLLATE utf8_bin NOT NULL,
  `buyDiaryLimit` double NOT NULL,
  `buyMonthlyLimit` double NOT NULL,
  `cashDiaryLimit` double NOT NULL,
  `cashMonthlyLimit` double NOT NULL,
  `emissionDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `expirationDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `type` tinyint(1) NOT NULL,
  `cvv` char(3) COLLATE utf8_bin NOT NULL,
  `emissionFee` bigint(20) unsigned NOT NULL,
  `maintenanceFee` bigint(20) unsigned NOT NULL,
  `renovationFee` bigint(20) unsigned NOT NULL,
  KEY `clientID` (`clientID`),
  KEY `accountID` (`accountID`),
  KEY `emissionFee` (`emissionFee`),
  KEY `maintenanceFee` (`maintenanceFee`),
  KEY `renovationFee` (`renovationFee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Payments: Tarjetas de crédito/débito';

-- --------------------------------------------------------

--
-- Table structure for table `Employees`
--

DROP TABLE IF EXISTS `Employees`;
CREATE TABLE IF NOT EXISTS `Employees` (
  `employeeID` varchar(32) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Control: Empleados del banco';

-- --------------------------------------------------------

--
-- Table structure for table `Enterprises`
--

DROP TABLE IF EXISTS `Enterprises`;
CREATE TABLE IF NOT EXISTS `Enterprises` (
  `enterpriseID` varchar(32) COLLATE utf8_bin NOT NULL,
  `name` varchar(32) COLLATE utf8_bin NOT NULL,
  `address` tinytext COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Libilities: Empresas cliente';

-- --------------------------------------------------------

--
-- Table structure for table `FeeCases`
--

DROP TABLE IF EXISTS `FeeCases`;
CREATE TABLE IF NOT EXISTS `FeeCases` (
  `feeCaseID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `amountFormula` varchar(256) COLLATE utf8_bin NOT NULL,
  `triggeringConditions` varchar(256) COLLATE utf8_bin NOT NULL,
  `subject` tinytext COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`feeCaseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Libilities: Casos de comisiones para las liquidaciones' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Histories`
--

DROP TABLE IF EXISTS `Histories`;
CREATE TABLE IF NOT EXISTS `Histories` (
  `historyID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`historyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Common: Lista de históricos' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `HistoriesTransactions`
--

DROP TABLE IF EXISTS `HistoriesTransactions`;
CREATE TABLE IF NOT EXISTS `HistoriesTransactions` (
  `historyID` bigint(20) unsigned NOT NULL,
  `transactionID` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`historyID`,`transactionID`),
  KEY `historyID` (`historyID`),
  KEY `HistoriesTransactions_ibfk_2` (`transactionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Common: Relación n-n entre historiales y transacciones';

-- --------------------------------------------------------

--
-- Table structure for table `InvestmentAccounts`
--

DROP TABLE IF EXISTS `InvestmentAccounts`;
CREATE TABLE IF NOT EXISTS `InvestmentAccounts` (
  `investmentAccountID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `clientID` varchar(32) COLLATE utf8_bin NOT NULL,
  `accountID` varchar(32) COLLATE utf8_bin NOT NULL,
  `buyStockageFee` bigint(20) unsigned NOT NULL,
  `sellStockageFee` bigint(20) unsigned NOT NULL,
  `transactionHistory` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`investmentAccountID`),
  KEY `clientID` (`clientID`,`accountID`,`buyStockageFee`),
  KEY `sellStockageFee` (`sellStockageFee`),
  KEY `accountID` (`accountID`),
  KEY `buyStockageFee` (`buyStockageFee`),
  KEY `transactionHistory` (`transactionHistory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Brokerage: Investment accounts' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `InvestmentFundPacks`
--

DROP TABLE IF EXISTS `InvestmentFundPacks`;
CREATE TABLE IF NOT EXISTS `InvestmentFundPacks` (
  `investmentFundPackID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `investmentFundID` varchar(32) COLLATE utf8_bin NOT NULL,
  `accountID` varchar(32) COLLATE utf8_bin NOT NULL,
  `investmentAccountID` bigint(20) unsigned NOT NULL,
  `amount` int(10) unsigned NOT NULL,
  PRIMARY KEY (`investmentFundPackID`),
  KEY `investmentFundID` (`investmentFundID`),
  KEY `accountID` (`accountID`,`investmentAccountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Brokerage: Participaciones de fondos de inversión' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `InvestmentFunds`
--

DROP TABLE IF EXISTS `InvestmentFunds`;
CREATE TABLE IF NOT EXISTS `InvestmentFunds` (
  `investmentFundID` varchar(32) COLLATE utf8_bin NOT NULL,
  `openerID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'Employee',
  `amount` int(11) NOT NULL,
  `purchasedAmount` bigint(20) unsigned NOT NULL,
  `totalPrice` double unsigned NOT NULL,
  `estimatedProfitability` double unsigned NOT NULL,
  `fee` bigint(20) unsigned NOT NULL,
  `cancellationFee` bigint(20) unsigned NOT NULL,
  KEY `investmentFundID` (`investmentFundID`),
  KEY `fee` (`fee`),
  KEY `cancellationFee` (`cancellationFee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Brokerage: Fondos de inversión';

-- --------------------------------------------------------

--
-- Table structure for table `LinearFees`
--

DROP TABLE IF EXISTS `LinearFees`;
CREATE TABLE IF NOT EXISTS `LinearFees` (
  `feeID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `minimum` double NOT NULL,
  `fee` double NOT NULL,
  PRIMARY KEY (`feeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Common: Comisiones lineales (y = mx + n)' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `LiquidationFees`
--

DROP TABLE IF EXISTS `LiquidationFees`;
CREATE TABLE IF NOT EXISTS `LiquidationFees` (
  `liquidationFeeID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `accountID` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`liquidationFeeID`),
  KEY `accountID` (`accountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Libilities: Comisiones de liquidación' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Loans`
--

DROP TABLE IF EXISTS `Loans`;
CREATE TABLE IF NOT EXISTS `Loans` (
  `loanID` varchar(32) COLLATE utf8_bin NOT NULL,
  `clientID` varchar(32) COLLATE utf8_bin NOT NULL,
  `accountID` varchar(32) COLLATE utf8_bin NOT NULL,
  `type` tinyint(1) unsigned NOT NULL COMMENT 'es.unileon.ulebank.assets.KindOfMethod',
  `paymentPeriod` tinyint(1) unsigned NOT NULL,
  `initialCapital` double unsigned NOT NULL,
  `amortizationTime` int(10) unsigned NOT NULL,
  `interest` double unsigned NOT NULL,
  `creationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` tinytext COLLATE utf8_bin NOT NULL,
  `debt` double unsigned NOT NULL,
  `openingFee` bigint(20) unsigned NOT NULL,
  `studyFee` bigint(20) unsigned NOT NULL,
  `modifyFee` bigint(20) unsigned NOT NULL,
  `cancelFee` bigint(20) unsigned NOT NULL,
  `delayedPaymentFee` bigint(20) unsigned NOT NULL,
  `amortizationFee` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`loanID`),
  KEY `clientID` (`clientID`),
  KEY `accountID` (`accountID`),
  KEY `studyFee` (`studyFee`),
  KEY `modifyFee` (`modifyFee`),
  KEY `cancelFee` (`cancelFee`),
  KEY `delayedPaymentFee` (`delayedPaymentFee`),
  KEY `amortizationFee` (`amortizationFee`),
  KEY `openingFee` (`openingFee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Assets: Lista de préstamos';

-- --------------------------------------------------------

--
-- Table structure for table `Offices`
--

DROP TABLE IF EXISTS `Offices`;
CREATE TABLE IF NOT EXISTS `Offices` (
  `officeID` varchar(32) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Control: Oficinas del banco';

-- --------------------------------------------------------

--
-- Table structure for table `Persons`
--

DROP TABLE IF EXISTS `Persons`;
CREATE TABLE IF NOT EXISTS `Persons` (
  `personID` varchar(32) COLLATE utf8_bin NOT NULL,
  `name` varchar(32) COLLATE utf8_bin NOT NULL,
  `surname` varchar(32) COLLATE utf8_bin NOT NULL,
  `address` varchar(32) COLLATE utf8_bin NOT NULL,
  `civilState` tinyint(1) NOT NULL,
  `profession` varchar(32) COLLATE utf8_bin NOT NULL,
  `birthDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`personID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Libilities: Clientes físicos';

-- --------------------------------------------------------

--
-- Table structure for table `ScheduledPayments`
--

DROP TABLE IF EXISTS `ScheduledPayments`;
CREATE TABLE IF NOT EXISTS `ScheduledPayments` (
  `sheduledPayementID` varchar(32) COLLATE utf8_bin NOT NULL,
  `loanID` varchar(32) COLLATE utf8_bin NOT NULL,
  `paymentDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `expirationDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `amount` double unsigned NOT NULL,
  `amortization` double unsigned NOT NULL,
  `interest` double unsigned NOT NULL,
  `outstandingCapital` double unsigned NOT NULL,
  `paid` tinyint(1) NOT NULL,
  PRIMARY KEY (`sheduledPayementID`),
  KEY `loanID` (`loanID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Assets: Pagos de los préstamos';

-- --------------------------------------------------------

--
-- Table structure for table `StockPacks`
--

DROP TABLE IF EXISTS `StockPacks`;
CREATE TABLE IF NOT EXISTS `StockPacks` (
  `stockPackID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `stockTicker` varchar(6) COLLATE utf8_bin NOT NULL,
  `accountID` varchar(32) COLLATE utf8_bin NOT NULL,
  `investmentAccountID` bigint(20) unsigned NOT NULL,
  `amount` int(10) unsigned NOT NULL,
  PRIMARY KEY (`stockPackID`),
  KEY `accountID` (`accountID`,`investmentAccountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Brokerage: Participaciones de empresas en bolsa' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Transactions`
--

DROP TABLE IF EXISTS `Transactions`;
CREATE TABLE IF NOT EXISTS `Transactions` (
  `transactionID` varchar(32) COLLATE utf8_bin NOT NULL,
  `amount` double NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `effectiveDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `subject` tinytext COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`transactionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Common: Transacciones';

--
-- Constraints for dumped tables
--

--
-- Constraints for table `AccountsClients`
--
ALTER TABLE `AccountsClients`
  ADD CONSTRAINT `AccountsClients_ibfk_2` FOREIGN KEY (`clientID`) REFERENCES `Persons` (`personID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `AccountsClients_ibfk_1` FOREIGN KEY (`accountID`) REFERENCES `Accounts` (`accountID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Cards`
--
ALTER TABLE `Cards`
  ADD CONSTRAINT `Cards_ibfk_5` FOREIGN KEY (`renovationFee`) REFERENCES `LinearFees` (`feeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Cards_ibfk_1` FOREIGN KEY (`clientID`) REFERENCES `Persons` (`personID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Cards_ibfk_2` FOREIGN KEY (`accountID`) REFERENCES `Accounts` (`accountID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Cards_ibfk_3` FOREIGN KEY (`emissionFee`) REFERENCES `LinearFees` (`feeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Cards_ibfk_4` FOREIGN KEY (`maintenanceFee`) REFERENCES `LinearFees` (`feeID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `HistoriesTransactions`
--
ALTER TABLE `HistoriesTransactions`
  ADD CONSTRAINT `HistoriesTransactions_ibfk_1` FOREIGN KEY (`historyID`) REFERENCES `HistoriesTransactions` (`historyID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `HistoriesTransactions_ibfk_2` FOREIGN KEY (`transactionID`) REFERENCES `Transactions` (`transactionID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `InvestmentAccounts`
--
ALTER TABLE `InvestmentAccounts`
  ADD CONSTRAINT `InvestmentAccounts_ibfk_5` FOREIGN KEY (`transactionHistory`) REFERENCES `Histories` (`historyID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `InvestmentAccounts_ibfk_1` FOREIGN KEY (`clientID`) REFERENCES `Persons` (`personID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `InvestmentAccounts_ibfk_2` FOREIGN KEY (`accountID`) REFERENCES `Accounts` (`accountID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `InvestmentAccounts_ibfk_3` FOREIGN KEY (`buyStockageFee`) REFERENCES `LinearFees` (`feeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `InvestmentAccounts_ibfk_4` FOREIGN KEY (`sellStockageFee`) REFERENCES `LinearFees` (`feeID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `InvestmentFunds`
--
ALTER TABLE `InvestmentFunds`
  ADD CONSTRAINT `InvestmentFunds_ibfk_2` FOREIGN KEY (`cancellationFee`) REFERENCES `LinearFees` (`feeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `InvestmentFunds_ibfk_1` FOREIGN KEY (`fee`) REFERENCES `LinearFees` (`feeID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `LiquidationFees`
--
ALTER TABLE `LiquidationFees`
  ADD CONSTRAINT `LiquidationFees_ibfk_1` FOREIGN KEY (`accountID`) REFERENCES `Accounts` (`accountID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `Loans`
--
ALTER TABLE `Loans`
  ADD CONSTRAINT `Loans_ibfk_8` FOREIGN KEY (`amortizationFee`) REFERENCES `LinearFees` (`feeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Loans_ibfk_1` FOREIGN KEY (`clientID`) REFERENCES `Persons` (`personID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Loans_ibfk_2` FOREIGN KEY (`accountID`) REFERENCES `Accounts` (`accountID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Loans_ibfk_3` FOREIGN KEY (`openingFee`) REFERENCES `LinearFees` (`feeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Loans_ibfk_4` FOREIGN KEY (`studyFee`) REFERENCES `LinearFees` (`feeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Loans_ibfk_5` FOREIGN KEY (`modifyFee`) REFERENCES `LinearFees` (`feeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Loans_ibfk_6` FOREIGN KEY (`cancelFee`) REFERENCES `LinearFees` (`feeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Loans_ibfk_7` FOREIGN KEY (`delayedPaymentFee`) REFERENCES `LinearFees` (`feeID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ScheduledPayments`
--
ALTER TABLE `ScheduledPayments`
  ADD CONSTRAINT `ScheduledPayments_ibfk_1` FOREIGN KEY (`loanID`) REFERENCES `Loans` (`loanID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `StockPacks`
--
ALTER TABLE `StockPacks`
  ADD CONSTRAINT `StockPacks_ibfk_1` FOREIGN KEY (`accountID`) REFERENCES `Accounts` (`accountID`) ON DELETE CASCADE ON UPDATE CASCADE;
