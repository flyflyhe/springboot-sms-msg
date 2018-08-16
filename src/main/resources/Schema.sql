CREATE TABLE IF NOT EXISTS `platform` (
  `id` int unsigned primary key auto_increment,
  `name` varchar(128) not null unique comment '平台名',
  `type` tinyint(1) not null default 1 comment '1:短信 2:邮件'
  `created_time` int unsigned default 0,
  `update_time` int unsigned default 0
)ENGINE='INnoDB' DEFAULT CHARSET='utf8';

CREATE TABLE IF NOT EXISTS `secret` (
  `id` int unsigned primary key auto_increment,
  `platform_id` int unsigned not null,
  `key` varchar(128) default '' comment '验证key',
  `secret` varchar(128) default '' comment '验证密钥',
  `sign_id` int unsigned default 0 comment '默写平台有签名id',
  `sign_name` varchar(128) default '' comment '某些平台有签名',
  `tmplate` varchar(512) default '' comment '发送模板',
  `created_time` int unsigned default 0,
  `update_time` int unsigned default 0
)ENGINE='INnoDB' DEFAULT CHARSET='utf8';