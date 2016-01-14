CREATE TABLE `testtable` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin

insert into `testtable` (`id`, `name`, `date`) values('1','panda1','2015-12-21 20:08:11');
insert into `testtable` (`id`, `name`, `date`) values('2','panda2','2015-12-22 10:24:32');
insert into `testtable` (`id`, `name`, `date`) values('3','panda3','2015-12-22 14:50:05');