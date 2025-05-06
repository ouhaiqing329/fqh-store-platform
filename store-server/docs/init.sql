-- 商品表
CREATE TABLE `product` (
    `id` varchar(64) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `price` decimal(10,2) NOT NULL,
    `stock` int NOT NULL,
    `image_url` varchar(255) DEFAULT NULL,
    `status` tinyint DEFAULT '1' COMMENT '1-上架 0-下架',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     PRIMARY KEY (`id`)
) ;

-- 用户表
CREATE TABLE `user` (
    `id` varchar(64) NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL,
    `password` varchar(100) NOT NULL,
    `phone` varchar(20) DEFAULT NULL,
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     PRIMARY KEY (`id`),
     UNIQUE KEY `idx_username` (`username`)
) ;

-- 购物车表
CREATE TABLE `cart` (
    `id` varchar(64) NOT NULL AUTO_INCREMENT,
    `user_id` varchar(64) NOT NULL,
    `product_id` varchar(64) NOT NULL,
    `quantity` int NOT NULL DEFAULT '1',
    `selected` tinyint DEFAULT '1' COMMENT '1-选中 0-未选中',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     PRIMARY KEY (`id`),
     UNIQUE KEY `idx_user_product` (`user_id`,`product_id`)
) ;