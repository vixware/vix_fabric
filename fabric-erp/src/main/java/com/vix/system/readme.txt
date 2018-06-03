1.权限管理
	关键字
		用户：权限的持有者。一般来说通过登录账号对应客观世界的一个人，一个用户有且只能在一个部门下。用户在权限系统中又分为不同的类型：一般用户/超级管理员（不受权限限制，在应用系统中有无限的权力）
		权限：一个权限对应一个功能，这个功能可以是页面/页面中的表单元素（如按钮/文本框）/业务层的方法，在技术层面看，一个权限只是一个字符串。目的是用户拥有的权限与功能的配置权限做匹配，以达到权限验证效果
		角色：权限的集合，因为权限的颗粒度太小无法满足对一个完整业务授权管理的描述。如会计角色，要求可以增加、删除与修改会计科目（或更多），而增加或删除会计科目均是一个功能单元应分别对应各自的权限。在应用系统中，一个角色实际上就是一个工作职能所拥有的权限集合。
		用户组：是角色/用户集合，即一个用户组有0-n个角色，n个用户均可以在这个组下。反之亦成立。
	权限范围
		用户级：只能看到当前用户自己所创建的订单
		当前部门级：如果当前用户与订单的创建人在同一个部门，则当前用户在查询订单时可以看到该订单
		部门级：可以为当前用户指定一个部门，使他只能看到这个部门下所有员工所创建的订单
		部门及子部门级：可以为当前用户指定一个部门，使他可以能看到这个部门包括这个部门下所有的子孙部门中所有员工所创建的订单（部门或者说是组织结构应是树形结构）
		系统级：能看到所有订单不受到任何组织结构及创建人的限制





2.组织机构管理
	关键字
		组织结构：是用户与部门的总称。部门应是树形结构。之所以在权限管理中引用组织结构，是因为影响权限对数据的筛选范围。对于范围可以分为：用户级(当前用户自己创建的记录)/系统级(所有记录)/当前部门级(当前用户所在的部门中所有用户创建的记录)/部门级/部门及子部门级。对于后两种类型还需要授权时给出参考部门。
	简介
		包含部门及公司级组织机构。


    1.用户表Users

    CREATE TABLE `users` (

       -- 账号是否有限 1. 是 0.否
       `enable` int(11) default NULL,
       `password` varchar(255) default NULL,
       `account` varchar(255) default NULL,
       `id` int(11) NOT NULL auto_increment,
       PRIMARY KEY  (`id`)
    )

 

   2.角色表Roles

   CREATE TABLE `roles` (
     `enable` int(11) default NULL,
     `name` varchar(255) default NULL,
     `id` int(11) NOT NULL auto_increment,
     PRIMARY KEY  (`id`)
   )

 

   3 用户_角色表users_roles

   CREATE TABLE `users_roles` (

     --用户表的外键
     `uid` int(11) default NULL,

     --角色表的外键
     `rid` int(11) default NULL,
     `urId` int(11) NOT NULL auto_increment,
     PRIMARY KEY  (`urId`),
     KEY `rid` (`rid`),
     KEY `uid` (`uid`),
    CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `roles` (`id`),
    CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `users` (`id`)
   )

 

   4.资源表resources

   CREATE TABLE `resources` (
     `memo` varchar(255) default NULL,

     -- 权限所对应的url地址
     `url` varchar(255) default NULL,

     --优先权
     `priority` int(11) default NULL,

     --类型
     `type` int(11) default NULL,

     --权限所对应的编码，例201代表发表文章
     `name` varchar(255) default NULL,
     `id` int(11) NOT NULL auto_increment,
     PRIMARY KEY  (`id`)
   )

 

   5.角色_资源表roles_resources

    CREATE TABLE `roles_resources` (
      `rsid` int(11) default NULL,
      `rid` int(11) default NULL,
      `rrId` int(11) NOT NULL auto_increment,
      PRIMARY KEY  (`rrId`),
      KEY `rid` (`rid`),
      KEY `roles_resources_ibfk_2` (`rsid`),
      CONSTRAINT `roles_resources_ibfk_2` FOREIGN KEY (`rsid`) REFERENCES `resources` (`id`),
      CONSTRAINT `roles_resources_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `roles` (`id`)
      )


