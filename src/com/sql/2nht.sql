-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.21-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for 2nht
CREATE DATABASE IF NOT EXISTS `2nht` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `2nht`;

-- Dumping structure for table 2nht.account
CREATE TABLE IF NOT EXISTS `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` text NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  `type` text NOT NULL,
  `address` text DEFAULT NULL,
  `phone_number` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table 2nht.account: ~4 rows (approximately)
DELETE FROM `account`;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `username`, `email`, `password`, `status`, `type`, `address`, `phone_number`) VALUES
	(3, 'hoang', 'hoang@gmail.com', '1234', 'Un-Lock', 'CUSTOMER', 'VN', '012345678'),
	(5, 'hoang123', 'hoang@gmail.com', '1234', 'Un-Lock', 'CUSTOMER', 'VN', '012345678'),
	(8, 'sadf', 'asdf@afas.adf', '01', 'Un-Lock', 'CUSTOMER', 'faf', '0123123123'),
	(9, 'admin', 'admin@gmail.com', '1234', 'Un-Lock', 'ADMIN', 'VN', '012345678');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- Dumping structure for table 2nht.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL DEFAULT 0,
  `product_id` int(11) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cart_product` (`product_id`),
  KEY `FK_cart_account` (`account_id`),
  CONSTRAINT `FK_cart_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_cart_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table 2nht.cart: ~0 rows (approximately)
DELETE FROM `cart`;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

-- Dumping structure for table 2nht.categories
CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0',
  `description` text DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table 2nht.categories: ~3 rows (approximately)
DELETE FROM `categories`;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` (`id`, `name`, `description`, `status`) VALUES
	(1, 'MacBook', 'lkdf;asldkfjads;lkf', 'Lock'),
	(2, 'iPhone', 'adfasfadsfadsf', 'Un-Lock'),
	(3, 'iPad', 'adfasdfsfasfsda', 'Un-Lock');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;

-- Dumping structure for table 2nht.feedback
CREATE TABLE IF NOT EXISTS `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL DEFAULT 0,
  `product_id` int(11) NOT NULL DEFAULT 0,
  `feedback` text NOT NULL,
  `point` int(11) NOT NULL DEFAULT 0,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1_Account_Feedback` (`account_id`),
  KEY `FK2_Product_Feedback` (`product_id`),
  CONSTRAINT `FK1_Account_Feedback` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK2_Product_Feedback` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table 2nht.feedback: ~4 rows (approximately)
DELETE FROM `feedback`;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` (`id`, `account_id`, `product_id`, `feedback`, `point`, `date`) VALUES
	(1, 3, 3, 'Very Good', 5, '2021-11-04'),
	(4, 3, 5, 'Awnsome', 5, '2021-11-21'),
	(5, 3, 12, 'abc', 5, '2021-12-01'),
	(6, 3, 13, 'adfa', 3, '2021-12-01');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;

-- Dumping structure for table 2nht.order
CREATE TABLE IF NOT EXISTS `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL DEFAULT 0,
  `name` varchar(50) NOT NULL DEFAULT '0',
  `total_price` int(11) NOT NULL DEFAULT 0,
  `create_date` date NOT NULL,
  `status` text NOT NULL,
  `address` text NOT NULL,
  `phone_number` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1_account` (`account_id`),
  CONSTRAINT `FK1_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table 2nht.order: ~4 rows (approximately)
DELETE FROM `order`;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`id`, `account_id`, `name`, `total_price`, `create_date`, `status`, `address`, `phone_number`) VALUES
	(6, 3, 'hoang', 4525, '2021-11-23', 'To Ship', 'VN', '012345678'),
	(7, 3, 'hoang', 4525, '2021-11-24', 'To Receive', 'VN', '012345678'),
	(8, 3, 'hoang', 18102, '2021-11-24', 'To Pay', 'VN', '012345678'),
	(9, 3, 'hoang', 4525, '2021-11-24', 'Completed', 'VN', '012345678');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- Dumping structure for table 2nht.order_detail
CREATE TABLE IF NOT EXISTS `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) NOT NULL DEFAULT 0,
  `product_id` int(11) NOT NULL DEFAULT 0,
  `order_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `FK__product` (`product_id`),
  KEY `FK__account` (`order_id`) USING BTREE,
  CONSTRAINT `FK__order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK__product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table 2nht.order_detail: ~6 rows (approximately)
DELETE FROM `order_detail`;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` (`id`, `quantity`, `product_id`, `order_id`) VALUES
	(12, 1, 5, 6),
	(13, 2, 5, 7),
	(14, 1, 3, 8),
	(15, 1, 5, 8),
	(16, 1, 9, 8),
	(17, 1, 10, 8);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;

