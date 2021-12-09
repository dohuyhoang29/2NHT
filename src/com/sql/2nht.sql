-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.20-MariaDB - mariadb.org binary distribution
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table 2nht.account: ~5 rows (approximately)
DELETE FROM `account`;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `username`, `email`, `password`, `status`, `type`, `address`, `phone_number`) VALUES
	(3, 'hoang', 'hoang@gmail.com', '12345678', 'Un-Lock', 'CUSTOMER', 'VN', '012345678'),
	(5, 'hoang123', 'hoang@gmail.com', '12345678', 'Un-Lock', 'CUSTOMER', 'VN', '012345678'),
	(8, 'sadf', 'asdf@afas.adf', '1234', 'Un-Lock', 'STAFF', 'faf', '0123123123'),
	(9, 'admin', 'admin@gmail.com', '1', 'Un-Lock', 'ADMIN', 'VN', '012345678'),
	(10, 'nhi', 'nhi@gmail.com', '12345678', 'Un-Lock', 'USER', 'VN', '0123456789');
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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table 2nht.categories: ~4 rows (approximately)
DELETE FROM `categories`;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` (`id`, `name`, `description`, `status`) VALUES
	(1, 'MacBook', 'lkdf;asldkfjads;lkf', 'Un-Lock'),
	(2, 'iPhone', 'adfasfadsfadsf', 'Un-Lock'),
	(3, 'iPad', 'adfasdfsfasfsda', 'Un-Lock'),
	(5, 'abc', 'afdasdkfjas;dlkfjsa;lkfjsal;jf', 'Un-Lock');
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

-- Dumping data for table 2nht.feedback: ~0 rows (approximately)
DELETE FROM `feedback`;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;

-- Dumping structure for table 2nht.order
CREATE TABLE IF NOT EXISTS `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL DEFAULT 0,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(50) NOT NULL DEFAULT '0',
  `total_price` int(11) NOT NULL DEFAULT 0,
  `create_date` date NOT NULL,
  `status` text NOT NULL,
  `address` text NOT NULL,
  `phone_number` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1_account` (`account_id`),
  CONSTRAINT `FK1_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table 2nht.order: ~2 rows (approximately)
DELETE FROM `order`;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`id`, `account_id`, `code`, `name`, `total_price`, `create_date`, `status`, `address`, `phone_number`) VALUES
	(13, 3, '#RKO60', 'hoang', 321, '2021-12-08', 'Completed', 'VN', '012345678'),
	(14, 3, '#CP34Z', 'hoang', 270790, '2021-12-08', 'Completed', 'VN', '012345678');
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table 2nht.order_detail: ~5 rows (approximately)
DELETE FROM `order_detail`;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` (`id`, `quantity`, `product_id`, `order_id`) VALUES
	(27, 1, 27, 13),
	(28, 1, 22, 14),
	(29, 1, 20, 14),
	(30, 1, 24, 14),
	(31, 1, 21, 14);
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table 2nht.product: ~10 rows (approximately)
DELETE FROM `product`;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `category_id`, `status`, `code`, `name`, `warranty_period`, `import_price`, `price`, `hard_drive`, `origin`, `img_src`, `screen`, `cpu`, `gpu`, `ram`, `operating_system`, `rear_camera`, `selfie_camera`, `battery_capacity`, `sim`, `weight`, `dimensions`) VALUES
	(19, 3, 'Un-Lock', '001', 'adfa', '123', 1341324, 13414, 'adfaf', 'adfasf', 'ipadmini65_1.jpg', '', '', '', '', '', '', '', '', '', '', ''),
	(20, 1, 'Lock', 'afdsf', 'dfsd', 'addfas', 1123, 123123, 'adfafd', 'adfasf', 'ipadmini65_1.jpg', '', '', '', '', '', '', '', '', '', '', ''),
	(21, 2, 'Un-Lock', 'adf', 'adf', '123', 123, 1231, 'adf', 'asdf', 'demo_ipad.png', '', '', '', '', '', '', '', '', '', '', ''),
	(22, 3, 'Un-Lock', 'adsf', 'adfs', '12', 123, 12312, 'afd', 'asfda', 'demo_ipad.png', '', '', '', '', '', '', '', '', '', '', ''),
	(23, 3, 'Un-Lock', 'adfasf', 'afdafds', '123', 123, 123123, 'adf', 'adf', 'ipad-pro-11-2021-1_4.jpg', 'adf', 'adf', 'adf', 'adf', 'asfas', 'fas', 'dfdf', 'asfasdf', 'fasdf', 'adf', 'fdaf'),
	(24, 3, 'Un-Lock', 'adf', 'asdfasf', 'adfas', 1341, 134124, 'df', 'adfasf', 'ipad-air-4-wifi-256gb-9.jpg', 'asdf', 'adf', 'adsf', 'adsf', 'asdf', 'sdfasfd', 'asdfa', 'afdasdf', 'asdf', 'sdf', 'asdf'),
	(25, 1, 'Lock', '002', 'Product 1', '24', 1234, 6541, '512GB', 'USA', '61333_apple_macbook_pro_16_4.jpg', 'asa', 'aas', 'asas', 'asas', 'sasa', 'as', 'sasas', 'asasas', 'sas', 'ass', 'sasa'),
	(26, 1, 'Lock', '002', 'Product 1', '24', 1234, 4321, '512GB', 'China', 'demo_macbook.png', '', '', '', '', '', '', '', '', '', '', ''),
	(27, 1, 'Lock', '004', 'Product 2', '24', 123, 321, '256GB', 'China', 'apple-ipad-pro-11-2020-wifi-128-gb-2.jpg', 'afdasf', 'adfs', 'adsf', 'adf', 'fds', 'fasdfas', 'asfdas', 'fasdf', 'asfasdf', 'asdf', 'asdf'),
	(28, 2, 'Un-Lock', '009', 'Product 7', '24', 4567, 4580, '512GB', 'USA', 'iphone-11-pro-max_1_.jpg', '6.7”, OLED, Super Retina XDR, ProMotion 120Hz', 'A15 Bionic, 6 nhân CPU, 4 nhân GPU, 5nm', '', '6GB', 'IOS', '3 camera 12 MP, cảm biến LiDAR', '12MP', 'Up to 28h', '2 SIM', '20x15x17', '0.25');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
