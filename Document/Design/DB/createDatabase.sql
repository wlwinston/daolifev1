

/*==============================================================*/
/* Table: dl_comment                                             */
/*==============================================================*/
create table daolife.dl_comment
(
   comment_id          smallint(6) not null auto_increment,
   content_id          smallint(6) not null,
   comment_body        varchar(1000) not null,
   rela_commentid      smallint(6),
   rela_userid         smallint(6) not null,
   user_id             smallint(6) not null,
   status              varchar(1) not null default '0',
   posttime            datetime not null,
   primary key (comment_id)
);

alter table daolife.dl_comment comment '回复表';

/*==============================================================*/
/* Table: dl_friend                                             */
/*==============================================================*/
create table daolife.dl_friend
(
   fri_id               smallint(6) not null auto_increment,
   fid_follow           smallint(6) not null,
   fid_fans             smallint(6) not null,
   primary key (fri_id)
);

alter table daolife.dl_friend comment '关注表';



/*==============================================================*/
/* Table: dl_hotdao                                             */
/*==============================================================*/
create table daolife.dl_hotdao
(
   hotdao_id            smallint(10) not null auto_increment,
   daonum               smallint(6) not null,
   user_name            varchar(64) not null default '0',
   content_body         varchar(1000) not null,
   retwitt_num          smallint(10) not null default 0,
   posttime             int(10) not null,
   up_sum               smallint(10) not null,
   user_id              smallint(10) not null,
   primary key (hotdao_id)
);

alter table daolife.dl_hotdao comment '最热叨';





/*==============================================================*/
/* Table: dl_topic                                              */
/*==============================================================*/
create table daolife.dl_topic
(
   topic_id             smallint(6) not null auto_increment,
   topic_body           varchar(20) not null,
   open                 smallint(1) not null default 0,
   primary key (topic_id)
);

alter table daolife.dl_topic comment '话题表';


/*==============================================================*/
/* Table: dl_contentTopic                                          */
/*==============================================================*/
create table daolife.dl_contenttopic
(
   contenttopic_id         smallint(10) not null auto_increment,
   content_id           smallint(10) not null,
   topic_id             smallint(6) not null,
   primary key (contenttopic_id)
);

alter table daolife.dl_contentat comment 'topic关联信息表';



/*==============================================================*/
/* Table: dl_contentat                                          */
/*==============================================================*/
create table daolife.dl_contentat
(
   contentat_id         smallint(10) not null auto_increment,
   content_id           smallint(10) not null,
   status_id            smallint(6) not null default 0,
   status_uid           smallint(10) not null,
   status_uname         varchar(64) not null,
   status_type          varchar(10),
   primary key (contentat_id)
);

alter table daolife.dl_contentat comment 'at信息表';





/*==============================================================*/
/* Table: dl_users                                              */
/*==============================================================*/
create table daolife.dl_users
(
   user_id              smallint(6) not null auto_increment,
   user_name            varchar(64) not null,
   user_nickname            varchar(64) not null,
   user_url             varchar(64),
   password             varchar(64) not null,
   salt                 varchar(6),
   user_head            varchar(200) not null default '0',
   mailadres            varchar(100) not null,
   home_city            varchar(16),
   live_city            varchar(16),
   birthday             varchar(10),
   signupdate           datetime not null,
   user_gender          varchar(8),
   user_info            varchar(255) not null default '这家伙很懒，什么也没写。',
   isclose              tinyint(1) not null default 0,
   last_login           datetime not null default 0,
   musicaddr            varchar(200),
   follow_num           smallint(6) not null default 0,
   fans_num             smallint(6),
   photo_num            smallint(6) not null default 0,
   at_week_num          smallint(6),
   at_month_num         smallint(6),
   at_sum_num           smallint(6),
   recommendInd         varchar(1) not null default '0',
   user_address         varchar(200),
   user_postcode        varchar(6),
   qq                   int(15),
   msn                  varchar(50),
   gtalk                varchar(50),
   auth_email           varchar(50) not null default '0',
   userlock             tinyint(1) not null default 0,
   primary key (user_id)
);

alter table daolife.dl_users comment '用户表';


drop table if exists daolife.dl_userroles;

/*==============================================================*/
/* Table: dl_userroles                                          */
/*==============================================================*/
create table daolife.dl_userroles
(
   userroles_id         smallint(12) not null auto_increment,
   user_id              smallint(10) not null,
   roles_name           varchar(30) not null,
   primary key (userroles_id)
);

alter table daolife.dl_userroles comment '用户角色表';


/*==============================================================*/
/* Table: dl_messages                                           */
/*==============================================================*/
create table daolife.dl_messages
(
   message_id           smallint(10) not null auto_increment,
   user_id              smallint(10) not null,
   message_body         varchar(300) not null,
   m_time               datetime not null,
   isread               smallint(1) not null default 0,
   type                 varchar(1) not null,
   primary key (message_id)
);

alter table daolife.dl_messages comment '消息表';



/*==============================================================*/
/* Table: dl_content                                            */
/*==============================================================*/
create table daolife.dl_content
(
   content_id           smallint(10) not null auto_increment,
   user_id              smallint(10) not null,
   topicid              smallint(6) not null default 0,
   content_body         varchar(1000) not null,
   posttime             datetime not null,
   origin_id            smallint(10) ,
   origin_allid         varchar(400) ,
   retwitt_num          smallint(10) not null default 0,
   up_num               smallint(10) not null default 0,
   status               varchar(1) not null default '0',
   type                 varchar(10) not null default '网页',
   primary key (content_id)
);

alter table daolife.dl_content comment '叨内容表';




/*==============================================================*/
/* Table: dl_product                                            */
/*==============================================================*/
create table daolife.dl_product
(
   product_id           smallint(6) not null auto_increment,
   product_name         varchar(1000) not null,
   product_url          varchar(200) not null,
   product_pic          varchar(200) NOT NULL,
   product_author       varchar(64) not null,
   product_authorurl    varchar(64) not null,
   product_sum          smallint(6) not null default 0,
   product_daonum       smallint(6) NOT NULL,
   product_followsum    int(10) not null,
   product_contentid    smallint(10) NOT NULL,
   product_posttime               datetime not null,
   product_tshirtPic    varchar(200) not null,
   primary key (product_id)
);

alter table daolife.dl_product comment '产品表';

/*==============================================================*/
/* Table: dl_productfollow                                            */
/*==============================================================*/
create table daolife.dl_productfollow
(
   productfollow_id            smallint(6) not null auto_increment,
   productfollow_productid     smallint(6) NOT NULL,
   productfollow_userid     smallint(6) NOT NULL,
   primary key (productfollow_id)
);

alter table daolife.dl_productfollow comment '产品关注表';


/*==============================================================*/
/* Table: dl_uplog                                              */
/*==============================================================*/
create table daolife.dl_uplog
(
   uplog_id             smallint(10) not null auto_increment,
   hotdao_id            smallint(10) not null,
   user_id              smallint(10) not null default 0,
   upIp                 varchar(50) not null,
   uptime               datetime not null,
   primary key (uplog_id)
);

alter table daolife.dl_uplog comment '顶历史表';




/*==============================================================*/
/* Table: dl_activity                                           */
/*==============================================================*/
create table daolife.dl_activity
(
   activity_id          smallint(6) not null auto_increment,
   activity_name        varchar(20) not null,
   activity_description varchar(200) not null,
   activity_sponsor     varchar(20) not null,
   topic_id             varchar(20) not null,
   activity_startdate   int(10) not null default 0,
   activity_enddate     int(10),
   primary key (activity_id)
);

alter table daolife.dl_activity comment '活动表';