-- Dumping structure for table 2nht.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `code` text DEFAULT NULL,
  `name` text DEFAULT NULL,
  `warranty_period` text DEFAULT NULL,
  `import_price` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `hard_drive` text DEFAULT NULL,
  `origin` text DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `img_src` text DEFAULT NULL,
  `screen` text DEFAULT NULL,
  `cpu` text DEFAULT NULL,
  `gpu` text DEFAULT NULL,
  `ram` text DEFAULT NULL,
  `operating_system` text DEFAULT NULL,
  `rear_camera` text DEFAULT NULL,
  `selfie_camera` text DEFAULT NULL,
  `battery_capacity` text DEFAULT NULL,
  `sim` text DEFAULT NULL,
  `weight` text DEFAULT NULL,
  `dimensions` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1_Catergories` (`category_id`),
  CONSTRAINT `FK1_Catergories` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table 2nht.product: ~12 rows (approximately)
DELETE FROM `product`;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `category_id`, `status`, `code`, `name`, `warranty_period`, `import_price`, `price`, `hard_drive`, `origin`, `color`, `img_src`, `screen`, `cpu`, `gpu`, `ram`, `operating_system`, `rear_camera`, `selfie_camera`, `battery_capacity`, `sim`, `weight`, `dimensions`) VALUES
	(3, 3, 'Un-Lock', 'adsf', 'adfads', '2021-11-10', 4255, 4524, 'sfg', 'sfg', 'sfdgsdg', 'demo_ipad.png', 'asdfasdfa', 'asdf', 'adfas', 'fasdf', 'adfasdfadf', 'f', 'adfads', 'adfad', 'fafasd', 'fasdfasdf', 'sdfas'),
	(5, 3, 'Un-Lock', 'adsf', 'adfads', '2021-11-10', 42536, 4525, 'sfg', 'sfg', 'sfdgsdg', 'demo_ipad.png', 'asdfasdfa', 'asdf', 'adfas', 'fasdf', 'adfasdfadf', 'f', 'adfads', 'adfad', 'fafasd', 'fasdfasdf', 'sdfas'),
	(9, 3, 'Un-Lock', 'adsf', 'adfads', '2021-11-10', 42537, 4526, 'sfg', 'sfg', 'sfdgsdg', 'demo_ipad.png', 'asdfasdfa', 'asdf', 'adfas', 'fasdf', 'adfasdfadf', 'f', 'adfads', 'adfad', 'fafasd', 'fasdfasdf', 'sdfas'),
	(10, 3, 'Un-Lock', 'adsf', 'adfads', '2021-11-10', 42538, 4527, 'sfg', 'sfg', 'sfdgsdg', 'demo_ipad.png', 'asdfasdfa', 'asdf', 'adfas', 'fasdf', 'adfasdfadf', 'f', 'adfads', 'adfad', 'fafasd', 'fasdfasdf', 'sdfas'),
	(11, 2, 'Un-Lock', 'ads', 'adfas', '2021-11-10', 4253, 452, 'sfg', 'sfg', 'sfdgsdg', 'demo_ipad.png', 'asdfasdfa', 'asdf', 'adfas', 'fasdf', 'adfasdfadf', 'f', 'adfads', 'adfad', 'fafasd', 'fasdfasdf', 'sdfas'),
	(12, 2, 'Un-Lock', 'ads', 'adfad', '2021-11-10', 4253, 452, 'sfg', 'sfg', 'sfdgsdg', 'demo_ipad.png', 'asdfasdfa', 'asdf', 'adfas', 'fasdf', 'adfasdfadf', 'f', 'adfads', 'adfad', 'fafasd', 'fasdfasdf', 'sdfas'),
	(13, 2, 'Un-Lock', 'ads', 'afads', '2021-11-10', 4253, 452, 'sfg', 'sfg', 'sfdgsdg', 'demo_ipad.png', 'asdfasdfa', 'asdf', 'adfas', 'fasdf', 'adfasdfadf', 'f', 'adfads', 'adfad', 'fafasd', 'fasdfasdf', 'sdfas'),
	(14, 2, 'Un-Lock', 'ads', 'adfds', '2021-11-10', 4253, 452, 'sfg', 'sfg', 'sfdgsdg', 'demo_ipad.png', 'asdfasdfa', 'asdf', 'adfas', 'fasdf', 'adfasdfadf', 'f', 'adfads', 'adfad', 'fafasd', 'fasdfasdf', 'sdfas'),
	(15, 1, 'Lock', 'ads', 'adfas', '2021-11-10', 4253, 452, 'sfg', 'sfg', 'sfdgsdg', 'demo_ipad.png', 'asdfasdfa', 'asdf', 'adfas', 'fasdf', 'adfasdfadf', 'f', 'adfads', 'adfad', 'fafasd', 'fasdfasdf', 'sdfas'),
	(16, 1, 'Lock', 'ads', 'adfas', '2021-11-10', 4253, 452, 'sfg', 'sfg', 'sfdgsdg', 'demo_ipad.png', 'asdfasdfa', 'asdf', 'adfas', 'fasdf', 'adfasdfadf', 'f', 'adfads', 'adfad', 'fafasd', 'fasdfasdf', 'sdfas'),
	(17, 1, 'Lock', 'ads', 'afads', '2021-11-10', 4253, 452, 'sfg', 'sfg', 'sfdgsdg', 'demo_ipad.png', 'asdfasdfa', 'asdf', 'adfas', 'fasdf', 'adfasdfadf', 'f', 'adfads', 'adfad', 'fafasd', 'fasdfasdf', 'sdfas'),
	(18, 1, 'Lock', 'SD', 'adfds', '2021-11-10', 4253, 452, 'sfg', 'sfg', 'sfdgsdg', 'demo_ipad.png', 'asdfasdfa', 'asdf', 'adfas', 'fasdf', 'adfasdfadf', 'f', 'adfads', 'adfad', 'fafasd', 'fasdfasdf', 'sdfas');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
