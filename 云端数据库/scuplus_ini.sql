-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2018-12-21 20:47:35
-- 服务器版本： 5.5.60-log
-- PHP 版本： 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `scuplus_ini`
--

-- --------------------------------------------------------

--
-- 表的结构 `admins`
--

CREATE TABLE `admins` (
  `id` int(10) UNSIGNED NOT NULL,
  `role` tinyint(1) NOT NULL,
  `account` int(10) UNSIGNED NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `movies`
--

CREATE TABLE `movies` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` tinytext NOT NULL,
  `duration` smallint(5) UNSIGNED NOT NULL,
  `description` text NOT NULL,
  `type` tinytext NOT NULL,
  `star` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `movies`
--

INSERT INTO `movies` (`id`, `name`, `duration`, `description`, `type`, `star`) VALUES
(1, '海王', 143, '华纳兄弟影片公司与导演温子仁联手为您呈现波澜壮阔的动作冒险电影——《海王》！横跨七大洋的广阔海底世界徐徐展开，给观众带来震撼十足的视觉奇观。本片由杰森·莫玛领衔主演，讲述半人半亚特兰蒂斯血统的亚瑟·库瑞踏上永生难忘的征途——他不但需要直面自己的特殊身世，更不得不面对生而为王的考验：自己究竟能否配得上“海王”之名。', '动作,冒险,奇幻', '杰森·莫玛,艾梅柏·希尔德,威廉·达福,妮可·基德曼'),
(2, '龙猫', 86, '小月的母亲生病住院了，父亲带着她与四岁的妹妹小梅到乡间的居住。她们对那里的环境都感到十分新奇，也发现了很多有趣的事情。她们遇到了很多小精灵，她们来到属于她们的环境中，看到了她们世界中很多的奇怪事物，更与一只大大胖胖的龙猫成为了朋友。龙猫与小精灵们利用他们的神奇力量，为小月与妹妹带来了很多神奇的景观，令她们大开眼界。 妹妹小梅常常挂念生病中的母亲，嚷着要姐姐带着她去看母亲，但小月拒绝了。小梅竟然自己前往，不料途中迷路了，小月只好寻找她的龙猫及小精灵朋友们帮助。', '动画,冒险,家庭', '秦岚,日高法子,坂本千夏,糸井重里,岛本须美'),
(3, '网络迷踪', 103, '工程师大卫·金（约翰·赵 饰）一直引以为傲的16岁乖女玛戈特突然失踪。前来调查此案的警探怀疑女儿离家出走。不满这一结论的父亲为了寻找真相，独自展开调查。他打开了女儿的笔记本电脑，用社交软件开始寻找破案线索。大卫必须在女儿消失之前，沿着她在虚拟世界的足迹找到她…', '悬疑,犯罪,剧情', '约翰·赵,黛博拉·梅辛,米切尔·拉');

-- --------------------------------------------------------

--
-- 表的结构 `orders`
--

CREATE TABLE `orders` (
  `id` int(10) UNSIGNED NOT NULL,
  `token` char(12) NOT NULL,
  `time` int(10) UNSIGNED NOT NULL,
  `source` tinyint(1) NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `num` tinyint(3) UNSIGNED NOT NULL,
  `price` decimal(5,2) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `order_seats`
--

CREATE TABLE `order_seats` (
  `id` int(10) UNSIGNED NOT NULL,
  `schedule_id` int(10) UNSIGNED NOT NULL,
  `seat_id` int(10) UNSIGNED NOT NULL,
  `order_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- 表的结构 `rooms`
--

CREATE TABLE `rooms` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` tinytext NOT NULL,
  `row` tinyint(3) UNSIGNED NOT NULL,
  `col` tinyint(3) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `rooms`
--

INSERT INTO `rooms` (`id`, `name`, `row`, `col`) VALUES
(1, '1号厅 6x7', 6, 7),
(2, '2号厅 5x5', 5, 5),
(3, '3号厅 4x4', 4, 4);

-- --------------------------------------------------------

--
-- 表的结构 `schedules`
--

CREATE TABLE `schedules` (
  `id` int(10) UNSIGNED NOT NULL,
  `movie_id` int(10) UNSIGNED NOT NULL,
  `date` date NOT NULL,
  `start_time` char(5) NOT NULL,
  `room_id` tinyint(3) UNSIGNED NOT NULL,
  `price` decimal(5,2) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `schedules`
--

INSERT INTO `schedules` (`id`, `movie_id`, `date`, `start_time`, `room_id`, `price`) VALUES
(2, 1, '2018-12-14', '14:00', 2, '44.00'),
(3, 1, '2018-12-15', '19:20', 3, '34.00'),
(4, 2, '2018-12-14', '10:05', 3, '29.00'),
(5, 2, '2018-12-15', '11:55', 2, '29.00'),
(6, 2, '2018-12-15', '17:30', 2, '29.00'),
(7, 3, '2018-12-14', '21:30', 1, '34.00'),
(8, 3, '2018-12-15', '22:55', 1, '29.00');

-- --------------------------------------------------------

--
-- 表的结构 `seats`
--

CREATE TABLE `seats` (
  `id` int(10) UNSIGNED NOT NULL,
  `room_id` tinyint(3) UNSIGNED NOT NULL,
  `row` tinyint(3) UNSIGNED NOT NULL,
  `col` tinyint(3) UNSIGNED NOT NULL,
  `name` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转存表中的数据 `seats`
--

INSERT INTO `seats` (`id`, `room_id`, `row`, `col`, `name`) VALUES
(1, 1, 1, 1, '1排1号'),
(2, 1, 1, 2, '1排2号'),
(3, 1, 1, 3, '1排3号'),
(4, 1, 1, 4, '1排4号'),
(5, 1, 1, 5, '1排5号'),
(6, 1, 1, 6, '1排6号'),
(7, 1, 1, 7, '1排7号'),
(8, 1, 2, 1, '2排1号'),
(9, 1, 2, 2, '2排2号'),
(10, 1, 2, 3, '2排3号'),
(11, 1, 2, 4, '2排4号'),
(12, 1, 2, 5, '2排5号'),
(13, 1, 2, 6, '2排6号'),
(14, 1, 2, 7, '2排7号'),
(15, 1, 3, 1, '3排1号'),
(16, 1, 3, 2, '3排2号'),
(17, 1, 3, 3, '3排3号'),
(18, 1, 3, 4, '3排4号'),
(19, 1, 3, 5, '3排5号'),
(20, 1, 3, 6, '3排6号'),
(21, 1, 3, 7, '3排7号'),
(22, 1, 5, 1, '5排1号'),
(23, 1, 5, 2, '5排2号'),
(24, 1, 5, 3, '5排3号'),
(25, 1, 5, 4, '5排4号'),
(26, 1, 5, 5, '5排5号'),
(27, 1, 5, 6, '5排6号'),
(28, 1, 5, 7, '5排7号'),
(29, 1, 6, 1, '6排1号'),
(30, 1, 6, 2, '6排2号'),
(31, 1, 6, 3, '6排3号'),
(32, 1, 6, 4, '6排4号'),
(33, 1, 6, 5, '6排5号'),
(34, 1, 6, 6, '6排6号'),
(35, 1, 6, 7, '6排7号'),
(36, 2, 1, 1, '1排1号'),
(37, 2, 1, 2, '1排2号'),
(38, 2, 1, 3, '1排3号'),
(39, 2, 1, 4, '1排4号'),
(40, 2, 1, 5, '1排5号'),
(41, 2, 2, 1, '2排1号'),
(42, 2, 2, 2, '2排2号'),
(43, 2, 2, 3, '2排3号'),
(44, 2, 2, 4, '2排4号'),
(45, 2, 2, 5, '2排5号'),
(46, 2, 3, 1, '3排1号'),
(47, 2, 3, 2, '3排2号'),
(48, 2, 3, 3, '3排3号'),
(49, 2, 3, 4, '3排4号'),
(50, 2, 3, 5, '3排5号'),
(51, 2, 5, 1, '5排1号'),
(52, 2, 5, 2, '5排2号'),
(53, 2, 5, 3, '5排3号'),
(54, 2, 5, 4, '5排4号'),
(55, 2, 5, 5, '5排5号'),
(56, 3, 1, 1, '1排1号'),
(57, 3, 1, 2, '1排2号'),
(58, 3, 1, 3, '1排3号'),
(59, 3, 1, 4, '1排4号'),
(60, 3, 2, 1, '2排1号'),
(61, 3, 2, 2, '2排2号'),
(62, 3, 2, 3, '2排3号'),
(63, 3, 2, 4, '2排4号'),
(64, 3, 3, 1, '3排1号'),
(65, 3, 3, 2, '3排2号'),
(66, 3, 3, 3, '3排3号'),
(67, 3, 3, 4, '3排4号'),
(68, 3, 4, 1, '4排1号'),
(69, 3, 4, 2, '4排2号'),
(70, 3, 4, 3, '4排3号'),
(71, 3, 4, 4, '4排4号');

-- --------------------------------------------------------

--
-- 表的结构 `vips`
--

CREATE TABLE `vips` (
  `id` int(10) UNSIGNED NOT NULL,
  `mobile` char(11) NOT NULL,
  `points` int(10) UNSIGNED NOT NULL DEFAULT '0',
  `discount` decimal(3,2) NOT NULL DEFAULT '1.00',
  `password` text NOT NULL,
  `name` tinytext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 转储表的索引
--

--
-- 表的索引 `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `order_seats`
--
ALTER TABLE `order_seats`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `schedules`
--
ALTER TABLE `schedules`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `seats`
--
ALTER TABLE `seats`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `vips`
--
ALTER TABLE `vips`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `admins`
--
ALTER TABLE `admins`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `movies`
--
ALTER TABLE `movies`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用表AUTO_INCREMENT `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `order_seats`
--
ALTER TABLE `order_seats`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- 使用表AUTO_INCREMENT `schedules`
--
ALTER TABLE `schedules`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- 使用表AUTO_INCREMENT `seats`
--
ALTER TABLE `seats`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- 使用表AUTO_INCREMENT `vips`
--
ALTER TABLE `vips`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
