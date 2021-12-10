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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table 2nht.cart: ~0 rows (approximately)
DELETE FROM `cart`;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`id`, `quantity`, `product_id`, `account_id`) VALUES
	(47, 2, 29, 3),
	(48, 1, 41, 3);
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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table 2nht.product: ~9 rows (approximately)
DELETE FROM `product`;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `category_id`, `status`, `code`, `name`, `warranty_period`, `import_price`, `price`, `hard_drive`, `origin`, `img_src`, `screen`, `cpu`, `gpu`, `ram`, `operating_system`, `rear_camera`, `selfie_camera`, `battery_capacity`, `sim`, `weight`, `dimensions`) VALUES
	(29, 2, 'Un-Lock', '001', 'iPhone 13 Pro Max', '12 months', 900, 1099, '128GB', 'China', 'iphone_13-_pro-1_2.png', '6.7", Super Retina XDR, OLED, 2778 x 1284 Pixel', 'A15 Bionic', 'Apple GPU 5 nhân', '6 GB', 'iOS 15', '12.0 MP + 12.0 MP + 12.0 MP', '12.0 MP', '4352 mAh', '2, 1 eSIM, 1 Nano SIM', '160.8 x 78.1', '228 g'),
	(30, 2, 'Un-Lock', '002', 'iPhone 13 Pro', '12 months', 800, 999, '128', 'China', 'iphone_13-_pro-1.png', '6.1", Super Retina XDR, OLED, 2532 x 1170 Pixel', 'A15 Bionic', 'Apple GPU 5 nhân', '6 GB', 'iOS 15', '12.0 MP + 12.0 MP + 12.0 MP', '12.0 MP', '3095 mAh', '2, 1 eSIM, 1 Nano SIM', '146.7 x 71.5', '189 g'),
	(31, 2, 'Un-Lock', '003', 'iPhone 13', '12 months', 600, 899, '128', 'China', 'iphone-13-0-0.png', '6.1", Super Retina XDR, OLED, 2532 x 1170 Pixel', 'A15 Bionic', 'Apple GPU 4 nhân ', '4 GB', 'iOS 15', '12.0 MP + 12.0 MP', '12.0 MP', '3225 mAh', '2, 1 eSIM, 1 Nano SIM', '146.7 x 71.5', '164g'),
	(32, 2, 'Un-Lock', '004', 'iPhone 13 mini', '12 months', 500, 799, '128', 'China', 'iphone-13-0-0_3.png', '5.4", Super Retina XDR, OLED, 1080 x 2340 Pixel', 'A15 Bionic', 'Apple GPU 4 nhân', '4 GB', 'iOS 15', '12.0 MP + 12.0 MP', '12.0 MP', '2406 mAh', '2, 1 eSIM, 1 Nano SIM', '131.5 x 64.2', '135 g'),
	(33, 2, 'Un-Lock', '005', 'iPhone 12 Pro Max ', '12 months', 900, 1099, '128', 'China', 'iphone-12-pro-max_3__7.png', '6.7", Super Retina XDR, OLED, 2778 x 1284 Pixel', 'A14 Bionic', 'Apple GPU 4 nhân', '6 GB', 'iOS 14', '12.0 MP + 12.0 MP + 12.0 MP', '12.0 MP', '3687 mAh', '2, 1 eSIM, 1 Nano SIM', '160.8 x 78.1', '228 g'),
	(34, 2, 'Un-Lock', '006', 'iPhone 12 Pro', '12 months', 800, 999, '128', 'China', 'iphone-12-pro-max_2_.png', '6.1", Super Retina XDR, OLED, 2532 x 1170 Pixel', 'A14 Bionic', 'Apple GPU 4 nhân', '6 GB', 'iOS 14', '12.0 MP + 12.0 MP + 12.0 MP', '12.0 MP', '2815 mAh', '2, 1 eSIM, 1 Nano SIM', '146.7 x 71.5', '189 g'),
	(35, 2, 'Un-Lock', '007', 'iPhone 12 Mini ', '12 months', 600, 799, '128', 'China', 'iphone-12_3_.png', '5.4", Super Retina XDR, OLED, 1080 x 2340 Pixel', 'A14 Bionic', 'Apple GPU 4 nhân', '4 GB', 'iOS 14', '12.0 MP + 12.0 MP', '12.0 MP', '2227 mAh', '2, 1 eSIM, 1 Nano SIM', '131.5 x 64.2', '135g'),
	(36, 2, 'Un-Lock', '008', 'iPhone 12', '12 months', 500, 699, '128', 'China', 'iphone-12_1__3.png', '6.1", Super Retina XDR, OLED, 2532 x 1170 Pixel', 'A14 Bionic', 'Apple GPU 4 nhân', '4 GB', 'iOS 14', '12.0 MP + 12.0 MP', '12.0 MP', '2815 mAh', '2, 1 eSIM, 1 Nano SIM', '146.7 x 71.5', '164 g'),
	(37, 3, 'Un-Lock', '009', 'Apple iPad mini 6', '12 months', 800, 1099, '256', 'China', 'ipad-mini-6-5_1.png', '8.3 inches', 'A15', 'A15 Bionic', '4 GB', 'IOS ', '12MP khẩu độ f/1.8', '12MP góc rộng', '3687 mAh', '1 sim', '2266 x 1488 ', '293g'),
	(38, 3, 'Un-Lock', '010', 'Apple iPad Pro 11 2021 M1', '12 months', 800, 1100, '128', 'China', 'ipad-pro-11-2021-1_4.png', '11.0" , Liquid Retina HD , IPS LCD , 2388 x 1668 Pixel', 'Apple M1', 'Apple M1', '8 GB', 'iPadOS', '10.0 MP + 12.0 MP', '12.0 MP', '3687 mAh', '0', '178.5 x 247.6', '471 g'),
	(39, 3, 'Un-Lock', '011', 'iPad 10.2 2021', '12 months', 800, 999, '128', 'China', 'ipad-10-2-2021-1_2.png', '10.2" , Retina HD , IPS LCD , 2048 x 1536 Pixel', 'A13 Bionic', 'Apple GPU 4 nhân', '3 GB', 'iPadOS', '8.0 MP', '12.0 MP', '3687 mAh', '0', '174.1 x 250.6', '490 g'),
	(40, 1, 'Un-Lock', '013', 'Apple Macbook Air 13 (MGN73SAA)', '12 months', 1200, 1500, '128', 'China', '56564_mba__4_.png', '13.3", 2560 x 1600 Pixel, IPS, IPS LCD LED Backlit, True Tone', 'GPU 7 nhân', 'Apple M1', '8 GB, LPDDR4', 'Mac OS', 'GPU 7 nhân', '', '720p FaceTime HD camera', '0', ' 304.1 x 212.4', '1.29'),
	(41, 1, 'Un-Lock', '012', 'Apple Macbook Pro 13 Touchbar', '12 months', 1200, 1500, '128', 'China', '56570_mbp_m1__5_.png', '13.3", 2560 x 1600 Pixel, IPS, IPS LCD LED Backlit, True Tone', 'Apple M1', 'Apple M1', '8 GB, LPDDR4', 'OS', '0', '720p FaceTime HD camera', '3225 mAh', '0', '304.1 x 15.6 x 212.2', ' 1.4');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
